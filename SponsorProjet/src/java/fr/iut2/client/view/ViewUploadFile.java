package fr.iut2.client.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import fr.iut2.client.event.ActionEvent;
import fr.iut2.client.model.Sponsor;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewUploadFile extends Composite {
	
	// WIDGETS
	VerticalPanel panel = new VerticalPanel();
	FormPanel uploadForm = new FormPanel();
	FileUpload upload = new FileUpload();
	//HTML messageServeur = new HTML();
        private ViewASponsor sponsor;
        private TextArea image;
        private boolean complete;
	
	public ViewUploadFile(final HandlerManager eventBus, ViewASponsor s) {
		sponsor = s;
                complete = false;
                this.image = image;
		// Associé un service au formulaire
		uploadForm.setAction(GWT.getModuleBaseURL() + "UploadFileServlet");//");
		
		// Pour ajouter un FileUpload widget, nous devons ajouter au formulaire
		// une méthode d'envoie POST, et un encodage multipart MIME.
		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);
		
		// Ajout du panel au formulaire
		uploadForm.setWidget(panel);
		
		// Propriétés du FileUpload widget.
		upload.setName("uploadFormElement");
		panel.add(upload);
		
		// Ajout du label message serveur
		//panel.add(messageServeur);
		
		// Ajout d'un bouton 'Submit'
		//Button uploadSubmitButton = new Button("Envoyer sur le serveur");
		//panel.add(uploadSubmitButton);
		
		/*uploadSubmitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				uploadForm.submit();
                                eventBus.fireEvent(new ActionEvent("UPLOADFORM_SUBMIT"));
			}
		});*/
		
		uploadForm.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			public void onSubmitComplete(SubmitCompleteEvent event) {
				//messageServeur.setHTML(event.getResults()); //getRésult == nOm??
                               // ViewUploadFile.this.sponsor.setUrlLogo(event.getResults().replaceAll("<pre>", "").replaceAll("</pre>", ""));
                               // ViewUploadFile.this.image.setText(event.getResults().replaceAll("<pre>", "").replaceAll("</pre>", ""));
                                eventBus.fireEvent(new ActionEvent("UPLOADFORM_OK=" + event.getResults().replaceAll("<pre>", "").replaceAll("</pre>", ""),sponsor));
			}
		});
		
		//
		this.initWidget(uploadForm);
	}

        public void submitUploadForm(){
           // if(!upload.getFilename().isEmpty()){
            //    complete =false;
                this.uploadForm.submit();
            //}
        }
        public boolean isComplete(){
            return complete;
        }

}