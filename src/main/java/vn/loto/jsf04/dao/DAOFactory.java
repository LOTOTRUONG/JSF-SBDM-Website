package vn.loto.jsf04.dao;

public class DAOFactory {
    public static CouleurDAO getCouleurDAO() {
        return new CouleurDAO();
    }
    public static TypeDAO getTypeDAO(){return new TypeDAO();}
    public static FabricantDAO getFabricantDAO(){return new FabricantDAO();}
    public static ContinentDAO getContinentDAO(){return new ContinentDAO();}
    public static PaysDAO getPaysDAO(){return new PaysDAO();}
    public static MarqueDAO getMarqueDAO(){return new MarqueDAO();}
    public static UserDAO getUserDAO(){return  new UserDAO();}

    public static ArticleDAO getArticleDAO(){return  new ArticleDAO();}


}
