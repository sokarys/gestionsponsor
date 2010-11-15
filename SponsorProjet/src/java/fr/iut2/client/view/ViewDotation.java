/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;




import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.iut2.client.event.ActionEvent;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.model.SponsorArgent;
import fr.iut2.client.model.SponsorLot;
import java.util.ArrayList;

/**
 * Vue pour voir les dons recus
 * @author sokarys
 */
public class ViewDotation extends Composite{
    private ListSponsor list;
    
    private ListBox lbLot = new ListBox();
    private ListBox lbArgent = new ListBox();
    private DeckPanel dPanel = new DeckPanel(); //Principal
    private DeckPanel dPanelLot = new DeckPanel();
    private DeckPanel dPanelArgent = new DeckPanel();
    private HandlerManager eventBus;
    private ArrayList<ViewASponsor> viewasponsorLot = new ArrayList<ViewASponsor>();
    private ArrayList<ViewASponsor> viewasponsorArgent = new ArrayList<ViewASponsor>();
    private float totalArgent;
    
    private DockPanel panelLot = new DockPanel(),
                      panelArgent = new DockPanel();

    private Button viewDon  = new Button(Racine.NAMEFIELD_CONSTANT.viewDotationLots()),
               viewArgent = new Button(Racine.NAMEFIELD_CONSTANT.viewDotationArgent());




   
/**
 * Constructeur
 * @param eventBus
 */
    public ViewDotation(final HandlerManager eventBus){
        this.eventBus = eventBus;
        HorizontalPanel menu = new HorizontalPanel();
        VerticalPanel panel = new VerticalPanel();
        

        menu.add(viewDon);
        menu.add(viewArgent);

        lbLot.setVisibleItemCount(15);
        lbArgent.setVisibleItemCount(15);
        
        dPanel.add(panelLot);
        dPanel.add(panelArgent);

        panel.add(menu);
        panel.add(dPanel);

     
        
        this.initWidget(panel);

/**
 * Event
 */
         viewDon.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWDOTATION_VIEWLOT"));
            }
        });

        viewArgent.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new ActionEvent("VIEWDOTATION_VIEWARGENT"));
            }
        });

        lbLot.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                eventBus.fireEvent(new ActionEvent("CHANGE_LIST_STATUS_LOT"));
            }
        });

        lbArgent.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                eventBus.fireEvent(new ActionEvent("CHANGE_LIST_STATUS_ARGENT"));
            }
        });
    }

    /**
     * Fonction de mise a jour de la vue
     * @param l
     */
     public void setList(ListSponsor l){
        panelLot.clear();
        panelArgent.clear();
        viewasponsorLot.clear();
        viewasponsorArgent.clear();
        totalArgent = 0;
        dPanelLot.clear();
        lbLot.clear();
        dPanelArgent.clear();
        lbArgent.clear();


         list = l;
        //lot
        for(int i=0; i<list.getNbSponsor(); i++){
                if(list.getASponsor(i) instanceof SponsorLot){
                    for(int j=0; j<((SponsorLot)list.getASponsor(i)).getNombreDeLot(); j++){
                        lbLot.addItem(( ((SponsorLot)list.getASponsor(i)).getLot(j)));
                        viewasponsorLot.add(new ViewASponsor(eventBus,list.getASponsor(i),true,false));
                        dPanelLot.add(viewasponsorLot.get(viewasponsorLot.size()-1));
                    }

                }else if(list.getASponsor(i) instanceof SponsorArgent){
                    lbArgent.addItem(String.valueOf(((SponsorArgent) list.getASponsor(i)).getArgent()));
                    totalArgent += ((SponsorArgent) list.getASponsor(i)).getArgent();
                    viewasponsorArgent.add(new ViewASponsor(eventBus,list.getASponsor(i),true,false));
                    dPanelArgent.add(viewasponsorArgent.get(viewasponsorArgent.size()-1));
                }
        }
        
        
        
        panelLot.add(lbLot, DockPanel.WEST);
        panelLot.add(dPanelLot, DockPanel.CENTER);

        panelArgent.add(new Label("Total : " + String.valueOf(totalArgent)),DockPanel.NORTH);
        panelArgent.add(lbArgent, DockPanel.WEST);
        panelArgent.add(dPanelArgent, DockPanel.CENTER);

        switchDeckPanel(0);
        switchLigneDon(0);
        switchLigneArgent(0);
     }

    public DeckPanel getdPanelLot() {
        return dPanelLot;
    }

    public ListBox getLbLot() {
        return lbLot;
    }

    public DeckPanel getdPanelArgent() {
        return dPanelArgent;
    }

    public ListBox getLbArgent() {
        return lbArgent;
    }


/**
 * Permet de changer le deckpanel d'une liste
 * @param index
 */
     public void switchDeckPanel(int index){
        if(dPanel != null){
            dPanel.showWidget(index);
            if(index == 0){
                viewDon.setEnabled(false);
                viewArgent.setEnabled(true);
            }else if(index == 1){
                viewDon.setEnabled(true);
                viewArgent.setEnabled(false);
            }
        }
    }

     public void switchLigne(int index){
         if(dPanel.getVisibleWidget()==0){
             switchLigneDon(index);
         }else if(dPanel.getVisibleWidget()==1){
             switchLigneArgent(index);
         }
     }

     public void switchLigneDon(int index){
         if(index < lbLot.getItemCount()){
             lbLot.setSelectedIndex(index);
             dPanelLot.showWidget(index);
         }
     }

     public void switchLigneArgent(int index){
         if(index < lbArgent.getItemCount()){
             lbArgent.setSelectedIndex(index);
             dPanelArgent.showWidget(index);
         }
     }

     /**
      * Permet de savoir quel est le panel qui est montré à l'utilisateur
      * @return int visible panel
      */
     public int getDPanelShow(){
         return dPanel.getVisibleWidget();
     }

     /**
      * Utilisé dans l'historique
      * @return title
      */
    @Override
    public String getTitle() {
        return "ViewDotation";
    }

}
