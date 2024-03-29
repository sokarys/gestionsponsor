/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.event.ActionEvent;
import fr.iut2.client.model.SponsorArgent;
import fr.iut2.client.model.SponsorLot;
import java.util.ArrayList;

/**
 *
 * @author sokarys
 */
public class ViewAllSponsor extends Composite{
    protected ListSponsor list;
    protected FlexTable fTable,fTableLot, fTableArgent;
    protected HandlerManager eventBus;
    protected DeckPanel dPanel;
    protected ArrayList<ViewASponsor> viewasponsor;
    protected ArrayList<ViewASponsor> viewasponsorLot;
    protected ArrayList<ViewASponsor> viewasponsorArgent;
    protected Button viewAll, viewLot, viewArgent;
    protected VerticalPanel panel;
    protected Button openAll;
    protected Button closeAll;
    
/**
 * Constructeur
 * @param eventBus
 */
    public ViewAllSponsor(final HandlerManager eventBus){
        panel = new VerticalPanel();
        HorizontalPanel hPanel = new HorizontalPanel();
        
        dPanel = new DeckPanel();
        viewAll = new Button(Racine.NAMEFIELD_CONSTANT.viewAll());
        viewLot = new Button(Racine.NAMEFIELD_CONSTANT.viewLot());
        viewArgent = new Button(Racine.NAMEFIELD_CONSTANT.viewArgent());
        openAll = new Button(Racine.NAMEFIELD_CONSTANT.openAll());
        closeAll = new Button(Racine.NAMEFIELD_CONSTANT.closeAll());

        hPanel.add(viewAll);
        hPanel.add(viewLot);
        hPanel.add(viewArgent);
        hPanel.add(openAll);
        hPanel.add(closeAll);



        
        
        this.eventBus = eventBus;
        fTable = new FlexTable();
        fTableLot = new FlexTable();
        fTableArgent = new FlexTable();

        dPanel.add(fTable);
        dPanel.add(fTableLot);
        dPanel.add(fTableArgent);

        viewasponsor = new ArrayList<ViewASponsor>();
        viewasponsorArgent = new ArrayList<ViewASponsor>();
        viewasponsorLot = new ArrayList<ViewASponsor>();

        
        panel.add(hPanel);
        panel.add(dPanel);

        initAction();
        
        this.initWidget(panel);


       
    }

    public void openAll(){
        
        for(ViewASponsor v : viewasponsor ){
            v.open(true);
        }
        for(ViewASponsor v : viewasponsorLot ){
            v.open(true);
        }
        for(ViewASponsor v : viewasponsorArgent ){
            v.open(true);
        }
    }

    public void closeAll(){

        for(ViewASponsor v : viewasponsor ){
            v.open(false);
        }
        for(ViewASponsor v : viewasponsorLot ){
            v.open(false);
        }
        for(ViewASponsor v : viewasponsorArgent ){
            v.open(false);
        }
    }

    /**
     * Init event
     */
    protected void initAction(){
        viewAll.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWALL_VIEWALL"));
            }
        });

        viewLot.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWALL_VIEWLOTS"));
            }
        });

        viewArgent.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWALL_VIEWARGENTS"));
            }
        });

        openAll.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("OPENALL_VIEWARGENTS"));
            }
        });

         closeAll.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("CLOSEALL_VIEWARGENTS"));
            }
        });
    }

    /**
     * Fonction pour metre a jour la vue
     * @param l
     */
    public void setList(ListSponsor l){
        fTable.clear();
        viewasponsor.clear();
        viewasponsorLot.clear();
        viewasponsorArgent.clear();
        list = l;
        int j=0;
        for(int i=0; i<list.getNbSponsor(); i++){
            if(i%5==0){
                j++;
            }
            viewasponsor.add(new ViewASponsor(eventBus,list.getASponsor(i),modifyMode()));
            fTable.setWidget(j,i%5,viewasponsor.get(i));
        }

        fTableLot.clear();
        fTableArgent.clear();
        j=0;
        int i1=0,i2=0;
        int k=0;
        for(int i=0; i<list.getNbSponsor(); i++){

            if(list.getASponsor(i) instanceof SponsorLot){
                if(i1%5==0){
                    j++;
                }
                viewasponsorLot.add(new ViewASponsor(eventBus,list.getASponsor(i),modifyMode()));
                fTableLot.setWidget(j,i1%5,viewasponsorLot.get(i1));
                i1++;
            }else if(list.getASponsor(i) instanceof SponsorArgent){
                if(i2%5==0){
                    k++;
                }
                viewasponsorArgent.add(new ViewASponsor(eventBus,list.getASponsor(i),modifyMode()));
                fTableArgent.setWidget(k,i2%5,viewasponsorArgent.get(i2));
                i2++;
            }
        }
        switchDeckPanel(0);
    }

/**
 * Fonction apelé par un event pour changer la vue
 * @param index
 */
    public void switchDeckPanel(int index){
        if(dPanel != null){
            dPanel.showWidget(index);
            if(index == 0){
                viewAll.setEnabled(false);
                viewLot.setEnabled(true);
                viewArgent.setEnabled(true);
            }else if(index == 1){
                viewAll.setEnabled(true);
                viewLot.setEnabled(false);
                viewArgent.setEnabled(true);
            }else {
                viewAll.setEnabled(true);
                viewLot.setEnabled(true);
                viewArgent.setEnabled(false);
            }
        }
    }

    /**
     * Fonction qui permet de dire si les ViewASponsor sont en mode modify ou read! utilise dans la sous classe ViewGereSponsor
     * @return Boolean mode
     */
    protected boolean modifyMode(){
        return false;
    }

    /**
     * Pour pouvoir gérer l'historique
     * @return Titre 
     */
    @Override
    public String getTitle() {
        return "ViewAllSponsor";
    }
}

