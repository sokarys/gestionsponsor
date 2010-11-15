/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.model;

/**
 *
 * @author sokarys
 */
public class SponsorArgent extends Sponsor {
    private float argent;

    public SponsorArgent(){}
    
    public SponsorArgent(String name, String adresse, String urlLogo, float argent) {
        super(name,adresse,urlLogo);
        this.argent = new Float(argent);
    }
    
    public float getArgent() {
        return argent;
    }

    /**
     * 
     * @return la version xml du sponsor
     */
    @Override
    public String toXML() {
        String xml = "";
        xml = "<sponsor class=\""+ this.getClass().getName()+"\">\n";
        xml += "<name>"+this.getName()+"</name>\n";
        xml += "<adresse>"+ this.getAdresse() + "</adresse>\n";
        xml += "<urllogo>" + this.getUrlLogo() + "</urllogo>\n";
        xml += "<argent>" + this.getArgent() + "</argent>\n";
        xml += "</sponsor>";
        return xml;
    }

    public void setArgent(float argent) {
        this.argent = argent;
    }
    
}
