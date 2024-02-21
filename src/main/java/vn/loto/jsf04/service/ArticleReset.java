package vn.loto.jsf04.service;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import vn.loto.jsf04.bean.*;

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

    @Inject
    private MarqueBean marqueBean;

    @Inject
    private FabricantBean fabricantBean;
    public void reset(){
        articleBean.initialize();
        paysBean.initialize();
        continentBean.initialize();
        marqueBean.initialize();
        fabricantBean.initialize();
    }
}
