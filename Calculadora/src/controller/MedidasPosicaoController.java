package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class MedidasPosicaoController extends MenuController implements Initializable{

	//Atributos
	
	@FXML
	Label labelMedia;

	@FXML
	Label labelMediana;
	
	@FXML
	Label labelModa;
	
	@FXML
	Label labelTitulo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		labelTitulo.setAlignment(Pos.CENTER);
	}	
	
	public void obterDados(Double media, Double mediana, Double moda) {
		String strMedia = String.format("%.4f", media);
		String strMediana = String.format("%.2f", mediana);
		String strModa = String.format("%.2f", moda);
		
		labelMedia.setText(strMedia);
		labelMediana.setText(strMediana);
		labelModa.setText(strModa);
	}

	
}
