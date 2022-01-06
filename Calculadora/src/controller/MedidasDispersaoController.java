package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MedidasDispersaoController extends MenuController{

	@FXML
	Label varianciaLabel;
	
	@FXML
	Label amplitudeLabel;
	
	@FXML
	Label desvioPadraoLabel;
	
	public void obterDados(Double variancia, Double desvioPadrao, Double amplitude) {
		varianciaLabel.setText(String.format("%.4f", variancia));
		desvioPadraoLabel.setText(String.format("%.4f", desvioPadrao));
		amplitudeLabel.setText(String.format("%.4f", amplitude));
	}

	
}
