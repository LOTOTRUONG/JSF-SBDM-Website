package vn.loto.jsf04.dao;
import vn.loto.jsf04.metier.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeDAO extends DAO<Type, Type, Integer> {
    @Override
    public Type getByID(Integer id) {
        String sqlRequest = "Select id_type, nom_type from TYPEBIERE where id_type = " + id;
        Type type;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if(resultSet.next()) return new Type(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Type> getAll(){
        ArrayList<Type> listType = new ArrayList<>();
        String sqlRequest = "SELECT id_type, nom_type FROM TYPEBIERE";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                listType.add(new Type(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listType;
    }

    @Override
    public ArrayList<Type> getLike(Type object) {
        String sqlCommand = "SELECT id_type, nom_type FROM TYPEBIERE WHERE id_type LIKE '%" + object.getLibelle()+"%'";
        ArrayList<Type> listType = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                listType.add(new Type(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {e.printStackTrace();}
        return listType;
    }
    @Override
    public boolean update(Type type) {
        String sqlRequest = "update TYPEBIERE set nom_type = ? WHERE id_type = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,type.getLibelle());
            preparedStatement.setInt(2, type.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean insert(Type type) {
        String sqlRequest = "insert into TYPEBIERE values " + type.getLibelle();
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlRequest);
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(Type object) {
        String sqlRequest = "Delete from TYPEBIERE WHERE ID_Type = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            return false;
        }
    }
}

