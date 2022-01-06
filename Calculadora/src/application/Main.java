package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	
	public enum Tela{
		
		TELA_CONFIGURACAO, TELA_TABELA, TELA_POSICAO,
		TELA_DISPERSAO;
	}
	

	//Atributos de Classe
	
	static Stage janela;
	static Scene telaConfiguracao;
	static Scene telaTabela;
	static Scene telaPosicao;
	static Scene telaDispersao;
	
	public static URL caminhoTelaConfiguracao;
	public static URL caminhoTelaTabela;
	public static URL caminhoTelaPosicao;
	public static URL caminhoTelaDispersao;
	
	public static FXMLLoader telaConfiguracaoLoader;
	
	
	
	//interfaces funcionais
	
	 private static Function<FXMLLoader, Parent> carregarParent = l ->{
		try {
			URL caminho = l.getLocation();
			l = null;
			l = new FXMLLoader(caminho);
			return l.load();
		} catch (IOException exception) {
		exception.printStackTrace();
		}
		return null;
	};
	
	private static Function<Parent, Scene> criarCena = p ->{
		return new Scene(p);
	};
	
	private static BiConsumer<String, Scene> mostrarJanela = (titulo, cena) ->{
		janela.setTitle(titulo);
		janela.setScene(cena);
		janela.show();
	};
	
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		janela = primaryStage;
		
		
		//Tela de Configurações
		caminhoTelaConfiguracao = getClass().getResource("/view/TelaConfiguracao.fxml");
		telaConfiguracaoLoader = new FXMLLoader(caminhoTelaConfiguracao);
		
		//Caminhos outros arquivos FXML
		
		caminhoTelaTabela = getClass().getResource("/view/TelaTabela.fxml");
		caminhoTelaPosicao = getClass().getResource("/view/TelaMedidasPosicao.fxml");
		caminhoTelaDispersao = getClass().getResource("/view/TelaMedidasDispersao.fxml");
		
		Parent p;
		
		
		p = carregarParent.apply(telaConfiguracaoLoader);
		colocarCena(p,Tela.TELA_CONFIGURACAO);
	
		
		p = null;
		
		mostrarTela(Tela.TELA_CONFIGURACAO);
	}
	
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
		

	public static void colocarCena(Parent p, Tela t) {
		
		switch(t) {
		case TELA_CONFIGURACAO:{
			
			telaConfiguracao = null;
			telaConfiguracao = criarCena.apply(p);
			break;
		}
		case TELA_TABELA:{
			
			telaTabela = null;
			telaTabela = criarCena.apply(p);
			break;
		}
		
		case TELA_POSICAO:{
			telaPosicao = null;
			telaPosicao = criarCena.apply(p);
			break;
		}
		
		case TELA_DISPERSAO:{
			telaDispersao = null;
			telaDispersao = criarCena.apply(p);
			break;
		}
		}
		
	}
	
public static void mostrarTela(Tela tela) {
	
	switch(tela) {
	
	case TELA_CONFIGURACAO:{
		mostrarJanela
		.accept("Calculadora - Configuração", telaConfiguracao);
		break;
	}
	
	case TELA_TABELA:{
		mostrarJanela
		.accept("Calculadora - Tabela", telaTabela);
		break;
	}
	
	case TELA_POSICAO:{
		mostrarJanela
		.accept("Calculadora - Medidas de Posição", telaPosicao);
		break;
	}
	
	case TELA_DISPERSAO:{
		mostrarJanela
		.accept("Calculadora - Medidas de Dispersão", telaDispersao);
		break;
	}
	}
}
}