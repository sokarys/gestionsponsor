/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.controller;


import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import fr.iut2.client.event.ActionEvent;
import fr.iut2.client.event.ActionEventHandler;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.model.SponsorArgent;
import fr.iut2.client.model.SponsorLot;
import fr.iut2.client.service.MyServiceAsync;
import fr.iut2.client.view.Racine;
import fr.iut2.client.view.ViewASponsor;

/**
 *
 * @author sokarys
 */
public class Controleur{

    private final HandlerManager eventBus;
    private final MyServiceAsync myService;
    
	private HasWidgets container;
        private ListSponsor listSponsor = null;
        private Racine racine;

        /**
         * Constructeur
         * @param service
         * @param eventBus
         */
	public Controleur(MyServiceAsync service, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.myService = service;
		bind();
	}

        /**
         * Déclaration des events
         */
	private void bind() {
		eventBus.addHandler(ActionEvent.TYPE, new ActionEventHandler() {

                        @Override
			public void onActionEvent(ActionEvent event) {
                                /**
                                 * Permet de change le sponsor en fonction de la sélection de la liste
                                 */
                                if(event.getAction().contains("CHANGE_LIST_STATUS_LOT")) {
                                        int selectedIndex = Racine.get().getViewDotation().getLbLot().getSelectedIndex();
                                        Racine.get().getViewDotation().switchLigneDon(selectedIndex);
                                }
                                /**
                                 * Permet de change le sponsor en fonction de la sélection de la liste
                                 */
                                else if(event.getAction().contains("CHANGE_LIST_STATUS_ARGENT")) {
                                        int selectedIndex = Racine.get().getViewDotation().getLbArgent().getSelectedIndex();
                                        Racine.get().getViewDotation().switchLigneArgent(selectedIndex);
                                }
                                /**
                                 * Permet d'activer ou de désactiver la modification d'un sponsor
                                 */
                                else if(event.getAction().contains("Modify_ViewASponsor")) {
                                        if(event.getViewASponsor().getState() == ViewASponsor.STATE.READ){
                                            event.getViewASponsor().toModify();
                                        }else if(event.getViewASponsor().getState() == ViewASponsor.STATE.MODIFY){
                                            event.getViewASponsor().toWatch();
                                        }
                                }
                                /**
                                 * Permet de supprimet un sponsor
                                 */
                                else if(event.getAction().contains("Delete_ViewASponsor")) {
                                            if(event.getViewASponsor() != null){
                                                Controleur.this.listSponsor.removeSponsor(event.getViewASponsor().getSponsor());
                                                saveModel(myService);
                                            }
                                }
                                /**
                                 * sauvegarde un sponsor dans la liste
                                 */
                                else if(event.getAction().contains("Save_ViewASponsor")) {
                                            event.getViewASponsor().getFormUploadImg().submitUploadForm();
                                            event.getViewASponsor().getSponsor().setName(event.getViewASponsor().getNameText());
                                            event.getViewASponsor().getSponsor().setAdresse(event.getViewASponsor().getAdresseText());
                                            event.getViewASponsor().getSponsor().setUrlLogo(event.getViewASponsor().getImgText());
                                            if(event.getViewASponsor().getSponsor() instanceof SponsorArgent){
                                                ((SponsorArgent) event.getViewASponsor().getSponsor()).setArgent(Float.valueOf(event.getViewASponsor().getArgentText()));
                                            }else  if(event.getViewASponsor().getSponsor() instanceof SponsorLot){
                                                ((SponsorLot) event.getViewASponsor().getSponsor()).setListeLot(event.getViewASponsor().getLotsList());
                                            }
                                }
                                /**
                                 * Permet de changer la vue et d'afficher tous les sponsors
                                 */
                                else if(event.getAction().contains("VIEWALL_VIEWALL")){
                                        Racine.get().getviewAllS().switchDeckPanel(0);
                                }
                                /**
                                 * Permet de changer la vue pour afficher que les sponsors lots
                                 */
                                else if(event.getAction().contains("VIEWALL_VIEWLOTS")){
                                        Racine.get().getviewAllS().switchDeckPanel(1);
                                }
                                /**
                                 * Permet de changer la vue pour afficher que les sponsors argents
                                 */
                                else if(event.getAction().contains("VIEWALL_VIEWARGENTS")){
                                        Racine.get().getviewAllS().switchDeckPanel(2);
                                }
                                /**
                                 * Permet de changer la vue et d'afficher tous les sponsors
                                 */
                                else if(event.getAction().contains("VIEWGERE_VIEWALL")){
                                        Racine.get().getViewGereSponsor().switchDeckPanel(0);
                                }
                                /**
                                 * Permet de changer la vue pour afficher que les sponsors lots
                                 */
                                else if(event.getAction().contains("VIEWGERE_VIEWLOTS")){
                                        Racine.get().getViewGereSponsor().switchDeckPanel(1);
                                }
                                /**
                                 * Permet de changer la vue pour afficher que les sponsors lots
                                 */
                                else if(event.getAction().contains("VIEWGERE_VIEWARGENTS")){
                                        Racine.get().getViewGereSponsor().switchDeckPanel(2);
                                }
                                 /**
                                 * Permet d'afficher la popup pour rajouter un sponsor
                                 */
                                else if(event.getAction().contains("VIEWGERE_ADD_SPONSOR")){
                                        Racine.get().getViewGereSponsor().addSponsor();
                                } /**
                                 * Permet de changer la vue dans le popup pour rajouter le bon sponsor
                                 */
                                else if(event.getAction().contains("VIEWDOTATION_VIEWLOT")){
                                        Racine.get().getViewDotation().switchDeckPanel(0);
                                }else if(event.getAction().contains("VIEWDOTATION_VIEWARGENT")){
                                        Racine.get().getViewDotation().switchDeckPanel(1);
                                }
                                /**
                                 * BOUTONS DE LA POPUP (rajouter,sauvegarder etc
                                 */
                                else if(event.getAction().contains("AddSponsorPopup_CancelSponsor")){
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
                                }
                                /**
                                 * Met a jour la liste sur le serveur après avoir uploadé la nouvelle image
                                 */
                                else if(event.getAction().contains("UPLOADFORM_OK")){
                                    try{
                                        String s = event.getAction().split("=")[1];
                                        event.getViewASponsor().getSponsor().setUrlLogo(s);
                                        saveModel(myService);
                                    }catch(Exception e){System.err.println("Erreur Controlleur, UPLOADFORM_OK");}
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

    /**
     * Création de la liste des sponsors et init des vues
     * @param myService
     */
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

/**
 * Sauvegarde de la liste des sponsor sur le serveur puis update des vues
 * @param myService
 */
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
