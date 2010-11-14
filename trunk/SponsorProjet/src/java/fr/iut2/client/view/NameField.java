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
}
