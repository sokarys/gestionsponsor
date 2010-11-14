/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import fr.iut2.client.event.ActionEvent;

/**
 *
 * @author sokarys
 */
public class ViewGereSponsor extends ViewAllSponsor{
    private HorizontalPanel hPanel = new HorizontalPanel();
    private Button add;
    
    private AddSponsorPopup popupadd;
    public ViewGereSponsor(final HandlerManager eventBus) {
        super(eventBus);
        add = new Button("Rajouter un Sponsor");
       
        
        hPanel.add(add);
       
        panel.insert(hPanel, 0);

        
        add.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWGERE_ADD_SPONSOR"));
            }
        });

       
        
    }

    @Override
    protected void initAction(){
        viewAll.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWGERE_VIEWALL"));
            }
        });

        viewLot.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWGERE_VIEWLOTS"));
            }
        });

        viewArgent.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWGERE_VIEWARGENTS"));
            }
        });
    }

    public AddSponsorPopup getPopupadd() {
        return popupadd;
    }

    public void addSponsor(){
        popupadd = new AddSponsorPopup(eventBus,ViewGereSponsor.this.list);
        popupadd.show();
        popupadd.center();
    }

    @Override
    protected boolean modifyMode() {
        return true;
    }



}
