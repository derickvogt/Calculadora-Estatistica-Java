package model;


import java.util.Map.Entry;
import java.util.TreeMap;

public class Tabela {
	
	
	//Atributos
	private TreeMap<String, Double[]> tabela = new TreeMap<>();
	private Double[] totais = new Double[6];
	public final String nomeVariavel;
	public final String titulo;
	
	
	//Medidas de Posição
	private Double moda;
	private Double mediana;
	private Double media;
	
	//Medidas de Disperção
	private Double variancia;
	private Double desvioPadrao;
	private Double amplitude;
	
	//Construtor
	public Tabela(String[] array, String titulo, String nomeVariavel){
		this.titulo = titulo;
		this.nomeVariavel = nomeVariavel;
		FrequenciaAbsoluta(array);
		calcularTotal(Coluna.FREQ_ABSOLUTA);
		FrequenciaRelativa();
		calcularTotal(Coluna.FREQ_RELATIVA);
		FreqRelativaPercentual();
		calcularTotal(Coluna.FREQ_RELATIVA_PERCENTUAL);
		freqAbsolutaAcumulada();
		freqRelativaAcumulada();
		freqRelativaAcumuladaPercentual();
		encontrarModa();
		encontrarMediana();
		calcularMedia();
		calcularVariancia();
		desvioPadrao();
		amplitude();
	}
	
	private void FrequenciaAbsoluta(String[] array) {
				int i = Coluna.FREQ_ABSOLUTA.getValor();
		
		for(String elemento : array) {
			if(tabela.containsKey(elemento)) {
				tabela.get(elemento)[i]++;
			}else {
				tabela.put(elemento, new Double[6]);
				tabela.get(elemento)[i] = 1.0;
			}
		}
	}
	
	private void FrequenciaRelativa() {
	
		int fi = Coluna.FREQ_ABSOLUTA.getValor();
		int fr = Coluna.FREQ_RELATIVA.getValor();
		
		Double[] array;
		
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			array = linha.getValue();
			array[fr] = (array[fi] / 
					totais[fi]);
			
		}
				
	}
	
	
	private void FreqRelativaPercentual() {
		tabela.entrySet().forEach(entry ->{
		
			Double[] array = entry.getValue();
			array[2] = array[1] * 100;});
		
	}
	
	private void freqAbsolutaAcumulada() {
		
		int i = Coluna.FREQ_ABSOLUTA.getValor();
		int j = Coluna.FREQ_ABSOLUTA_ACUMULADA.getValor();
		Double[] array;
		boolean isPrimeiraLinha = true;
		Double acumulador = 0.0;
		
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			array = linha.getValue();
			
			if(isPrimeiraLinha) {
				array[j] = array[i];
				acumulador = array[i];
				isPrimeiraLinha = false;
			}else {
				acumulador += array[i];
				array[j] = acumulador;
			}
		}
	}
	
	
	private void calcularTotal(Coluna coluna) {
		
		Double acumulador = 0.0;
		
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			acumulador += linha.getValue()[coluna.getValor()];
		}
		
		if(coluna == Coluna.FREQ_RELATIVA) {
			String s = String.format("%.0f", acumulador);
			acumulador = Double.valueOf(s);
		}

		
		totais[coluna.getValor()] = acumulador; 
				
	}
	
	private void freqRelativaAcumulada() {
		int freqAbs = Coluna.FREQ_ABSOLUTA.getValor();
		int freqRelaAcu = Coluna.FREQ_RELATIVA_ACUMULADA.getValor();
		int freqAbsAcumu = Coluna.FREQ_ABSOLUTA_ACUMULADA.getValor();
		
		Double[] array; 
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			
			array = linha.getValue();
			array[freqRelaAcu] = array[freqAbsAcumu] / 
					totais[freqAbs];
		}
	}
	
	
	private void freqRelativaAcumuladaPercentual() {
		tabela.entrySet().forEach(l -> 
		l.getValue()[5] = l.getValue()[4] * 100);
	}

	private void encontrarModa(){
		Double maior = 0.0;
		int i = Coluna.FREQ_ABSOLUTA.getValor();
		Double[] array;
		for(Entry<String, Double[]> linhas : tabela.entrySet()) {
			array = linhas.getValue();
			maior = array[i] > maior ? array[i] : maior;
		}
		
		this.moda = maior;
	}
	
	private void encontrarMediana() {
		int fi = Coluna.FREQ_ABSOLUTA.getValor();
		boolean isPar = totais[fi] % 2 == 0 ? true : false;
		Double contador = 0.0;
		double posMediana = (totais[fi] + 1) / 2; 
		
		double numPosicaoMenor = Math.floor(posMediana); 
		double numPosicaoMaior = Math.ceil(posMediana);
		
		Double array[];
		
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			array = linha.getValue();
			contador += array[fi];
			
			if(isPar) {
				if(contador >= numPosicaoMenor && contador >= 
						numPosicaoMaior) {
					numPosicaoMenor = array[fi];
					numPosicaoMaior = array[fi];
				mediana = ((numPosicaoMenor + numPosicaoMaior) / 2);
					break;
				}
			}else {
				if(contador >= posMediana) {
					posMediana = array[fi];
					mediana = posMediana;
					break;
				}
			}
		}
	}
	
	
	private void calcularMedia() {
		int fi = Coluna.FREQ_ABSOLUTA.getValor();
		double somatorio = 0;
		double key;
		Double[] array;
		
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			array = linha.getValue();
			key = Double.parseDouble(linha.getKey());
			somatorio += array[fi] * key;
		}
		
		media = somatorio / totais[fi];
	}
	
	
	private void calcularVariancia() {
		
		int fi = Coluna.FREQ_ABSOLUTA.getValor();
		Double key;
		
		Double somatorio = 0.0;
		Double[] array;
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			
			key = Double.parseDouble(linha.getKey());
			array = linha.getValue();
			
			somatorio += Math.pow((key - media), 2) * array[fi];
		}
		
		variancia = somatorio/totais[fi];
	}
	
	
	private void desvioPadrao() {
		desvioPadrao = Math.sqrt(variancia);
	}
	
	private void amplitude() {
		Double maior = 0.0;
		Double menor = 0.0;
		
		int fi = Coluna.FREQ_ABSOLUTA.getValor();
		Double[] array;
		for(Entry<String, Double[]> linha : tabela.entrySet()) {
			array = linha.getValue();
			if(array[fi] > maior) {
				maior = array[fi];
			}else if(array[fi] < menor) {
				menor = array[fi];
			}
		}
		
		amplitude = maior - menor;
		
		
	}
	
	//Get's
	public Double[] getTotal() {
		return totais;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getNomeVariavel() {
		return nomeVariavel;
	}
	
	public Double getModa() {
		return moda;
	}
	
	public Double getMediana() {
		return mediana;
	}
	
	public Double getMedia() {
		return media;
	}
	
	public Double getVariancia() {
		return variancia;
	}
	
	public Double getDesvioPadrao() {
		return desvioPadrao;
	}
	
	public Double getAmplitude() {
		return amplitude;
	}
	
	public Double[] getTotais() {
		return totais;
	}
	
	
	public TreeMap<String, Double[]> getTabela(){
		return tabela;
	}
	
	

}

