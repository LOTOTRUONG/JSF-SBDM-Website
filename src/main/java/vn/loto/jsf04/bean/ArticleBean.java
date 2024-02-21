package vn.loto.jsf04.bean;

import com.lowagie.text.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import vn.loto.jsf04.dao.DAOFactory;
import vn.loto.jsf04.metier.*;
import vn.loto.jsf04.service.PrintDocumentView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped

public class ArticleBean  implements Serializable {
    private List<Article> allArticle;
    private Article selectedArticle;
    private ArticleSearch articleSearch;

    @PostConstruct
    public void init() {
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




    public List<Article> completeArticle(String query) {
        List<Article> filteredArticles = new ArrayList<>();
        for (Article article : allArticle) {
            if (article.getNomArticle().toLowerCase().contains(query.toLowerCase())) {
                filteredArticles.add(article);
            }
        }
        return filteredArticles;
    }

    public void search() {
        try {
            allArticle = DAOFactory.getArticleDAO().getLike(articleSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        articleSearch = new ArticleSearch();
        allArticle = DAOFactory.getArticleDAO().getLike(articleSearch);
        selectedArticle = new Article();
    }

}