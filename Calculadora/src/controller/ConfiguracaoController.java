package controller;

import java.io.File;
import java.io.IOException;

import application.Main;
import application.Main.Tela;
import exceptions.ArquivoVazioException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Tabela;


public class ConfiguracaoController extends MenuController{

	//Atributos
	
	@FXML
	Button botaoProcurar;
	
	@FXML
	Label labelArquivo;
	
	@FXML
	TextField textFieldTitulo;
	
	@FXML
	TextField textFieldVariavel;
	
	@FXML
	Button botaoGerar;
		
	File arquivoSelecionado;
	
	
	@FXML
	public void initialize() {
		botaoGerar.setDisable(true);
		
		textFieldVariavel.setOnKeyPressed(evento ->{
			if(evento.getCode() == KeyCode.ENTER) {
			botaoGerar.fire();
			}
		});
		
		textFieldTitulo.setOnKeyPressed(evento ->{
			if(evento.getCode() == KeyCode.ENTER) {
				textFieldVariavel.requestFocus();
			}
		});
		
		desabilitarBotoesMenu(true);
	}

	public void procurarArquivo() {
	
		FileChooser fc = new FileChooser();
		
		fc.getExtensionFilters()
		.addAll(new ExtensionFilter("Arquivos txt", "*.txt"));
		
		File arquivo; 
		arquivo = fc.showOpenDialog(null);
		
		if(arquivo != null) {
		
			arquivoSelecionado = arquivo;
			arquivo = null;
			labelArquivo.setText(arquivoSelecionado.getName());
			labelArquivo.setVisible(true);
			labelArquivo.setTextAlignment(TextAlignment.LEFT);
			botaoProcurar.setText("Procurar outro Arquivo");
		}else {
			botaoProcurar.setText("Procurar Arquivo");
		}
		
		habilitarBotaoGerar();
	}
	
	public void habilitarBotaoGerar() {
		
		boolean campoTituloIsVazio = textFieldTitulo.getText().isEmpty(); 
		boolean campoVariavelIsVazio = textFieldVariavel.getText().isEmpty();
		
		if(!campoTituloIsVazio && !campoVariavelIsVazio &&
				arquivoSelecionado != null) {
			
			botaoGerar.setDisable(false);
			botaoGerar.getStyleClass().remove("botaoDesarmado");
			botaoGerar.getStyleClass().add("botaoGerarAtivo");
		}else {
			botaoGerar.setDisable(true);
			botaoGerar.getStyleClass().remove("botaoGerarAtivo");
			
			if(!botaoGerar.getStyleClass().contains("botaoDesarmado")) {
				botaoGerar.getStyleClass().add("botaoDesarmado");				
			}
		}
		
	}
	
	public void botaoGerarTabela() {
		
		
		try {
			
			String[] dados; 
			
			//lendo o arquivo
			dados = model.LeitorArquivo.lerArquivo(arquivoSelecionado);
			
			
			//Obtendo os campos
			String titulo = textFieldTitulo.getText();
			String nomeVariavel = textFieldVariavel.getText();
			
			
			//Instanciando a tabela
			Tabela tabela = new Tabela(dados, titulo, nomeVariavel);
			
			
			//Modificando a cena Tabela
			FXMLLoader tabelaLoader = new FXMLLoader(Main.caminhoTelaTabela);
			Parent p = tabelaLoader.load();
			TabelaController tc = tabelaLoader.getController();
			tc.obterTabela(tabela);
			//Alterando a tela Tabela
			Main.colocarCena(p, Tela.TELA_TABELA);
			
			
			
			//Modificando a cena Medidas de Posição
			FXMLLoader medidasPosicaoLoader = 
				new FXMLLoader(Main.caminhoTelaPosicao);
			p = medidasPosicaoLoader.load();
			MedidasPosicaoController mp;
			mp = medidasPosicaoLoader.getController();
			Main.colocarCena(p, Tela.TELA_POSICAO);
			
			Double media = tabela.getMedia();
			Double mediana = tabela.getMediana();
			Double moda = tabela.getModa();
			
			mp.obterDados(media, mediana, moda);
			
			
			
			//Modificando a cena Medidas de Dispersão
			FXMLLoader dispersaoLoader = new FXMLLoader
					(Main.caminhoTelaDispersao);
			p = dispersaoLoader.load();
			MedidasDispersaoController md;
			md =dispersaoLoader.getController();
			md.obterDados(tabela.getVariancia(), 
					tabela.getDesvioPadrao(), tabela.getAmplitude());
			
			Main.colocarCena(p, Tela.TELA_DISPERSAO);
			
			
			
			tabela = null;
			
			
			//Mudando para a tela Tabela
			Main.mostrarTela(Tela.TELA_TABELA);
			
			
		}catch(ArquivoVazioException | IOException e) {
			labelArquivo.setText("Arquivo Vazio ou com Espaços em Branco");
		}finally {
			desabilitarBotoesMenu(false);
		}		
	}
	
	private void desabilitarBotoesMenu(boolean b) {
		botaoMenuTabela.setDisable(b);
		botaoMenuPosicao.setDisable(b);
		botaoMenuDispersao.setDisable(b);
	}
	
}
