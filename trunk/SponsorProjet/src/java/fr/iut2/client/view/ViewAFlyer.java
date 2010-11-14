/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import fr.iut2.client.model.Sponsor;

/**
 *
 * @author sokarys
 */
public class ViewAFlyer extends Composite{
    private Image imgLogo;
    private Sponsor sponsor;
    private HandlerManager eventBus;

    public ViewAFlyer(HandlerManager eventBus, Sponsor sponsor) {
       this.sponsor = sponsor;
       this.eventBus = eventBus;
       imgLogo = new Image(sponsor.getUrlLogo());
       this.initWidget(imgLogo);
    }

    public void update(){
        imgLogo = new Image(sponsor.getUrlLogo());
    }

}
