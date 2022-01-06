package controller;
import application.Main;
import application.Main.Tela;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

	@FXML
	protected Button botaoMenuConfiguracao;
	
	@FXML
	protected Button botaoMenuTabela;
	
	@FXML
	protected Button botaoMenuPosicao;
	
	@FXML
	protected Button botaoMenuDispersao;
	
	
	@FXML
	protected void botaoMenuConfiguracao() {
		Main.mostrarTela(Tela.TELA_CONFIGURACAO);
	}
	
	@FXML
	public void botaoMenuTabela() {
		Main.mostrarTela(Tela.TELA_TABELA);
		
	}
	
	@FXML
	protected void botaoMenuPosicao() {
		Main.mostrarTela(Tela.TELA_POSICAO);
	}
	
	@FXML
	protected void botaoMenuDispersao() {
		Main.mostrarTela(Tela.TELA_DISPERSAO);
	}
	
	
}
