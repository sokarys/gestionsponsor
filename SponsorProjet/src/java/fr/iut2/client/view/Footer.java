/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;


import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author sokarys
 */
public class Footer extends Composite{
    private HorizontalPanel p = new HorizontalPanel();
    private Label contenu = new Label(Racine.NAMEFIELD_CONSTANT.contenuFooter());
    private Anchor langFr = new Anchor("<img class='drapeaux' src='ressources/img/fr.jpg'/>",true,"http://127.0.0.1:8888/GestionSponsor.html?gwt.codesvr=127.0.0.1:9997&locale=fr");
    private Anchor langEn = new Anchor("<img class='drapeaux' src='ressources/img/en.jpg'/>",true,"http://127.0.0.1:8888/GestionSponsor.html?gwt.codesvr=127.0.0.1:9997&locale=en");
    
    public Footer(){

        contenu.setHorizontalAlignment(Label.ALIGN_CENTER);
        p.add(contenu);
        langFr.setHorizontalAlignment(Anchor.ALIGN_RIGHT);
        p.add(langFr);
        langEn.setHorizontalAlignment(Anchor.ALIGN_RIGHT);
        p.add(langEn);
        this.initWidget(p);
    }
}
