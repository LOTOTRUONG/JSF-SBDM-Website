package vn.loto.jsf04.dao;

import vn.loto.jsf04.metier.Continent;
import vn.loto.jsf04.metier.Pays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO extends DAO<Pays, Continent, Integer> {
    @Override
    public Pays getByID(Integer id) {
        String sqlRequest = "Select id_pays, nom_pays from pays where id_pays = " +id;
        Pays pays;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if(resultSet.next()) return new Pays(resultSet.getInt(1),resultSet.getString(2));
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public ArrayList<Pays> getPaysMarqueContinent(){
        String sqlRequest = "{Call ps_PaysWithMarque}";
        ArrayList <Pays> liste = new ArrayList<>();
        try(CallableStatement callableStatement = connection.prepareCall(sqlRequest)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Pays pay = new Pays(resultSet.getInt("ID_PAYS"), resultSet.getString("NOM_PAYS"));
                Continent continent = new Continent();
                continent.setLibelle(resultSet.getString("NOM_CONTINENT"));
                pay.setContinent(continent);
                pay.setCountMarque(resultSet.getInt("NUMBER_MARQUE"));
                liste.add(pay);
            }
            resultSet.close();
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return liste;
    }
    @Override
    public ArrayList<Pays> getAll() {
        ArrayList<Pays> liste = new ArrayList<>();
        String sqlRequest = "SELECT ID_Pays, NOM_Pays FROM Pays";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                liste.add(new Pays(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Pays> getLike(Continent continent) {
        if (continent.getId() == 0) {
            return getAll();
        } else {
            return getAllPaysInContinent(continent.getId());
        }
    }

    private ArrayList<Pays> getAllPaysInContinent(int continentId) {
        ArrayList<Pays> liste = new ArrayList<>();
        String sqlCommand = "SELECT ID_PAYS, NOM_PAYS FROM PAYS WHERE ID_CONTINENT = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, continentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idPays = resultSet.getInt("ID_PAYS");
                String nomPays = resultSet.getString("NOM_PAYS");
                liste.add(new Pays(idPays, nomPays));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }




    @Override
    public boolean update(Pays object) {
        String sqlRequest = "update PAYS set NOM_PAYS = ? WHERE ID_PAYS = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, object.getLibelle());
            preparedStatement.setInt(2, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(Pays object) {
        String sqlRequest = "insert into PAYS values " + object.getLibelle();
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlRequest);
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Pays object) {
        String sqlRequest = "Delete from PAYS WHERE ID_PAYS = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            return false;
        }
    }

    public List<Pays> getAllWithContinent() {
        List<Pays> paysList = new ArrayList<>();
        String sqlRequest = "SELECT PAYS.ID_PAYS, PAYS.NOM_PAYS, CONTINENT.NOM_CONTINENT " +
                "FROM PAYS " +
                "JOIN CONTINENT ON PAYS.ID_CONTINENT = CONTINENT.ID_CONTINENT";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                Pays pays = new Pays();
                pays.setId(resultSet.getInt("ID_PAYS"));
                pays.setLibelle(resultSet.getString("NOM_PAYS"));

                Continent continent = new Continent();
                continent.setLibelle(resultSet.getString("NOM_CONTINENT"));

                pays.setContinent(continent);
                paysList.add(pays);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paysList;
    }
}
