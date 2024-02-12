package vn.loto.jsf04.SERVICE;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import vn.loto.jsf04.BEAN.ArticleBean;
import vn.loto.jsf04.BEAN.ContinentBean;
import vn.loto.jsf04.BEAN.PaysBean;

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
