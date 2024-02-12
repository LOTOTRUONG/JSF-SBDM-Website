package vn.loto.jsf04.METIER;

public class Couleur {
    private Integer id;
    private String nomCouleur;

    public Couleur(){
        id=0;
        nomCouleur = "";
    }
    public Couleur(Integer id, String nomCouleur)
    {
        this.id = id;
        this.nomCouleur = nomCouleur;
    }


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNomCouleur()
    {
        return nomCouleur;
    }

    public void setNomCouleur(String nomCouleur)
    {
        this.nomCouleur = nomCouleur;
    }




    @Override
    public String toString()
    {
        return nomCouleur;
    }
}
