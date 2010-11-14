/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.iut2.client.model.ListSponsor;
import java.util.ArrayList;

/**
 *
 * @author sokarys
 */
@RemoteServiceRelativePath("myservice")
public interface MyService extends RemoteService{
    public ListSponsor getSponsor();
    public void setSponsor(ListSponsor list);
}
