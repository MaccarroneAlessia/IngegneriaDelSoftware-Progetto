package org.progetto.State;

import java.io.IOException;

public class NotFind implements StateFile {

    @Override
    public void read() {
        System.out.println("non ci sono file modificati per il seguente file");
    }

    @Override
    public StateFile write(String word, String path) throws IOException { // il file essendo inizializzato con lo stato
                                                                          // NotFind entrerà in questo metodo e cambierà
                                                                          // stato
        System.out.println("PAROLA TROVATA: Cambio di stato");
        StateFile Find = new Find();
        return Find.write(word, path);
    }

    @Override
    public void show() {
        System.out.println("NOT FIND: Il file non contiene nessuna parola.");
        read();
    }

}
