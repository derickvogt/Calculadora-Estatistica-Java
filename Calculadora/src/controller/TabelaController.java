package controller;

import java.net.URL;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import model.Coluna;
import model.Tabela;

public class TabelaController extends MenuController implements Initializable{

	//Atributos
	
	@FXML
	Label tabelaTitulo;

	private Tabela tabela;
	
	
	@FXML
	TableView <Entry<String, Double[]>> tabelaFrequencia;
	
	 @FXML
	    private Label totalFreqAbsoluta;

	    @FXML
	    private Label totalFreqAbsolutaAcu;

	    @FXML
	    private Label totalFreqRelAcu;

	    @FXML
	    private Label totalFreqRelAcuPercentual;

	    @FXML
	    private Label totalFreqRelativa;

	    @FXML
	    private Label totalFreqRelativaPerc;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabelaTitulo.setWrapText(true);
	}
	
	public void obterTabela(Tabela tabelaGerada) {
		tabela = tabelaGerada;
		mudarTitulo(tabela.getTitulo());
		gerarTabela();
	}
	
	private void mudarTitulo(String titulo) {
		tabelaTitulo.setText(titulo);
	}

	
	
	//Coluna Variável
	private void gerarTabela() {
		TableColumn<Entry<String, Double[]>, String> colunaVariavel =
				new TableColumn<>(tabela.getNomeVariavel());
		colunaVariavel.setCellValueFactory(key -> new SimpleStringProperty(key.getValue().getKey()));
		
		
		
		
		//Coluna Frequência Absoluta
		TableColumn<Entry<String, Double[]>, String> colunaFreqAbsoluta =
				new TableColumn<>("fi");
		
		colunaFreqAbsoluta.setCellValueFactory(v -> new SimpleStringProperty(
				String.format("%.2f", v.getValue()
				.getValue()[Coluna.FREQ_ABSOLUTA.getValor()])));
		
		
		//Coluna Frequência Relativa
		TableColumn<Entry<String, Double[]>, String> colunaFreqRelativa;
		colunaFreqRelativa = new TableColumn<>("fr");
		colunaFreqRelativa.setCellValueFactory(v ->
		new SimpleStringProperty(String.format("%.2f",v.getValue()
		.getValue()[Coluna.FREQ_RELATIVA.getValor()])));
		
		
		//Coluna Frequência Relativa Percentual
		TableColumn<Entry<String, Double[]>, String> 
		colFreqRelaPercentual;
		
		colFreqRelaPercentual = new TableColumn<>("fr%");
		colFreqRelaPercentual.setCellValueFactory(v ->
		new SimpleStringProperty(String.format("%.2f%%", v.getValue()
				.getValue()[Coluna.FREQ_RELATIVA_PERCENTUAL.getValor()])));

		
		//Coluna Frequência Absoluta Acumulada
		TableColumn<Entry<String, Double[]>, String> 
		colunaFreqAbsAcumulada;
		colunaFreqAbsAcumulada = new TableColumn<>("Fi");
		colunaFreqAbsAcumulada.setCellValueFactory(v ->
		new SimpleStringProperty(String.format("%.2f", v.getValue()
		.getValue()[Coluna.FREQ_ABSOLUTA_ACUMULADA.getValor()])));
	
		
		//Frequencia acumulada relativa
		TableColumn<Entry<String, Double[]>, String>
		colunaFreqRelAcumulada;
		colunaFreqRelAcumulada = new TableColumn<>("Fr");
		colunaFreqRelAcumulada.setCellValueFactory(v ->
		new SimpleStringProperty(String.format("%.2f", v.getValue()
		.getValue()[Coluna.FREQ_RELATIVA_ACUMULADA.getValor()])));
		
		
		//Frequencia relativa acumulada percentual
		
		TableColumn<Entry<String, Double[]>, String>
		colunaFreqRelAcuPercentual;
		colunaFreqRelAcuPercentual = new TableColumn<>("Fr%");
		
		colunaFreqRelAcuPercentual.setCellValueFactory(v ->
		new SimpleStringProperty(String.format("%.2f%%", v.getValue()
		.getValue()[Coluna.FREQ_RELATIVA_ACUM_PERCENTUAL.getValor()])));
		
		
		//Adicionando as colunas na tabela
		tabelaFrequencia.getColumns().add(colunaVariavel);
		tabelaFrequencia.getColumns().add(colunaFreqAbsoluta);
		tabelaFrequencia.getColumns().add(colunaFreqRelativa);
		tabelaFrequencia.getColumns().add(colFreqRelaPercentual);
		tabelaFrequencia.getColumns().add(colunaFreqAbsAcumulada);
		tabelaFrequencia.getColumns().add(colunaFreqRelAcumulada);
		tabelaFrequencia.getColumns().add(colunaFreqRelAcuPercentual);
		
		//Adicionando EntrySet na ta tabela.
		tabelaFrequencia.getItems().addAll(tabela.getTabela().entrySet());
		
		obterTotais();
		
	}
	
	private void obterTotais() {
		int fi = Coluna.FREQ_ABSOLUTA.getValor();
		int fr = Coluna.FREQ_RELATIVA.getValor();
		int frPerc = Coluna.FREQ_RELATIVA_PERCENTUAL.getValor();
		
		Double[] totais = tabela.getTotais();
		
		totalFreqAbsoluta.setText(String.format("%.2f", totais[fi]));
		totalFreqRelativa.setText(String.format("%.2f", totais[fr]));
		totalFreqRelativaPerc.setText(String.format("%.2f%%", totais[frPerc]));
		totalFreqAbsolutaAcu.setText("-");
		totalFreqRelAcu.setText("-");
		totalFreqRelAcuPercentual.setText("-");
	}
	
}
