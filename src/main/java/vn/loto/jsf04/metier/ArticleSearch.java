package vn.loto.jsf04.metier;

import lombok.Getter;
import lombok.Setter;
import vn.loto.jsf04.metier.submetier.Stock;
import vn.loto.jsf04.metier.submetier.Titrage;
import vn.loto.jsf04.metier.submetier.Volume;

import java.util.Objects;

public class ArticleSearch {
    @Getter @Setter
    private int id ;
    @Getter @Setter
    private String libelle = "";
    private Titrage titrageMin = new Titrage(0F);
    private Titrage titrageMax = new Titrage(30F);
    @Getter @Setter
    private Volume volume = new Volume();
    @Getter @Setter
    private Stock stock=new Stock();
    @Getter @Setter
    private Float prix;

    @Getter @Setter
    private Pays pays = new Pays();
    @Getter @Setter
    private Continent continent = new Continent();
    @Getter @Setter
    private Couleur couleur = new Couleur();
    @Getter @Setter
    private Type typeBiere = new Type();
    @Getter @Setter
    private Marque marque = new Marque();
    @Getter @Setter
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ArticleSearch that = (ArticleSearch) object;

        if (id != that.id) return false;
        if (!Objects.equals(libelle, that.libelle)) return false;
        if (!Objects.equals(titrageMin, that.titrageMin)) return false;
        if (!Objects.equals(titrageMax, that.titrageMax)) return false;
        if (!Objects.equals(volume, that.volume)) return false;
        if (!Objects.equals(stock, that.stock)) return false;
        if (!Objects.equals(prix, that.prix)) return false;
        if (!Objects.equals(pays, that.pays)) return false;
        if (!Objects.equals(continent, that.continent)) return false;
        if (!Objects.equals(couleur, that.couleur)) return false;
        if (!Objects.equals(typeBiere, that.typeBiere)) return false;
        if (!Objects.equals(marque, that.marque)) return false;
        return Objects.equals(fabricant, that.fabricant);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        result = 31 * result + (titrageMin != null ? titrageMin.hashCode() : 0);
        result = 31 * result + (titrageMax != null ? titrageMax.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (pays != null ? pays.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        result = 31 * result + (couleur != null ? couleur.hashCode() : 0);
        result = 31 * result + (typeBiere != null ? typeBiere.hashCode() : 0);
        result = 31 * result + (marque != null ? marque.hashCode() : 0);
        result = 31 * result + (fabricant != null ? fabricant.hashCode() : 0);
        return result;
    }
}
