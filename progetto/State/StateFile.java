package org.progetto.State;

import java.io.IOException;

// RUOLO: State
public interface StateFile {
    public void read(); // per leggere i file modificati

    public StateFile write(String word, String path) throws IOException; // per scrivere in un secondo file evidenziando
                                                                         // le parole trovate

    public void show(); // mostra lo stato della ricerca
}
