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

    
    @Override
    public ListSponsor getSponsor() {
       
        ArrayList<String> sponsor = new ArrayList<String>();
        try {
            //System.out.print("FILE FACTORY = " + FileFactory.getText("sponsor.xml"));
            String[] sponsorInFile = FileFactory.getText("sponsor.xml").split("\n"); //Transforme le xml en une ArrayList de string!
            //sponsor.addAll(Arrays.asList(sponsorInFile));
            System.out.println("TAILLE sponsorInFile = " + sponsorInFile.length);
            
            for(int i=0; i<sponsorInFile.length ; i++){
                sponsor.add(sponsorInFile[i]);
            }
            System.out.println("##############################");
            System.out.println("TAILLE sponso = " + sponsor.size());
            System.out.println("##############################");
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
                System.out.println(name + "  " + adresse + "  " + urlLogo + "  " + argent);
                if(classeName.contains("SponsorArgent")){
                    System.out.println("ADD SponsorArgent");
                    listSponsor.addSponsor(new SponsorArgent(name,adresse, urlLogo,Float.valueOf(argent)));
                }else if(classeName.contains("SponsorLot")){
                    System.out.println("ADD SponsorLot");
                     listSponsor.addSponsor(new SponsorLot(name,adresse,urlLogo,lots));
                }
            }
        }

        System.out.println(listSponsor.toString());
        System.out.print("NB SPONSOR = " + listSponsor.getNbSponsor());
        return listSponsor;
        
    }

    @Override
    public void setSponsor(ListSponsor list) {
        FileFactory.setText("sponsor.xml", list.toXML());
    }


    private ListSponsor initSponsor(ArrayList<String> list){
        ListSponsor listSponsor = new ListSponsor();
        String name = "", adresse = "", urlLogo = "", argent = "";
        ListeLot lots;
        String classeName = "";
        
        for(int i=0; i<list.size(); i++){
            if(list.get(i).contains("<sponsor")){
                classeName = list.get(i);
                lots = new ListeLot();
                while(!list.get(i).contains("</sponsor>")){
                    if(list.get(i).contains("<name>")){
                        name = list.get(i).replace("<name>", "").replace("</name>", "");
                    }
                    if(list.get(i).contains("<adresse>")){
                        adresse = list.get(i).replace("<adresse>", "").replace("</adresse>", "");
                    }
                    if(list.get(i).contains("<urllogo>")){
                        urlLogo = list.get(i).replace("<urllogo>", "").replace("</urllogo>", "");
                    }
                    if(list.get(i).contains("<argent>")){
                        argent = list.get(i).replace("<argent>", "").replace("</argent>", "");
                    }
                    if(list.get(i).contains("<lot>")){
                        lots.addLot(list.get(i).replace("<lot>", "").replace("</lot>", ""));
                    }
                }
                System.out.println(name + "  " + adresse + "  " + urlLogo);
                if(classeName.contains("SponsorArgent")){
                    System.out.println("ADD SponsorArgent");
                    listSponsor.addSponsor(new SponsorArgent(name,adresse,urlLogo,Float.valueOf(argent)));
                }else if(classeName.contains("SponsorLot")){
                    System.out.println("ADD SponsorLot");
                     listSponsor.addSponsor(new SponsorLot(name,adresse,urlLogo,lots));
                }
            }
        }

        //System.out.println(listSponsor.toString());
        //System.out.print("NB SPONSOR = " + listSponsor.getNbSponsor());
        return listSponsor;
    }

   

}
