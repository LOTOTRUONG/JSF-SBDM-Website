package vn.loto.jsf04.service;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import vn.loto.jsf04.bean.ArticleBean;
import vn.loto.jsf04.bean.ContinentBean;
import vn.loto.jsf04.bean.PaysBean;

import java.io.Serializable;
@Named
@ViewScoped
public class ArticleReset implements Serializable {
    @Inject
    private ArticleBean articleBean;
    @Inject
    private PaysBean paysBean;
    @Inject
    private ContinentBean continentBean;
    public void reset(){
        articleBean.initialize();
        paysBean.initialize();
        continentBean.initialize();
    }
}
