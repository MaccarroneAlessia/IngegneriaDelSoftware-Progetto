package org.progetto.State;

import java.io.*;
import java.util.stream.IntStream;

/*
 * Il progetto deve riguardare la ricerca contemporanea di un certo numero di parole 
 * all’interno di file di testo presenti in una cartella. -> 
 * se ne occupa il contesto con il supporto delle classi ConcreteState grazie al design pattern
 * 
 * Le parole vengono date come input insieme, -> 
 * nel main del client che si occuperà di passarle correttamente alla classe Facade che interagisce con il sottosistema
 * 
 * quindi si genera un corrispondente file di testo (output), -> la classe ConcreteState FIND
 * in cui le parole trovate sono evidenziate (con doppi asterischi o con un altro marker).
 * 
*/

public class Client {
	public static File isDir(File nameDir){
		if (nameDir.isDirectory()){
			System.out.println("il path inserito è una cartella");
			return nameDir;
		}
		else{
            System.out.println("ERRORE: il path inserito non è una cartella");
            System.out.println("inserire il path di una cartella con il seguente formato");
            System.out.println("/percorso/delle/cartelle");

			InputStreamReader reader = new InputStreamReader (System.in);
			BufferedReader myInput = new BufferedReader (reader);
			String str1 = new String();

			try {str1 = myInput.readLine();
			} catch (IOException e) {
				System.out.println ("Si è verificato un errore: " + e);
				System.exit(-1); 
			}
			
			System.out.println ("Hai scritto: " +str1);
			File name = new File(str1);
			return isDir(name);
		}
	}

	public static void main(String[] args) {

		String nameDir = new String(args[0]);

		int n = (args.length)-1;
		String[] words = new String[n];					            //array delle parole da cercare
		IntStream.rangeClosed(1,n-1).forEach(i -> words[i-1] = args[i]);

		Folder dir = new Folder(isDir(new File(nameDir)), words);		        //oggetto di tipo facade
        //l'oggetto facade salva i file all'interno alla cartella e l'array di parole

        dir.search();

	}
}
