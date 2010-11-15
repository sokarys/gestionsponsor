package fr.iut2.server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import fr.iut2.client.model.ListSponsor;
import fr.iut2.client.model.ListeLot;
import fr.iut2.client.model.SponsorArgent;
import fr.iut2.client.model.SponsorLot;
import fr.iut2.client.service.MyService;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sokarys
 */

public class MyServiceImpl extends RemoteServiceServlet implements MyService {
    private static final long serialVersionUID = 1L;


    /**
     * Fonction pour récupérer la liste des sponsors! Lit et parsse la liste pour en créer une a donné au vue
     * @return
     */
    @Override
    public ListSponsor getSponsor() {
       
        ArrayList<String> sponsor = new ArrayList<String>();
        try {
            //System.out.print("FILE FACTORY = " + FileFactory.getText("sponsor.xml"));
            String[] sponsorInFile = FileFactory.getText("sponsor.xml").split("\n"); //Transforme le xml en une ArrayList de string!
            //sponsor.addAll(Arrays.asList(sponsorInFile));
            
            for(int i=0; i<sponsorInFile.length ; i++){
                sponsor.add(sponsorInFile[i]);
            }
  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

         ListSponsor listSponsor = new ListSponsor();
        String name = "", adresse = "", urlLogo = "", argent = "";
        ListeLot lots;
        String classeName = "";

        for(int i=0; i<sponsor.size(); i++){
            if(sponsor.get(i).contains("<sponsor")){
                classeName = sponsor.get(i);
                name = ""; adresse = ""; urlLogo = ""; argent = "";
                lots = new ListeLot();
                while(!sponsor.get(i).contains("</sponsor>")){
                    if(sponsor.get(i).contains("<name>")){
                        name = sponsor.get(i).replace("<name>", "").replace("</name>", "");
                    }
                    if(sponsor.get(i).contains("<adresse>")){
                        adresse = sponsor.get(i).replace("<adresse>", "").replace("</adresse>", "");
                    }
                    if(sponsor.get(i).contains("<urllogo>")){
                        urlLogo = sponsor.get(i).replace("<urllogo>", "").replace("</urllogo>", "");
                    }
                    if(sponsor.get(i).contains("<argent>")){
                        argent = sponsor.get(i).replace("<argent>", "").replace("</argent>", "").replaceAll(",", ".");
                    }
                    if(sponsor.get(i).contains("<lot>")){
                        lots.addLot(sponsor.get(i).replace("<lot>", "").replace("</lot>", ""));
                    }
                    i++;
                }
                if(classeName.contains("SponsorArgent")){
                    listSponsor.addSponsor(new SponsorArgent(name,adresse, urlLogo,Float.valueOf(argent)));
                }else if(classeName.contains("SponsorLot")){
                     listSponsor.addSponsor(new SponsorLot(name,adresse,urlLogo,lots));
                }
            }
        }
        return listSponsor;
        
    }

    /**
     * Sauvegarde la liste dans le fichier, pour ne pas perdre les modifs des sponsors
     * @param list
     */
    @Override
    public void setSponsor(ListSponsor list) {
        FileFactory.setText("sponsor.xml", list.toXML());
    }


}
