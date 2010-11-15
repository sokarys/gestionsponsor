package fr.iut2.client.event;
 

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import fr.iut2.client.view.ViewASponsor;

public class ActionEvent extends GwtEvent<ActionEventHandler> {
	public static Type<ActionEventHandler> TYPE = new Type<ActionEventHandler>();

	private String action;
        private ViewASponsor sponsor;

        /**
         * Construcuteur
         * @param action
         */
	public ActionEvent(String action) {
		super();
		this.action = action;
                this.sponsor = null;
	}

        /**
         * Constructeur avec un ViewASponsor pour pouvoir modifier ou récup les donnée d'un sponsor
         * @param action
         * @param sponsor
         */
        public ActionEvent(String action,ViewASponsor sponsor) {
		super();
		this.action = action;
                this.sponsor = sponsor;
	}
	
	@Override
	public Type<ActionEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ActionEventHandler handler) {
		handler.onActionEvent(this);
	}

	public String getAction() {
		return action;
	}

        public ViewASponsor getViewASponsor(){
            return sponsor;
        }
}
