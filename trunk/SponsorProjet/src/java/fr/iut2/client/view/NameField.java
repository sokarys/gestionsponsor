/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;


import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/**
 *
 * @author sokarys
 */
public interface NameField extends Constants {
  @DefaultStringValue("Accueil")
  String accueil();

  @DefaultStringValue("Rajouter un Sponsor")
  String addSponsor();

  @DefaultStringValue("Modifier le Sponsor")
  String modifySponsor();

  @DefaultStringValue("Tous les Sponsors")
  String allSponsor();

  @DefaultStringValue("Gérer les Sponsors")
  String gererSponsor();

@DefaultStringValue("Flyers")
String flyers();

@DefaultStringValue("Actions")
String actions();

@DefaultStringValue("Supprimer un Sponsor")
String dellSponsor();
        
@DefaultStringValue("Les Donnations")
String listDonSponsor();

@DefaultStringValue("Ajouter")
String ajouter();

@DefaultStringValue("Les Joyeux Lurons")
String titreAccueil();

@DefaultStringValue("Gestion des Sponsors")
String contenuHeader();

@DefaultStringValue("Créé par Olivier Locci")
String contenuFooter();

@DefaultStringValue("Nom")
String ViewASponsorName();

@DefaultStringValue("Adresse")
String ViewASponsorAdresse();

@DefaultStringValue("Lot")
String ViewASponsorLot();

@DefaultStringValue("Argent")
String ViewASponsorArgent();

@DefaultStringValue("viewAll")
String viewAll();

@DefaultStringValue("viewArgent")
String viewArgent();

@DefaultStringValue("viewLot")
String viewLot();

@DefaultStringValue("Liste des dons argents")
String viewDotationArgent();

@DefaultStringValue("Liste des dons lots")
String viewDotationLots();


    @DefaultStringValue("Rajouter un Sponsor Lot")
    String AddSponsorPopup_addLot();
    
    @DefaultStringValue("Rajouter un Sponsor Argent")
    String AddSponsorPopup_addArgent();

    @DefaultStringValue("Nom")
    String AddSponsorPopup_name();

    @DefaultStringValue("adresse")
    String AddSponsorPopup_adresse();

    @DefaultStringValue("argent")
    String AddSponsorPopup_argent();

    @DefaultStringValue("Rajouter")
    String AddSponsorPopup_addsponsor();

    @DefaultStringValue("Nouveau lot")
    String AddSponsorPopup_addLotList();

     @DefaultStringValue("Supprimmer les lots vides")
     String AddSponsorPopup_removeEmpty();

    @DefaultStringValue("Annuler")
    String AddSponsorPopup_cancel();

    @DefaultStringValue("Rajouter un Sponsor")
    String ViewGereSponsor_addSponsor();



}
