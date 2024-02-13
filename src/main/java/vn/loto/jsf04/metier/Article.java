package vn.loto.jsf04.metier;

import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.metier.SubMetier.Stock;
import vn.loto.jsf04.metier.SubMetier.Titrage;
import vn.loto.jsf04.metier.SubMetier.Volume;

import java.util.Objects;
public class Article {
    @Getter
    @Setter
    private int idArticle;
    @Getter
    @Setter
    private String nomArticle;
    @Getter
    @Setter
    private Volume volumeArticle;
    @Getter
    @Setter
    private Float prixArticle;
    @Getter
    @Setter
    private Titrage titrageArticle;
    @Getter
    @Setter
    private Type typeArticle;
    @Getter
    @Setter
    private Couleur couleurArticle;
    @Getter
    @Setter
    private Marque marqueArticle;
    @Getter
    @Setter
    private Continent continentArticle;
    @Getter
    @Setter
    private Pays paysArticle;
    @Getter
    @Setter
    private Fabricant fabricantArticle;
    @Getter
    @Setter
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
