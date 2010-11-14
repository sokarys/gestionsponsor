/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

/**
 *
 * @author sokarys
 */

import com.google.gwt.user.client.ui.Image;
 import com.google.gwt.user.client.ui.PopupPanel;
 

 public class ImagePopupAnimate extends PopupPanel {

     public ImagePopupAnimate(String img) {
         super(true);
         this.setAnimationEnabled(true);
         Image image = new Image(img);
         image.setWidth("100%");
         image.setHeight("100%");
         this.add(image);
         this.center();
         this.show();
         
     }

 }
