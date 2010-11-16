/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabListener;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.service.MyService;
import fr.iut2.client.service.MyServiceAsync;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sokarys
 */
@SuppressWarnings({"deprecation", "deprecation", "deprecation", "deprecation"})
public class Racine extends Composite implements ValueChangeHandler<String>{

        public static final NameField NAMEFIELD_CONSTANT = GWT.create(NameField.class);

        protected HashMap<Integer, String> mapHistory =  new HashMap<Integer, String>();
       
     /*   private static RacineUiBinder uiBinder = GWT.create(RacineUiBinder.class);

	interface RacineUiBinder extends UiBinder<Widget, Racine> {
	}
*/
	// DATA
	private static Racine SINGLETON;
        private static final String MAX_HEIGHT = "100%";
        private static final String MAX_WIDTH = "100%";

        private TabPanel tabPanel;
        private DockPanel dockPanel;

        private ViewAllSponsor viewAllS;
        private ViewDotation viewDotation;
        private ViewFlyers viewFlyers;
        private ViewGereSponsor viewGereSponsor;
      //  private ViewUploadFile viewUploadFile;

        private HandlerManager eventBus;

	public Racine(HandlerManager eventBus) {
                this.eventBus = eventBus;
                
		//actionsPanel = new ActionsPanel(eventBus);

//		initWidget(uiBinder.createAndBindUi(this));

		SINGLETON = this;

                
                viewAllS = new ViewAllSponsor(eventBus);
                viewDotation = new ViewDotation(eventBus);
                viewFlyers = new ViewFlyers(eventBus);
                viewGereSponsor = new ViewGereSponsor(eventBus);
                //viewUploadFile = new ViewUploadFile(eventBus);

                init();
                
	}

        /**
         * Initialisation du panel principal
         */
        private void init(){

                dockPanel = new DockPanel();
                dockPanel.setHeight(MAX_HEIGHT);
                dockPanel.setWidth(MAX_WIDTH);
                dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
                dockPanel.setSpacing(10);
                tabPanel = new TabPanel();
                tabPanel.setSize("100%", "600px");
                tabPanel.add(new ViewAccueil(), NAMEFIELD_CONSTANT.accueil());
                tabPanel.add(viewAllS, NAMEFIELD_CONSTANT.allSponsor());
                tabPanel.add(viewDotation, NAMEFIELD_CONSTANT.listDonSponsor());
                tabPanel.add(viewFlyers, NAMEFIELD_CONSTANT.flyers());
                tabPanel.add(viewGereSponsor,NAMEFIELD_CONSTANT.gererSponsor());
                tabPanel.selectTab(0);

                
                 for (int i=0; i<tabPanel.getWidgetCount(); i++){
			mapHistory.put(i, tabPanel.getWidget(i).getTitle());
		}

                
		historyInit();
		setHandler();

                dockPanel.add(new Header(), DockPanel.NORTH);
                dockPanel.add(tabPanel, DockPanel.CENTER);
                dockPanel.add(new Footer(), DockPanel.SOUTH);
                this.initWidget(dockPanel);
		// Ajout du gestionnaire d'agencement au panneau principal
		RootPanel.get().add(this);
            
        }

	public static Racine get() {
		return SINGLETON;
	}

	public void go(HasWidgets container) {
		container.clear();
		container.add(this);
	}


       public ViewAllSponsor getviewAllS(){
            return viewAllS;
       }

        public ViewDotation getViewDotation() {
            return viewDotation;
       }

        public ViewFlyers getViewFlyers(){
            return viewFlyers;
        }

        public ViewGereSponsor getViewGereSponsor(){
            return this.viewGereSponsor;
        }


        public HandlerManager getEventBus(){
            return this.eventBus;
        }

      protected void historyInit() {
		//ajout du support de l'historique
		History.addValueChangeHandler(this);

		//on regarde l'historique de départ
		String token = History.getToken();
		if (token.length() == 0){
			//si pas d'historique
			tabPanel.selectTab(0);//selection par défaut
			//mise à jour de ma gestion de l'historique avec la sélection par défaut
			History.newItem(mapHistory.get(0));
		}else{
			//il y a un historique
			//selection javascript de l'onglet correspondant
			tabPanel.selectTab(getIndex(History.getToken()));
		}
	}

	/**
	 * Ajoute un handler au tabPanel pour la gestion du token de l'historique
	 */
	protected void setHandler() {
		 //pour gérer l'historique lors d'un changement d'onglet
		tabPanel.addSelectionHandler(new SelectionHandler<Integer>(){
                       @Override
			public void onSelection(SelectionEvent<Integer> event) {
				History.newItem(mapHistory.get(event.getSelectedItem()));
			}
		});
	}

        protected int getIndex(String historyToken) {
		for(int i : mapHistory.keySet()){
			if (mapHistory.get(i).equals(historyToken))
				return i;
		}
		return -1;
	}

    

        /**
	 * implémentation de l'interface ValueChangeHandler<String>
	 */
        @Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String historyToken = event.getValue();
		//sélection javascript au cas où l'adresse a été entrée "à la main"
		tabPanel.selectTab(getIndex(historyToken));//sélection du nouveau tab
	}
        

}
