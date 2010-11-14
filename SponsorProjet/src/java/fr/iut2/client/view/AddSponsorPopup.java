/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.iut2.client.event.ActionEvent;
import fr.iut2.client.model.*;
import fr.iut2.client.model.Sponsor;
import fr.iut2.client.model.SponsorArgent;
import fr.iut2.client.model.SponsorLot;
import java.util.ArrayList;

/**
 * Permet de créer des sponsors
 * @author sokarys
 */
public class AddSponsorPopup extends PopupPanel{
    private Button addSponsor,cancelSponsor,addLotToList, removeEmptyLotToList;
    private Button addLot, addArgent;
    private Sponsor sponsor;
    private VerticalPanel choosePanel = new VerticalPanel();
    private DeckPanel dPanel = new DeckPanel();
    private HandlerManager eventBus;
    private TextArea name,adresse,argent;
    private TextArea image;
    private ViewUploadFile formUploadImg;
    private ArrayList<TextArea> listeLot;
    private ListSponsor listeSponsor;
    private VerticalPanel p2 = new VerticalPanel();
    
    public AddSponsorPopup(HandlerManager eventBus,ListSponsor listeSponsor){
        super(false);
        this.setAnimationEnabled(true);
        this.setAutoHideEnabled(false);
        //Choix du add
        this.eventBus = eventBus;
        this.listeSponsor = listeSponsor;

        addLot = new Button("Add Sopnsor Lot");
        addArgent = new Button("Add Sopnsor Argent");
        choosePanel.add(addLot);
        choosePanel.add(addArgent);       
        dPanel.add(choosePanel);

        name = new TextArea();
        name.setText("name");
        adresse = new TextArea();
        adresse.setText("adresse");
        argent = new TextArea();
        argent.setText("argent");
        image  = new TextArea();
        image.setEnabled(false);

        addSponsor = new Button("AddSponsor");
        addLotToList = new Button("Add One Lot");
        removeEmptyLotToList = new Button("Remove Empty lot");
        cancelSponsor = new Button("Cancel");
        addSponsor = new Button("Add");




        
        dPanel.showWidget(0);
        initAction();
        setGlassEnabled(true);
        this.setWidget(dPanel);
        this.center();
        this.show();
        //this.add(this.);
    }


    public void updateListLot(){
        p2.clear();
        for(TextArea t : listeLot){
            p2.add(t);
        }
    }

    public void createNewSponsorArgent(){
        VerticalPanel p = new VerticalPanel();

        sponsor = new SponsorArgent();
        p.add(name);
        p.add(adresse);
        formUploadImg = new ViewUploadFile(eventBus,new ViewASponsor(eventBus,sponsor,true));
        p.add(formUploadImg);
        p.add(image);
        p.add(argent);
        p.add(addSponsor);
        p.add(cancelSponsor);



        dPanel.add(p);



        switchDPanel(1);
        
    }
    
    public void createNewSponsorLot(){
        VerticalPanel p = new VerticalPanel();
        
        sponsor = new SponsorLot();
        p.add(name);
        p.add(adresse);
        formUploadImg = new ViewUploadFile(eventBus,new ViewASponsor(eventBus,sponsor,true));
        p.add(formUploadImg);
        p.add(image);
        p.add(addLotToList);
        p.add(removeEmptyLotToList);
        p.add(p2);
        p.add(addSponsor);
        p.add(cancelSponsor);
        listeLot = new ArrayList<TextArea>();
        
        
        
        dPanel.add(p);
        
        

        switchDPanel(1);
        
    }


    
    private void initAction(){

        cancelSponsor.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("AddSponsorPopup_CancelSponsor"));
               //AddSponsorPopup.this.hide();
            }

        });

        addSponsor.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
               eventBus.fireEvent(new ActionEvent("AddSponsorPopup_addSponsor"));
            }

        });

        
        removeEmptyLotToList.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("AddSponsorPopup_removeEmptylot"));
               
            }
        });

        
        addLot.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("AddSponsorPopup_addlot"));
            }
        });


        addArgent.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("AddSponsorPopup_addArgent"));
            }
        });

        addLotToList.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
               eventBus.fireEvent(new ActionEvent("AddSponsorPopup_addOneLotListe"));
            }
        });
    }

    public void addOneLotTotheList(){
      listeLot.add(new TextArea());
      updateListLot();
    }

    public Sponsor getSponsor(){
        return sponsor;
    }

    public void switchDPanel(int index){
        dPanel.showWidget(index);
    }


    public void addSponsor(){
        if(sponsor instanceof SponsorLot){
           ListeLot l = new ListeLot();

           for(TextArea t : this.listeLot){
               if(!t.getText().isEmpty()){
                    l.addLot(t.getText());
               }
           }

           sponsor.setAdresse(adresse.getText());
           sponsor.setName(name.getText());
           ((SponsorLot) sponsor).setListeLot(l);

           System.out.println(sponsor);
           
           listeSponsor.addSponsor(sponsor);
       }else  if(sponsor instanceof SponsorArgent){
           
           sponsor.setAdresse(adresse.getText());
           sponsor.setName(name.getText());
           try{
                ((SponsorArgent) sponsor).setArgent(Float.valueOf(argent.getText().replaceAll(",", ".")));
            }catch(Exception e){
                ((SponsorArgent) sponsor).setArgent(Float.valueOf(argent.getText() + ".00"));
            }

           listeSponsor.addSponsor(sponsor);
       }
    }

    public void removeEmptySponsorLot(){
         p2.clear();
                for(int i=0; i< listeLot.size() ; i++){
                    if(listeLot.get(i).getText().isEmpty()){
                        listeLot.remove(i);
                        i--;
                    }
                }
        updateListLot();
    }

    public ViewUploadFile getViewUploadFile(){
        return this.formUploadImg;
    }
}
