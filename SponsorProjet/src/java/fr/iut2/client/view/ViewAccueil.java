/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author sokarys
 */
public class ViewAccueil extends Composite{

    private static ViewAccueilUiBinder uiBinder = GWT.create(ViewAccueilUiBinder.class);

    interface ViewAccueilUiBinder extends UiBinder<HorizontalPanel, ViewAccueil> {}
    
    @UiField(provided=true)
    Label title;


    public ViewAccueil(){
       
        title = new Label();
        title.setText(Racine.NAMEFIELD_CONSTANT.titreAccueil());
        title.setHorizontalAlignment(Label.ALIGN_CENTER);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public String getTitle() {
        return "ViewAccueil";
    }

}
