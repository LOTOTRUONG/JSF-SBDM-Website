package vn.loto.jsf04.metier;

import java.util.Objects;

public class Pays {
    private Integer id;
    private String libelle;
    private Continent continent;
    private double latitude;
    private double longitude;


    public Pays()
    {
        id=0;
        libelle = "";
        continent = new Continent();
    }
    public Pays(Integer id, String libelle)
    {
        this.id = id;
        this.libelle = libelle;
        this.continent = new Continent();
    }

    public Pays(Integer id, String libelle, Continent continent)
    {
        this.id = id;
        this.libelle = libelle;
        this.continent = continent;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getLibelle()
    {
        return libelle;
    }

    public void setLibelle(String libelle)
    {
        this.libelle = libelle;
    }

    public Continent getContinent()
    {
        return continent;
    }

    public void setContinent(Continent continent)
    {
        this.continent = continent;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return libelle;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return id == pays.id && Objects.equals(libelle, pays.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle);
    }


}

