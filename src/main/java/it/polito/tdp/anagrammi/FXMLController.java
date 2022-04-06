package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	
	@FXML
	private Label txtTempo;
	 
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnagrammiCorretti"
    private TextArea txtAnagrammiCorretti; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnagrammiErrati"
    private TextArea txtAnagrammiErrati; // Value injected by FXMLLoader

    @FXML // fx:id="txtDaAnagrammare"
    private TextField txtDaAnagrammare; // Value injected by FXMLLoader

    @FXML
    void handleAnagramma(ActionEvent event) {
    	String daAnagrammare = txtDaAnagrammare.getText();
    	txtAnagrammiCorretti.clear();
    	txtAnagrammiErrati.clear();
    	
    	if(daAnagrammare.equals("")) {
    		txtAnagrammiCorretti.setText("Per favore inserire una stringa");
    		return;
    	}
    	
    	//INSERIRE POSSIBILE CONTROLLO SU CARATTERI NUMERICI O PARTICOLARI
    	/*
    	String prova = daAnagrammare.replaceAll("[^\\\\d]", "");
    	
    	
    	if(!daAnagrammare.equals(prova)) {
    		txtAnagrammiCorretti.setText("Per favore inserire una stringa con solo lettere");
    		return;
    	}
    	*/
    	
    	//CREIAMO DUE VERSIONI, UNA CON dizionario.contains(anagramma) E UNA CON QUERY
    	long inizio = System.currentTimeMillis();
    	
    	
    	//VERSIONE 1
    	for(String s : this.model.anagramma(daAnagrammare)) {
    		if(this.model.getDizionario().contains(s)) {
    			txtAnagrammiCorretti.appendText(s+"\n");
    		} else {
    			txtAnagrammiErrati.appendText(s+"\n");
    		}
    	}
    	
    	
    	/*
    	//VERSIONE 2
    	for(String s : this.model.anagramma(daAnagrammare)) {
    		if(this.model.isCorrect(s)) {
    			txtAnagrammiCorretti.appendText(s+"\n");
    		} else {
    			txtAnagrammiErrati.appendText(s+"\n");
    		}
    	}
    	*/
    	
    	long totale = System.currentTimeMillis()-inizio;
    	txtTempo.setText(totale+"");
    	
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtDaAnagrammare.clear();
    	txtAnagrammiCorretti.clear();
    	txtAnagrammiErrati.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnagrammiCorretti != null : "fx:id=\"txtAnagrammiCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnagrammiErrati != null : "fx:id=\"txtAnagrammiErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDaAnagrammare != null : "fx:id=\"txtDaAnagrammare\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
    
    
}
