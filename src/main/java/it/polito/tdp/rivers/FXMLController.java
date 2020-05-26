package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRiempi(ActionEvent event) {
    	txtStartDate.clear();
    	txtEndDate.clear();
    	txtNumMeasurements.clear();
    	txtFMed.clear();
    	
    	this.model.getFlowRiver(boxRiver.getValue());
    	txtStartDate.appendText(""+this.model.getFirstDate());
    	txtEndDate.appendText(""+this.model.getLastDate());
    	txtNumMeasurements.appendText(""+this.model.getNumFlow());
    	txtFMed.appendText(""+this.model.getFlowAvg(boxRiver.getValue()));
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	double k = 0;
    	
    	try {
    		k = Double.parseDouble(txtK.getText());
    	} catch (NumberFormatException e) {
			txtResult.appendText("Inserire un numero!\n");
			return;
    	}
    	
    	if(k<0) {
			txtResult.appendText("Il numero inserito deve essere maggiore di 0!\n");
			return;
    	}
    	
    	this.model.simulaEventi(k);
    	
    	txtResult.appendText("La capacità media è pari a "+this.model.getMediaC()+"\n");
    	txtResult.appendText("Il numero di giorni in cui non si è potuta garantire l'erogazione minima è "+this.model.getNumGiorni()+"\n");

    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.boxRiver.getItems().setAll(this.model.getAllRivers());
    }
}
