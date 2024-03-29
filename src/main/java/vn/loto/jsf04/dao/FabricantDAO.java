package vn.loto.jsf04.dao;
import vn.loto.jsf04.metier.Fabricant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FabricantDAO extends DAO<Fabricant, Fabricant, Integer> {
    @Override
    public Fabricant getByID(Integer id) {
        String sqlRequest = "Select id_fabricant, nom_fabricant from couleur where id_fabricant= " + id;
        Fabricant fabricant;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if(resultSet.next()) return new Fabricant(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Fabricant getByLibelle(String libelle) {
        String sqlRequest = "Select id_fabricant, nom_fabricant from fabricant where nom_fabricant = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, libelle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return new Fabricant(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Fabricant> getAll() {
        ArrayList<Fabricant> liste = new ArrayList<>();
        String sqlRequest = "SELECT * FROM FABRICANT";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()){
                liste.add(new Fabricant(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Fabricant> getLike(Fabricant object) {
        String sqlCommand = "SELECT ID_FABRICANT, NOM_FABRICANT FROM FABRICANT WHERE ID_CONTINENT LIKE '%" + object.getNomFabricant() + "%'";
        ArrayList<Fabricant> liste = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                liste.add(new Fabricant(resultSet.getInt(1), resultSet.getString(2)));
            }
            resultSet.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return liste;
    }


    @Override
    public boolean update(Fabricant object) {
        String sqlRequest = "update FABRICANT set NOM_FABRICANT = ? WHERE ID_FABRICANT = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,object.getNomFabricant());
            preparedStatement.setInt(2, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;}
    }

    @Override
    public boolean insert(Fabricant object) {
        String sqlRequest = "insert into FABRICANT values " + object.getNomFabricant();
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlRequest);
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Fabricant object) {
        String sqlRequest = "Delete from FABRICANT WHERE ID_FABRICANT = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException E) {
            return false;
        }
    }

}

