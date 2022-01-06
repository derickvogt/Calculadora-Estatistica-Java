package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import exceptions.ArquivoVazioException;

public class LeitorArquivo {

	
	public static String[] lerArquivo(File arquivo) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		
		boolean isVazio = true;
		FileReader leitorDeArquivo;
			
			leitorDeArquivo = new FileReader(arquivo);
			BufferedReader bufferLeitura = new BufferedReader(leitorDeArquivo);
		
			
			String linha = "";
			
			while(true) {
				linha = bufferLeitura.readLine();
				
				if(linha != null) {
				
					sb.append(linha.replaceAll(",", "."));
					sb.append("/");
					isVazio = false;
					
				}else {
					if(linha == null && isVazio) {
						bufferLeitura.close();
						throw new ArquivoVazioException();
					}
					break;
				}
			}
			
			leitorDeArquivo.close();
			bufferLeitura.close();
				
		
	
		return sb.toString().split("/");
	}
}
