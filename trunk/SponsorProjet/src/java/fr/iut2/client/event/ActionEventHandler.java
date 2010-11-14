package fr.iut2.client.event;

import com.google.gwt.event.shared.EventHandler;
import fr.iut2.client.view.ViewASponsor;

public interface ActionEventHandler extends EventHandler {
  void onActionEvent(ActionEvent event);
}
