package vn.loto.jsf04.dao;

import vn.loto.jsf04.metier.Roles;
import vn.loto.jsf04.metier.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UtilisateurDAO extends DAO<Utilisateur, Roles, Integer> {
    @Override
    public Utilisateur getByID(Integer id) {
        return null;    }

    @Override
    public ArrayList<Utilisateur> getAll() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM UTILISATEUR";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                utilisateurs.add(new Utilisateur(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    public Utilisateur getByUsername(String username) {
        String query = "SELECT NOM_UTILISATEUR, PASSWORD, UTILISATEUR.ID_ROLE, ROLES.NOM_ROLE FROM UTILISATEUR JOIN ROLES ON ROLES.ID_ROLE = UTILISATEUR.ID_ROLE WHERE NOM_UTILISATEUR = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("NOM_UTILISATEUR");
                String password = resultSet.getString("PASSWORD");
                int roleId = resultSet.getInt("ID_ROLE");
                String roleName = resultSet.getString("NOM_ROLE");

                Roles roles = new Roles(roleId, roleName);
                Utilisateur utilisateur = new Utilisateur(nom, password, roles);
                return utilisateur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ArrayList<Utilisateur> getLike(Roles object) {
        return null;
    }

    @Override
    public boolean insert(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateur (NOM_UTILISATEUR, PASSWORD, ID_ROLE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, utilisateur.getUsername());
            preparedStatement.setString(2, utilisateur.getPassword());
            preparedStatement.setInt(3, 2);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Utilisateur utilisateur) {
        String sqlRequest = "UPDATE UTILISATEUR SET PASSWORD = ? WHERE NOM_UTILISATEUR = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, utilisateur.getPassword());
            preparedStatement.setString(2, utilisateur.getUsername());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Utilisateur object) {
        return false;
    }


}
