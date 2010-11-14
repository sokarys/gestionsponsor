/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import fr.iut2.client.controller.Controleur;
import fr.iut2.client.service.MyService;
import fr.iut2.client.service.MyServiceAsync;

/**
 * Main entry point.
 *
 * @author sokarys
 */
public class sponsorprojetEntryPoint implements EntryPoint {
    /** 
     * Creates a new instance of sponsorprojetEntryPoint
     */
    public sponsorprojetEntryPoint() {
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
		MyServiceAsync analyseurService = GWT.create(MyService.class);
		HandlerManager eventBus = new HandlerManager(null);
                Controleur appViewer = new Controleur(analyseurService, eventBus);
                appViewer.go(RootLayoutPanel.get());
	}
}
