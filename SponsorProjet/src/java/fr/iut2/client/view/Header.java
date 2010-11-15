/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author sokarys
 */
public class Header extends Composite{
    private Label contenu = new Label(Racine.NAMEFIELD_CONSTANT.contenuHeader());

    public Header(){
        this.initWidget(contenu);
    }

}
