/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;

/**
 *
 * @author sokarys
 */
public class ListeLot implements IsSerializable {
    private ArrayList<String> listLot;
    
    public ListeLot(){
        listLot = new ArrayList<String>();
    }

    public ListeLot(ArrayList<String> listLot){
        this.listLot = listLot;
    }

    public void addLot(String lot){
        listLot.add(lot);
    }

    public String getLot(int index){
        return listLot.get(index);
    }

    public ArrayList<String> getListLot() {
        return listLot;
    }

    public void setListLot(ArrayList<String> listLot) {
        this.listLot = listLot;
    }

    public int getNombreDeLot(){
        return this.listLot.size();
    }

    public String toXML(){
        String xml ="";
        for(int i=0; i<this.getNombreDeLot(); i++){
            xml += "<lot>"+this.getLot(i)+"</lot>\n";
        }
        return xml;
    }



    


}
