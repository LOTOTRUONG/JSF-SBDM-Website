package vn.loto.jsf04.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.*;
import vn.loto.jsf04.metier.SubMetier.Volume;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@SessionScoped

public class ArticleBean  implements Serializable {
    private String nameSearch;
    private List<Article> allArticle;
    private Article selectedArticle;
    private ArticleSearch articleSearch;

    @PostConstruct
    public void init(){
        initialize();
    }

    public List<Article> getAllArticle() {
        return allArticle;
    }

    public void setAllArticle(List<Article> allArticle) {
        this.allArticle = allArticle;
    }

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }


    public ArticleSearch getArticleSearch() {
        return articleSearch;
    }

    public void setArticleSearch(ArticleSearch articleSearch) {
        this.articleSearch = articleSearch;
    }


    public String getNameSearch() {
        return nameSearch;
    }
    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }



    private List<Volume> extractVolumeOptions(List<Article> articles) {
        Set<Integer> volumeSet = new HashSet<>();
        for (Article article : articles) {
            volumeSet.add(article.getVolumeArticle().getVolume());
        }
        return volumeSet.stream().map(Volume::new).collect(Collectors.toList());
    }

    public void search() {
        try {
            allArticle = DAOFactory.getArticleDAO().getLike(articleSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }    }




    public void initialize() {
        allArticle = DAOFactory.getArticleDAO().getAll();
        articleSearch = new ArticleSearch();
        selectedArticle = new Article(0, "");
    }

}