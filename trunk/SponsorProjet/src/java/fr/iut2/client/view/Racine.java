/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.service.MyService;
import fr.iut2.client.service.MyServiceAsync;

/**
 *
 * @author sokarys
 */
public class Racine extends Composite{

        public static final NameField NAMEFIELD_CONSTANT = GWT.create(NameField.class);
        
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

	/*@UiField
	TextPanelTab textPanelTab;

	@UiField
	UsersPanel usersPanel;

	@UiField(provided=true)
	ActionsPanel actionsPanel;
*/
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

        private void init(){

                dockPanel = new DockPanel();
                dockPanel.setHeight(MAX_HEIGHT);
                dockPanel.setWidth(MAX_WIDTH);

                tabPanel = new TabPanel();
                tabPanel.setSize(MAX_WIDTH,MAX_HEIGHT);
                tabPanel.add(new ViewAccueil(), NAMEFIELD_CONSTANT.accueil());
                tabPanel.add(viewAllS, NAMEFIELD_CONSTANT.allSponsor());
                tabPanel.add(viewDotation, NAMEFIELD_CONSTANT.listDonSponsor());
                tabPanel.add(viewFlyers, NAMEFIELD_CONSTANT.flyers());
                tabPanel.add(viewGereSponsor,NAMEFIELD_CONSTANT.gererSponsor());
                tabPanel.selectTab(0);

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
        

}
