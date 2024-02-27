package vn.loto.jsf04.dao;

import vn.loto.jsf04.metier.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User, User, Integer> {
    @Override
    public User getByID(Integer id) {
        return null;    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> listUser = new ArrayList<>();

        String sqlRequest = "SELECT * FROM UTILISATEUR";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                listUser.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }
    public User getByUsername(String username) {
        String query = "SELECT NOM_UTILISATEUR, PASSWORD, NOM_ROLE FROM UTILISATEUR WHERE NOM_UTILISATEUR = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("NOM_UTILISATEUR");
                String password = resultSet.getString("PASSWORD");
                String roleName = resultSet.getString("NOM_ROLE");
                User utilisateur = new User(nom, password, roleName);
                return utilisateur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getAllRoles() {
        List<String> allRoles = new ArrayList<>();
        String query = "SELECT DISTINCT NOM_ROLE FROM UTILISATEUR";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allRoles.add(resultSet.getString("NOM_ROLE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRoles;
    }


    @Override
    public ArrayList<User> getLike(User object) {
        return null;
    }

    @Override
    public boolean insert(User utilisateur) {
        String query = "INSERT INTO UTILISATEUR (NOM_UTILISATEUR, PASSWORD, NOM_ROLE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, utilisateur.getLogin());
            preparedStatement.setString(2, utilisateur.getPassword());
            preparedStatement.setString(3, "user");
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User utilisateur) {
        String sqlRequest = "UPDATE UTILISATEUR SET PASSWORD = ? WHERE NOM_UTILISATEUR = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, utilisateur.getPassword());
            preparedStatement.setString(2, utilisateur.getLogin());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUsernameRole(User updateUtilisateur){
        String sqlRequest = "UPDATE UTILISATEUR SET NOM_ROLE = ? WHERE NOM_UTILISATEUR = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, updateUtilisateur.getRoles());
            preparedStatement.setString(2, updateUtilisateur.getLogin());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public boolean delete(User object) {
        String sqlRequest = "Delete from UTILISATEUR WHERE NOM_UTILISATEUR = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, object.getLogin());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            return false;
        }
    }


}
