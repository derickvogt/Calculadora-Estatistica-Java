package model;

public enum Coluna {
	
	//Atributos
	FREQ_ABSOLUTA(0), FREQ_RELATIVA(1),
	FREQ_RELATIVA_PERCENTUAL(2),
	FREQ_ABSOLUTA_ACUMULADA(3),
	FREQ_RELATIVA_ACUMULADA(4),
	FREQ_RELATIVA_ACUM_PERCENTUAL(5);
	
	
	
	private int valor;
	
	//Construtor
	Coluna(int valor){
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}	
}
