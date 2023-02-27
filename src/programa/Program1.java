package programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Produtos;

public class Program1 {

	public static void main(String[] args) throws ParseException{
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Produtos> list = new ArrayList<>();
		
		System.out.println("Insira o caminho do arquivo: ");
		String caminhoArquivo = sc.nextLine();
		
		File arquivo = new File(caminhoArquivo);
		
		String caminhoDiretorio = arquivo.getParent();
		
		boolean success = new File(caminhoDiretorio + "\\out").mkdir();

		String caminhoSaida = caminhoDiretorio + "\\out\\summary.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){
			
			String itemCsv = br.readLine();
			while(itemCsv != null) {
				
				String[] campos = itemCsv.split(",");
				String nome = campos[0];
				double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);
				
				list.add(new Produtos(nome, preco, quantidade));				
				itemCsv = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoSaida))){
				
				for(Produtos item : list) {
					bw.write(item.getNome() + ", " + String.format("%.2f", item.total()));
					bw.newLine();
				}
				
				
			}catch(IOException e) {
				System.out.println("Erro ao ler o arquivo: " + e.getMessage());
			}
			
		}catch (IOException e) {
			System.out.println("Falha ao ler o arquivo" + e.getMessage());
		}
		
		
		
		
	
		
		
		sc.close();

	}

}
