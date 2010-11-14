/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.model;

/**
 *
 * @author sokarys
 */
public class SponsorLot extends Sponsor {
    private ListeLot listeLot;


    public SponsorLot(){}

    
    public SponsorLot(String name, String adresse, String urlLogo, ListeLot listeLot) {
        super(name,adresse,urlLogo);
        this.listeLot = listeLot;
    }

    public String getLot(int index){
        if(this.listeLot != null){
            return this.listeLot.getLot(index);
        }
        return "";
    }

    public int getNombreDeLot(){
        if(this.listeLot != null){
            return this.listeLot.getNombreDeLot();
        }
        return 0;
    }
    
    public ListeLot getListLot(){
        return this.listeLot;
    }

    @Override
    public String toXML() {
        String xml = "";
        xml = "<sponsor class=\""+ this.getClass().getName()+"\">\n";
        xml += "<name>"+this.getName()+"</name>\n";
        xml += "<adresse>"+ this.getAdresse() + "</adresse>\n";
        xml += "<urllogo>" + this.getUrlLogo() + "</urllogo>\n";
        xml += this.getListLot().toXML();
        xml += "</sponsor>";
        return xml;
    }

    public void setListeLot(ListeLot listeLot) {
        this.listeLot = listeLot;
    }



}
