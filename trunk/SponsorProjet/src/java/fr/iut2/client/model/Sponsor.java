/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author sokarys
 */
public abstract class Sponsor implements IsSerializable{
    
    private String name;
    private String adresse;
    private String urlLogo;

    public Sponsor(){
        
    }

    public Sponsor(String name, String adresse, String urlLogo) {
        this.name = name;
        this.adresse = adresse;
        this.urlLogo = urlLogo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        if(!urlLogo.equals("null")){
            this.urlLogo = urlLogo;
        }
    }

    public abstract String toXML();

    
    @Override
    public String toString() {
        return "Sponsor{" + "name=" + name + "adresse=" + adresse + "urlLogo=" + urlLogo + '}';
    }


    

}
