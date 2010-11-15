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

    /**
     * Permet de créer un popup pour rajouter un sponsor
     * @param eventBus
     * @param listeSponsor
     */
    public AddSponsorPopup(HandlerManager eventBus,ListSponsor listeSponsor){
        super(false);
        this.setAnimationEnabled(true);
        this.setAutoHideEnabled(false);
        //Choix du add
        this.eventBus = eventBus;
        this.listeSponsor = listeSponsor;

        addLot = new Button(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_addLot());
        addArgent = new Button(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_addArgent());
        choosePanel.add(addLot);
        choosePanel.add(addArgent);       
        dPanel.add(choosePanel);

        name = new TextArea();
        name.setText(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_name());
        adresse = new TextArea();
        adresse.setText(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_adresse());
        argent = new TextArea();
        argent.setText(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_argent());
        image  = new TextArea();
        image.setEnabled(false);

        //addSponsor = new Button("AddSponsor");
        addLotToList = new Button(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_addLotList());
        removeEmptyLotToList = new Button(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_removeEmpty());
        cancelSponsor = new Button(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_cancel());
        addSponsor = new Button(Racine.NAMEFIELD_CONSTANT.AddSponsorPopup_addsponsor());




        
        dPanel.showWidget(0);
        initAction();
        setGlassEnabled(true);
        this.setWidget(dPanel);
        this.center();
        this.show();
        //this.add(this.);
    }

/**
 * Mise a jour du panel pour afficher les lots
 */
    public void updateListLot(){
        p2.clear();
        for(TextArea t : listeLot){
            p2.add(t);
        }
    }

    /**
     * fonction pour créer un popup pour rajouter un sponsor Argent
     */
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

    /**
     * fonction pour créer un popup pour rajouter un sponsor Lot
     */
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


    /**
     * Evenements
     */
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


    /**
     * Fonction a apeler pour rajouter un sponsor
     */
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

    /**
     * supprime tous le sponsor de la liste et qui sont vides
     */
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
