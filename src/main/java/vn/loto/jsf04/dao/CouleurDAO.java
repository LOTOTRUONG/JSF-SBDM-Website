package vn.loto.jsf04.dao;

import vn.loto.jsf04.metier.Couleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CouleurDAO extends DAO<Couleur, Couleur, Integer> {
    @Override
    public Couleur getByID(Integer id) {
        String sqlRequest = "Select id_couleur, nom_couleur from couleur where id_couleur = " + id;
        Couleur couleur = null;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if(resultSet.next()) return new Couleur(resultSet.getInt(1),resultSet.getString(2));
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Couleur> getAll(){
        ArrayList<Couleur> listCouleur = new ArrayList<>();
        String sqlRequest = "SELECT id_couleur, NOM_COULEUR FROM COULEUR";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            while (resultSet.next()) {
                listCouleur.add(new Couleur(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCouleur;
    }

    @Override
    public ArrayList<Couleur> getLike(Couleur object) {
        String sqlCommand = "SELECT ID_COULEUR, NOM_COULEUR FROM COULEUR WHERE ID_COULEUR LIKE '%" + object.getNomCouleur()+"%'";
        ArrayList<Couleur> listCouleur = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                listCouleur.add(new Couleur(resultSet.getInt(1),resultSet.getString(2)));
            } resultSet.close();
        } catch (Exception e) {e.printStackTrace();}
        return listCouleur;
    }
    @Override
    public boolean update(Couleur couleur) {
        String sqlRequest = "update COULEUR set NOM_Couleur = ? WHERE id_couleur = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,couleur.getNomCouleur());
            preparedStatement.setInt(2, couleur.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean insert(Couleur couleur) {
        String sqlRequest = "insert into COULEUR values " + couleur.getNomCouleur();
        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlRequest);
            return true;
        }catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(Couleur object) {
        String sqlRequest = "Delete from COULEUR WHERE ID_COULEUR = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException E) {
            return false;
        }
    }
}
