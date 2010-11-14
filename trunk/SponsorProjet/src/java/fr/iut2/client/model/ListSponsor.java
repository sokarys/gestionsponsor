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
public class ListSponsor implements IsSerializable{
    private ArrayList<Sponsor> listSponsor;

    public ListSponsor(){
        listSponsor = new ArrayList<Sponsor>();
    }

    public int getNbSponsor(){
        return listSponsor.size();
    }

    public int getNbSponsorArgent(){
        int nb =0;
        for(int i=0; i<listSponsor.size(); i++){
            if(listSponsor.get(i) instanceof SponsorArgent){
                nb++;
            }
        }
        return nb;
    }

    public ArrayList<Sponsor> getAllSponsorArgent(){
        ArrayList<Sponsor> l = new ArrayList<Sponsor>();
        for(int i=0; i<listSponsor.size(); i++){
            if(listSponsor.get(i) instanceof SponsorArgent){
                l.add(listSponsor.get(i));
            }
        }
        return l;
    }


    public int getNbSponsorLot(){
        int nb =0;
        for(int i=0; i<listSponsor.size(); i++){
            if(listSponsor.get(i) instanceof SponsorLot){
                nb++;
            }
        }
        return nb;
    }


    public ArrayList<Sponsor> getAllSponsorLot(){
        ArrayList<Sponsor> l = new ArrayList<Sponsor>();
        for(int i=0; i<listSponsor.size(); i++){
            if(listSponsor.get(i) instanceof SponsorLot){
                l.add(listSponsor.get(i));
            }
        }
        return l;
    }

    public Sponsor getASponsor(int index){
        return listSponsor.get(index);
    }


    public void addSponsor(Sponsor s){
        listSponsor.add(s);
    }



    
    public ArrayList<Sponsor> getListSponsor() {
        return listSponsor;
    }

    public void setListSponsor(ArrayList<Sponsor> listSponsor) {
        this.listSponsor = listSponsor;
    }


    public String toXML(){
        String str = "";
        for(Sponsor s : listSponsor){
            str += s.toXML() + "\n";
        }
        return str;
    }

    @Override
    public String toString() {
        return "ListSponsor{" + "listSponsor=" + listSponsor.size() + '}';
    }

    public void removeSponsor(Sponsor s){
        this.listSponsor.remove(s);
    }

    

}
