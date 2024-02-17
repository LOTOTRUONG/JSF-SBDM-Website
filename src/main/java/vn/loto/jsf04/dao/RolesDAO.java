package vn.loto.jsf04.dao;

import vn.loto.jsf04.metier.Roles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RolesDAO extends DAO<Roles, Roles, Integer> {
    @Override
    public Roles getByID(Integer id) {
        String sqlRequest = "SELECT * FROM ROLES WHERE id_role = " +id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if (resultSet.next()){
                return new Roles(resultSet.getInt(1), resultSet.getString(2) );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;     }

    @Override
    public ArrayList<Roles> getAll() {
        ArrayList<Roles> liste = new ArrayList<>();
        String sqlRequest = "SELECT * from ROLES";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()){
                liste.add(new Roles(resultSet.getInt(1), resultSet.getString(2)));
            }
            resultSet.close();
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Roles> getLike(Roles object) {
        return null;
    }

    @Override
    public boolean insert(Roles object) {
        return false;
    }

    @Override
    public boolean update(Roles object) {
        return false;
    }

    @Override
    public boolean delete(Roles object) {
        return false;
    }
}
