/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.iut2.client.event.ActionEvent;
import fr.iut2.client.model.*;
import java.util.ArrayList;

/**
 *
 * @author sokarys
 */
public class ViewASponsor extends Composite{
    public static enum STATE {MODIFY, READ};
    protected final String SIZE_LOGO = "50px";
    protected TextArea name,adresse,argent,image;
    protected Image logoImg;
    protected ArrayList<TextArea> lot;
    protected DisclosurePanel content;
    protected Button modifyGo,save,delete;
    private ViewUploadFile formUploadImg;
    protected Sponsor sponsor;
    protected HandlerManager eventBus;
    protected STATE state;

    public ViewASponsor(HandlerManager eventBus,Sponsor sponsor, boolean isOpen, boolean modifyMode){
            this(eventBus,sponsor,modifyMode);
            content.setOpen(isOpen);
    }

    public ViewASponsor(HandlerManager eventBus,Sponsor sponsor,boolean modifyMode){
        this.sponsor = sponsor;
        this.eventBus = eventBus;
        
        VerticalPanel flex = new VerticalPanel();
        content = new DisclosurePanel();
        content.setAnimationEnabled(true);

        name = new TextArea();
        name.setVisibleLines(1);
        name.setText(sponsor.getName());
        content.setHeader(name);


        adresse = new TextArea();
        adresse.setText(sponsor.getAdresse());
        adresse.setVisibleLines(2);
        flex.add(adresse);

        image = new TextArea();
        image.setText(sponsor.getUrlLogo());
        image.setVisibleLines(1);
        image.setVisible(false);
        flex.add(image);
        formUploadImg = new ViewUploadFile(eventBus,this);
        flex.add(formUploadImg);
        formUploadImg.setVisible(false);

        if(sponsor.getUrlLogo() != null){
            if(!sponsor.getUrlLogo().isEmpty()){
                logoImg = new Image(sponsor.getUrlLogo());
                logoImg.setWidth(SIZE_LOGO);
                logoImg.setHeight(SIZE_LOGO);
                flex.add(logoImg);
            }
        }

       
        
        
         if(sponsor instanceof SponsorArgent){
             SponsorArgent slot = (SponsorArgent) sponsor;
             argent = new TextArea();
             argent.setVisibleLines(1);
             argent.setText(String.valueOf(new Float(slot.getArgent())));
             flex.add(new Label("Argents : "));
             flex.add(argent);
         }else if(sponsor instanceof SponsorLot){
             flex.add(new Label("Lots : "));
              SponsorLot slot = (SponsorLot) sponsor;
                lot = new ArrayList<TextArea>();
              for(int i=0; i<slot.getNombreDeLot(); i++){
                  
                  lot.add(new TextArea());
                  lot.get(i).setText(slot.getLot(i));
                  lot.get(i).setVisibleLines(1);
                  flex.add(lot.get(i));
              }

        }

        modifyGo = new Button("Modify");
        
        
        save  = new Button("Save");
        delete= new Button("Delete");
        
        this.changeModifyStatus(modifyMode);

        flex.add(save);
        flex.add(delete);
        flex.add(modifyGo);
  
        content.setContent(flex);
        
        this.toWatch();       
        this.initWidget(content);

        modifyGo.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                ViewASponsor.this.eventBus.fireEvent(new ActionEvent("Modify_ViewASponsor",ViewASponsor.this));
            }
        });

        save.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                 ViewASponsor.this.eventBus.fireEvent(new ActionEvent("Save_ViewASponsor",ViewASponsor.this));
            }
        });

         delete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                 ViewASponsor.this.eventBus.fireEvent(new ActionEvent("Delete_ViewASponsor",ViewASponsor.this));
            }
        });

        image.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                ViewASponsor.this.eventBus.fireEvent(new ActionEvent("Zoom_ViewASponsor",ViewASponsor.this));
            }
        });

     
    }

    public void toModify(){
        image.setVisible(true);
        if(logoImg!=null){
            logoImg.setVisible(false);
        }
        modifyGo.setText("Read");
        save.setVisible(true);
        delete.setVisible(true);
        formUploadImg.setVisible(true);
        name.setReadOnly(false);
        image.setReadOnly(true);
        adresse.setReadOnly(false);

        if(sponsor instanceof SponsorLot){
            for(TextArea t : lot){
                t.setReadOnly(false);
            }
        }else if(sponsor instanceof SponsorArgent){
            argent.setReadOnly(false);
        }

        state = STATE.MODIFY;
    }


    public void updateContent(){
        name.setText(sponsor.getName());
        adresse.setText(sponsor.getAdresse());
        image.setText(sponsor.getUrlLogo());
        
         if(sponsor instanceof SponsorArgent){
             SponsorArgent slot = (SponsorArgent) sponsor;
             argent.setText(String.valueOf(new Float(slot.getArgent())));
         }else if(sponsor instanceof SponsorLot){
              SponsorLot slot = (SponsorLot) sponsor;
                lot.clear();
              for(int i=0; i<slot.getNombreDeLot(); i++){
                  lot.add(new TextArea());
                  lot.get(i).setText(slot.getLot(i));
              }
         }
    }

    public void toWatch(){
        image.setVisible(false);
        if(logoImg!=null){
            logoImg.setVisible(true);
        }
        modifyGo.setText("Modify");
        save.setVisible(false);
        delete.setVisible(false);
        formUploadImg.setVisible(false);
        name.setReadOnly(true);
        image.setReadOnly(true);
        adresse.setReadOnly(true);
        
        if(sponsor instanceof SponsorLot){
            for(TextArea t : lot){
                t.setReadOnly(true);
            }
        }else if(sponsor instanceof SponsorArgent){
            argent.setReadOnly(true);
        }
        state = STATE.READ;
    }

    public STATE getState() {
        return state;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public String getNameText(){
        return name.getText();
    }

    public String getAdresseText(){
        return adresse.getText();
    }
    
    public String getImgText(){
        return image.getText();
    }

    public String getArgentText(){
        return argent.getText();
    }

    public ListeLot getLotsList(){
        ListeLot l = new ListeLot();
        for(TextArea s : lot){
            l.addLot(s.getText());
        }
        return l;
    }

    public void changeModifyStatus(boolean b){
        this.modifyGo.setVisible(b);
    }

    public ViewUploadFile getFormUploadImg() {
        return formUploadImg;
    }

    
}
