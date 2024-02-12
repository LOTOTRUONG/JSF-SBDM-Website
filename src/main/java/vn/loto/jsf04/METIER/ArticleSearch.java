package vn.loto.jsf04.METIER;

import vn.loto.jsf04.METIER.SubMetier.Stock;
import vn.loto.jsf04.METIER.SubMetier.Titrage;
import vn.loto.jsf04.METIER.SubMetier.Volume;

public class ArticleSearch {
    private int id ;
    private String libelle = "";
    private Titrage titrageMin = new Titrage(0F);
    private Titrage titrageMax = new Titrage(30F);

    private Volume volume = new Volume();

    private Stock stock=new Stock();
    private Float prix;
    private Pays pays = new Pays();
    private Continent continent = new Continent();
    private Couleur couleur = new Couleur();
    private Type typeBiere = new Type();
    private Marque marque = new Marque();
    private Fabricant fabricant = new Fabricant();

    public Titrage getTitrageMin() {
        return titrageMin;
    }

    public void setTitrageMin(Titrage titrageMin) {
        this.titrageMin = titrageMin;
    }

    public Titrage getTitrageMax() {
        return titrageMax;
    }

    public void setTitrageMax(Titrage titrageMax) {
        this.titrageMax = titrageMax;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolumne(Volume volume) {
        this.volume = volume;
    }
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Type getTypeBiere() {
        return typeBiere;
    }

    public void setTypeBiere(Type typeBiere) {
        this.typeBiere = typeBiere;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

}
