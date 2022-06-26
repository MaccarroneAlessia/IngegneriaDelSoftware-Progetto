package org.progetto.State;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Find implements StateFile{

    private String pathmod;
    private ArrayList<String> wordsFind = new ArrayList<>();

    private String newFile(String txt){
        String path2 = "C:/mod" + txt;

        try {
            File file = new File(path2);
            if (file.exists()){
                System.out.println("Il file " + path2 + " esiste già");
                double n = Math.random();
                String path3 = n + txt;
                return newFile(path3);
            }
            if (file.createNewFile()){
                System.out.println("Il file è stato creato: " + path2);
                return path2;
            }
            else{
                System.out.println("Il file non può essere creato");
                return newFile(path2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return newFile(txt);
        }
    }

    private boolean highlight(){

        FileInputStream fstream = null;
        DataInputStream in = null;
        BufferedWriter out = null;

        try {
            // apro il file
            fstream = new FileInputStream(pathmod);

            // prendo l'inputStream
            in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            StringBuilder fileContent = new StringBuilder();

            // Leggo il file riga per riga
            while ((strLine = br.readLine()) != null) {

                int n = wordsFind.size();
                
                if(strLine.contains(wordsFind.get(n-1))){        //cerca l'ultima parola inserita nell'array delle parole trovate
                    // se la riga è uguale a quella ricercata
                    strLine.replace(wordsFind.get(n-1),"***"+wordsFind+"***");
                    fileContent.append(strLine + System.getProperty("line.separator"));
                    
                } else {
                    // ... altrimenti la trascrivo così com'è
                    fileContent.append(strLine);
                    fileContent.append(System.getProperty("line.separator"));
                }
            }

            // Sovrascrivo il file con il nuovo contenuto (aggiornato)
            FileWriter fstreamWrite = new FileWriter(pathmod);
            out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());

        } finally {
            // chiusura dell'output e dell'input
            try {
                fstream.close();
                out.flush();
                out.close();
                in.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean modify(String txt) throws IOException{
        pathmod = newFile(txt);             //Creare un nuovo file txt da modificare
        FileWriter writer = new FileWriter(pathmod, true);
        PrintWriter out = new PrintWriter(writer);

        try {
            File ftxt = new File(txt);
            File fpathmod = new File(pathmod);
            copyFile(ftxt, fpathmod);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        out.close();

        return highlight();
    }

    private static void copyFile(File src, File dest) throws IOException {
        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public void read() {
        Path path = Paths.get(pathmod);
        try {
            //String content = Files.readString(path);
            //System.out.println(content);
            
            Stream<String> lines = Files.lines(path);
            String content = lines.collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);
            lines.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    @Override
    public StateFile write(String word, String path) throws IOException {
        wordsFind.add(word);

        boolean control = this.modify(path);
        if(control) {
            System.out.println("file modificato correttamente");
            return this;
        }

        return null;
    }

    @Override
    public void show() {
        System.out.println("FIND: Il file contiene almeno una parola cercata");
        System.out.println("Il file ha una copia modificata con il nome: " + pathmod);
        read();
    }

}


