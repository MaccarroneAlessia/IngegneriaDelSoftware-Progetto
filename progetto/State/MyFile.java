package org.progetto.State;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

//il mio file rappresenta il contesto
public class MyFile{
    private StateFile st = new NotFind();         //oggetto state
    private final String path;                    //nome file
    private String[] words;                       //array di parole da cercare
    private int n;                                //numero di parole

    public MyFile(String pathname, String[] arrWord, int m){
        path = pathname;
        words = arrWord;
        n = m;
    }

    private void inspection(){
        if (st == null) {
            System.out.println("ERRORE: stato perso");
            System.exit(-1);
        }
    }
    
	public void show() {						//	da in output lo stato della ricerca del file
        inspection();
        System.out.println("stiamo cercando le parole dal file "+ path);
        System.out.println("Le parole da cercare sono: ");
        IntStream.rangeClosed(0,n-1).forEach(i -> System.out.print(words[i]));
		st.show();
	}

	private void search(String word) throws IOException {			//	cerca la parola passata dal file
		//chiama read 
        File a = new File(path);
        Scanner b = new Scanner(a);
        String now=null;
        while((now=b.next())!=null)
        {
            if(now.equals(word))
            {
                st = st.write(word, path);
            }
        }
	}

    public void searcher(){                            //metodo chiamato dal contesto per la ricerca
        IntStream.rangeClosed(1, n-1).forEach(i -> {
            try {
                search(words[i]);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
