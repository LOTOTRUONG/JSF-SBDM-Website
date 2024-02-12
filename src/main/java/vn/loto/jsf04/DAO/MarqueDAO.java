package vn.loto.jsf04.DAO;

import vn.loto.jsf04.METIER.Marque;
import vn.loto.jsf04.METIER.Pays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MarqueDAO extends DAO<Marque, Marque, Integer> {
    @Override
    public Marque getByID(Integer id) {
        String sqlReques = "SELECT ID_MARQUE, NOM_MARQUE FROM MARQUE WHERE ID_MARQUE = " +id;
        Marque marque;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReques);
            if(resultSet.next()) return new Marque(resultSet.getInt(1),resultSet.getString(2));
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Marque getByLibelle(String libelle) {
        String sqlRequest = "Select id_marque, nom_marque from Marque where nom_marque = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, libelle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return new Marque(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ArrayList<Marque> getAll() {
        ArrayList<Marque> liste = new ArrayList<>();
        String sqlRequest = "SELECT ID_Marque, NOM_Marque FROM Marque";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                liste.add(new Marque(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Marque> getLike(Marque object) {
        String sqlCommand = "SELECT ID_Marque, NOM_Marque FROM Marque WHERE ID_Marque LIKE '%" + object.getLibelle()+"%'";
        ArrayList<Marque> liste = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                liste.add(new Marque(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {e.printStackTrace();}
        return liste;
    }

    @Override
    public boolean insert(Marque object) {
        String sqlRequest = "update PMarque set NOM_PMarque = ? WHERE ID_Marque = ?";
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
    public boolean update(Marque object) {
        String sqlRequest = "insert into Marque values " + object.getLibelle();
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlRequest);
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Marque object) {
        String sqlRequest = "Delete from Marque WHERE ID_Marque = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            return false;
        }
    }

    public List<Marque> getByPays(Pays pays) {
        List<Marque> marqueList = new ArrayList<>();
        String sqlRequest = "SELECT id_marque, nom_marque FROM Marque WHERE id_pays = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, pays.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                marqueList.add(new Marque(resultSet.getInt(1), resultSet.getString(2), pays));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return marqueList;
    }

}
