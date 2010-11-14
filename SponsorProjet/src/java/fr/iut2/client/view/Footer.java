/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author sokarys
 */
public class Footer extends Composite{
    private Label contenu = new Label(Racine.NAMEFIELD_CONSTANT.contenuFooter());
    public Footer(){
        contenu.setHorizontalAlignment(Label.ALIGN_RIGHT);
        this.initWidget(contenu);
    }
}
