/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.model.SponsorArgent;
import java.util.ArrayList;

/**
 *
 * @author sokarys
 */
public class ViewFlyers extends Composite{
    private ListSponsor list;
    private HorizontalPanel hPanel;
    private HandlerManager eventBus;
    private ArrayList<ViewAFlyer> viewaflyer;

    public ViewFlyers(HandlerManager eventBus){
        this.eventBus = eventBus;
        hPanel = new HorizontalPanel();
        viewaflyer = new  ArrayList<ViewAFlyer>();
        this.initWidget(hPanel);
    }

    public void setList(ListSponsor l){
        hPanel.clear();
        list = l;
        for(int i=0; i<list.getNbSponsor(); i++){
            if(list.getASponsor(i) instanceof SponsorArgent){
                hPanel.add(new ViewAFlyer(eventBus,list.getASponsor(i)));
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
