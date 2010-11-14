/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.controller;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import fr.iut2.client.event.ActionEvent;
import fr.iut2.client.event.ActionEventHandler;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.model.Sponsor;
import fr.iut2.client.model.SponsorArgent;
import fr.iut2.client.model.SponsorLot;
import fr.iut2.client.service.MyService;
import fr.iut2.client.service.MyServiceAsync;
import fr.iut2.client.view.AddSponsorPopup;
import fr.iut2.client.view.Racine;
import fr.iut2.client.view.ViewASponsor;

/**
 *
 * @author sokarys
 */
public class Controleur {

    private final HandlerManager eventBus;
    private final MyServiceAsync myService;
    
	private HasWidgets container;
        private ListSponsor listSponsor = null;
        private Racine racine;

	public Controleur(MyServiceAsync service, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.myService = service;
		bind();
	}

	private void bind() {
		eventBus.addHandler(ActionEvent.TYPE, new ActionEventHandler() {

                        @Override
			public void onActionEvent(ActionEvent event) {
                                if(event.getAction().contains("CHANGE_LIST_STATUS_LOT")) {
                                        int selectedIndex = Racine.get().getViewDotation().getLbLot().getSelectedIndex();
                                        Racine.get().getViewDotation().switchLigneDon(selectedIndex);
                                }else if(event.getAction().contains("CHANGE_LIST_STATUS_ARGENT")) {
                                        int selectedIndex = Racine.get().getViewDotation().getLbArgent().getSelectedIndex();
                                        Racine.get().getViewDotation().switchLigneArgent(selectedIndex);
                                }else if(event.getAction().contains("Modify_ViewASponsor")) {
                                        if(event.getViewASponsor().getState() == ViewASponsor.STATE.READ){
                                            event.getViewASponsor().toModify();
                                        }else if(event.getViewASponsor().getState() == ViewASponsor.STATE.MODIFY){
                                            event.getViewASponsor().toWatch();
                                        }
                                }else if(event.getAction().contains("Delete_ViewASponsor")) {
                                            if(event.getViewASponsor() != null){
                                                Controleur.this.listSponsor.removeSponsor(event.getViewASponsor().getSponsor());
                                                saveModel(myService);
                                            }
                                }else if(event.getAction().contains("Save_ViewASponsor")) {
                                            event.getViewASponsor().getFormUploadImg().submitUploadForm();
                                            event.getViewASponsor().getSponsor().setName(event.getViewASponsor().getNameText());
                                            event.getViewASponsor().getSponsor().setAdresse(event.getViewASponsor().getAdresseText());
                                            event.getViewASponsor().getSponsor().setUrlLogo(event.getViewASponsor().getImgText());
                                            if(event.getViewASponsor().getSponsor() instanceof SponsorArgent){
                                                ((SponsorArgent) event.getViewASponsor().getSponsor()).setArgent(Float.valueOf(event.getViewASponsor().getArgentText()));
                                            }else  if(event.getViewASponsor().getSponsor() instanceof SponsorLot){
                                                ((SponsorLot) event.getViewASponsor().getSponsor()).setListeLot(event.getViewASponsor().getLotsList());
                                            }
                                }else if(event.getAction().contains("VIEWALL_VIEWALL")){
                                        Racine.get().getviewAllS().switchDeckPanel(0);
                                }else if(event.getAction().contains("VIEWALL_VIEWLOTS")){
                                        Racine.get().getviewAllS().switchDeckPanel(1);
                                }else if(event.getAction().contains("VIEWALL_VIEWARGENTS")){
                                        Racine.get().getviewAllS().switchDeckPanel(2);
                                }else if(event.getAction().contains("VIEWGERE_VIEWALL")){
                                        Racine.get().getViewGereSponsor().switchDeckPanel(0);
                                }else if(event.getAction().contains("VIEWGERE_VIEWLOTS")){
                                        Racine.get().getViewGereSponsor().switchDeckPanel(1);
                                }else if(event.getAction().contains("VIEWGERE_VIEWARGENTS")){
                                        Racine.get().getViewGereSponsor().switchDeckPanel(2);
                                }else if(event.getAction().contains("VIEWGERE_ADD_SPONSOR")){
                                        Racine.get().getViewGereSponsor().addSponsor();
                                }else if(event.getAction().contains("VIEWDOTATION_VIEWLOT")){
                                        Racine.get().getViewDotation().switchDeckPanel(0);
                                }else if(event.getAction().contains("VIEWDOTATION_VIEWARGENT")){
                                        Racine.get().getViewDotation().switchDeckPanel(1);
                                }else if(event.getAction().contains("AddSponsorPopup_CancelSponsor")){
                                        Racine.get().getViewGereSponsor().getPopupadd().hide();
                                }else if(event.getAction().contains("AddSponsorPopup_addSponsor")){
                                        Racine.get().getViewGereSponsor().getPopupadd().getViewUploadFile().submitUploadForm();
                                        Racine.get().getViewGereSponsor().getPopupadd().addSponsor();
                                        Racine.get().getViewGereSponsor().getPopupadd().hide();
                                        
                                }else if(event.getAction().contains("AddSponsorPopup_CancelSponsor")){
                                        Racine.get().getViewGereSponsor().getPopupadd().hide();
                                }else if(event.getAction().contains("AddSponsorPopup_removeEmptylot")){
                                        Racine.get().getViewGereSponsor().getPopupadd().removeEmptySponsorLot();
                                }else if(event.getAction().contains("AddSponsorPopup_addlot")){
                                        Racine.get().getViewGereSponsor().getPopupadd().createNewSponsorLot();
                                }else if(event.getAction().contains("AddSponsorPopup_addArgent")){
                                        Racine.get().getViewGereSponsor().getPopupadd().createNewSponsorArgent();
                                }else if(event.getAction().contains("AddSponsorPopup_addOneLotListe")){
                                        Racine.get().getViewGereSponsor().getPopupadd().addOneLotTotheList();
                                }else if(event.getAction().contains("UPLOADFORM_OK")){
                                        String s = event.getAction().split("=")[1];
                                        event.getViewASponsor().getSponsor().setUrlLogo(s);
                                        saveModel(myService);
                                }
			}
		});

	}


	public void go(HasWidgets container) {
		this.container = container;
                initialisationModel(myService);
                racine = new Racine(eventBus);
                racine.go(this.container);
	}


      private void initialisationModel(MyServiceAsync myService){
             
         AsyncCallback<ListSponsor> getSponsor  = new AsyncCallback<ListSponsor>() {
            @Override
            public void onFailure(Throwable caught) {
               System.out.print("ErrorLOAD");
            }

            @Override
            public void onSuccess(ListSponsor result) {
                Controleur.this.listSponsor = result;
                System.out.print(result.toString());
                Controleur.this.racine.getviewAllS().setList(result);
                Controleur.this.racine.getViewDotation().setList(result);
                Controleur.this.racine.getViewFlyers().setList(result);
                Controleur.this.racine.getViewGereSponsor().setList(result);
    
                System.out.print("OK LOAD");
            }
            };

            myService.getSponsor(getSponsor);

        }


      private void saveModel(MyServiceAsync myService){
      AsyncCallback<Void> setSponsor  = new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {
                   System.out.print("ErrorLOAD");
                }

                @Override
                public void onSuccess(Void result) {
                    System.out.print("OK LOAD");
                    Controleur.this.racine.getviewAllS().setList(listSponsor);
                    Controleur.this.racine.getViewDotation().setList(listSponsor);
                    Controleur.this.racine.getViewFlyers().setList(listSponsor);
                    Controleur.this.racine.getViewGereSponsor().setList(listSponsor);
                }
            };

            myService.setSponsor(listSponsor,setSponsor);
        }

}
