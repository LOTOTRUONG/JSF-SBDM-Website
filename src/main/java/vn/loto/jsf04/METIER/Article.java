package vn.loto.jsf04.METIER;

import vn.loto.jsf04.METIER.SubMetier.Stock;
import vn.loto.jsf04.METIER.SubMetier.Titrage;
import vn.loto.jsf04.METIER.SubMetier.Volume;

import java.util.Objects;
public class Article {
    private int idArticle;
    private String nomArticle;
    private Volume volumeArticle;

    private Float prixArticle;

    private Titrage titrageArticle;
    private Type typeArticle;
    private Couleur couleurArticle;
    private Marque marqueArticle;

    private Continent continentArticle;
    private Pays paysArticle;
    private Fabricant fabricantArticle;
    private Stock stockArticle;
    public Article(int idArticle, String nomArticle) {
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        volumeArticle = new Volume();
        stockArticle = new Stock();
        titrageArticle = new Titrage(0F);
        typeArticle = new Type();
        couleurArticle = new Couleur();
        marqueArticle = new Marque();
        paysArticle = new Pays();
        continentArticle = new Continent();
        fabricantArticle = new Fabricant();
    }

    public Article(int idArticle, String nomArticle, float prixBiere, int volumnBiere, float titrageBiere, int stockBiere, int idType, String nomType, int idCouleur, String nomCouleur, int idMarque, String nomMarque, int idFabricant, String nomFabricant, int idPays, String nomPays, int idContinent, String nomContinent) {
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.prixArticle = prixBiere;
        this.volumeArticle = new Volume(volumnBiere);
        this.titrageArticle = new Titrage(titrageBiere);
        this.typeArticle = new Type(idType, nomType);
        this.couleurArticle = new Couleur(idCouleur, nomCouleur);
        this.marqueArticle = new Marque(idMarque, nomMarque);
        this.fabricantArticle = new Fabricant(idFabricant, nomFabricant);
        this.paysArticle = new Pays(idPays, nomPays);
        this.continentArticle = new Continent(idContinent, nomContinent);
        this.stockArticle = new Stock(stockBiere);
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public Volume getVolumeArticle() {
        return volumeArticle;
    }

    public void setVolumeArticle(Volume volumeArticle) {
        this.volumeArticle = volumeArticle;
    }

    public Type getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(Type typeArticle) {
        this.typeArticle = typeArticle;
    }

    public Couleur getCouleurArticle() {
        return couleurArticle;
    }

    public void setCouleurArticle(Couleur couleurArticle) {
        this.couleurArticle = couleurArticle;
    }
    public Float getPrixArticle() {
        return prixArticle;
    }

    public void setPrixArticle(Float prixArticle) {
        this.prixArticle = prixArticle;
    }

    public Marque getMarqueArticle() {
        return marqueArticle;
    }

    public void setMarqueArticle(Marque marqueArticle) {
        this.marqueArticle = marqueArticle;
    }

    public Titrage getTitrageArticle() {
        return titrageArticle;
    }

    public void setTitrageArticle(Titrage titrageArticle) {
        this.titrageArticle = titrageArticle;
    }

    public Stock getStockArticle() {
        return stockArticle;
    }

    public void setStockArticle(Stock stockArticle) {
        this.stockArticle = stockArticle;
    }
    public Continent getContinentArticle() {
        return continentArticle;
    }

    public void setContinentArticle(Continent continentArticle) {
        this.continentArticle = continentArticle;
    }

    public Pays getPaysArticle() {
        return paysArticle;
    }

    public void setPaysArticle(Pays paysArticle) {
        this.paysArticle = paysArticle;
    }

    public Fabricant getFabricantArticle() {
        return fabricantArticle;
    }

    public void setFabricantArticle(Fabricant fabricantArticle) {
        this.fabricantArticle = fabricantArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return idArticle == article.idArticle && stockArticle == article.stockArticle && Objects.equals(nomArticle, article.nomArticle) && Objects.equals(volumeArticle, article.volumeArticle) && Objects.equals(typeArticle, article.typeArticle) && Objects.equals(couleurArticle, article.couleurArticle) && Objects.equals(marqueArticle, article.marqueArticle) && Objects.equals(titrageArticle, article.titrageArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, nomArticle, volumeArticle, typeArticle, couleurArticle, marqueArticle, titrageArticle, stockArticle);
    }
}
