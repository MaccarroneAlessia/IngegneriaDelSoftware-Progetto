package org.progetto.State;

import java.io.File;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Objects;

// la cartella è una classe di supporto che fa interfaccia al client e il sottosistema -> come un Facade
public class Folder {
	static private int m; 						// numero file di testo
	static private File winFolder;				// file caltella
	private boolean find[];						//se true abbiamo trovato delle parole
												//in quel corrispondente file di indice i
	private List<MyFile> theFile; 				//si salva in una lista gli oggetti MyFile

    public Folder(File path, final String[] toSearch){
		String name = path.toString();
		boolean errore = getArrayFile(name, toSearch);

		System.out.println("Cartella : " + winFolder + "  con " + m + " file di testo");
	
		if(!errore)
			System.out.println("An error has occurred");

		IntStream.rangeClosed(1,m).forEach(i -> find[i] = false);
    }

	private boolean getArrayFile(String name, final String[] toSearch){
		String ROOT = "C:";
		name = name.replace("\\", "/"); // Create a new variable
		char FS = File.separatorChar;
		
		try{
			winFolder = new File(ROOT + FS + name);
			m = (int) winFolder.length();
			String[] supp = winFolder.list();
			int n = toSearch.length;
			IntStream.rangeClosed(0,m-1).forEach(i -> theFile.add(new MyFile(supp[i], toSearch, n)));
			return true;
		}
		catch (Exception e) {
            System.err.println(e.getMessage());
        }

		return false;
	}

	//metodi che chiamano i metodi di MyFile e che verranno chiamati dal Client per la ricerca
	public void search(){
		System.out.println("Stiamo cercando nei file della cartella le parole passate...");
		IntStream.rangeClosed(0,m-1).forEach(i -> theFile.get(i).searcher());

		System.out.println("Mostriamo i file modificati");
		IntStream.rangeClosed(0,m-1).forEach(i -> theFile.get(i).show());
	}
	


}
