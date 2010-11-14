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
public class ViewAccueil extends Composite{

    private Label title = new Label(Racine.NAMEFIELD_CONSTANT.titreAccueil());
    
    public ViewAccueil(){
        title.setHorizontalAlignment(Label.ALIGN_CENTER);
        this.initWidget(title);
    }
    
}
