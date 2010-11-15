/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.allen_sauer.gwt.dnd.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.model.SponsorArgent;
import java.util.ArrayList;

/**
 *
 * @author sokarys
 */
public class ViewFlyers extends Composite{
    private ListSponsor list;
    private AbsolutePanel aPanel;
    //private HorizontalPanel hPanel;
    private PickupDragController dragController;
    private HandlerManager eventBus;
    private HorizontalPanel hPanel;
    private DecoratorPanel dPanel;
    private ArrayList<ViewAFlyer> viewaflyer;


    public ViewFlyers(HandlerManager eventBus){
        this.eventBus = eventBus;
        aPanel = new AbsolutePanel();
        dPanel = new DecoratorPanel();
        hPanel = new HorizontalPanel();
        hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        aPanel.addStyleDependentName("flyerAbsolutePanel");
        aPanel.setSize("600px", "400px");

        hPanel.add(dPanel);
        dPanel.add(aPanel);

        //hPanel.setSize("500px","500px");
        dragController = new PickupDragController(aPanel,true);
        viewaflyer = new  ArrayList<ViewAFlyer>();
        this.initWidget(hPanel);
    }

    public void setList(ListSponsor l){
        aPanel.clear();
        Image ig = new Image("images/luron.jpg");
        ig.setSize("200px", "200px");
        aPanel.add(ig,200,100);
     

        list = l;
        for(int i=0; i<list.getNbSponsor(); i++){
            if(list.getASponsor(i) instanceof SponsorArgent){
                Image img = new Image(list.getASponsor(i).getUrlLogo());
                aPanel.add(img);
                dragController.makeDraggable(img);

            }
        }
    }

    public void  updateViewFlyers(){
        //this.setList(list);
        for(ViewAFlyer v : viewaflyer){
            v.update();
        }
    }
}
