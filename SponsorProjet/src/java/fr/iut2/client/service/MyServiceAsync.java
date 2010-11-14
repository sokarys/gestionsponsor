/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.iut2.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import fr.iut2.client.model.ListSponsor;
import java.util.ArrayList;

/**
 *
 * @author sokarys
 */
public interface MyServiceAsync {

    public void getSponsor(AsyncCallback<ListSponsor> asyncCallback);

    public void setSponsor(ListSponsor list, AsyncCallback<Void> asyncCallback);

}
