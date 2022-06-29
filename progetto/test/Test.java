package org.progetto.State;

public class Test {
    private static int prove = 0;
    private static String[] words = new String[] { "esame", "ingegneria", "del", "software" };
    private static int m = 3;

    public static void main(String[] args) {
        testSearcher();
    }

    private static void testSearcher() {
        String _a = newFile(true);
        String _b = newFile(false);
        MyFile a = new MyFile(_a, words, m);
        MyFile b = new MyFile(_b, words, m);

        System.out.println("Visualizziamo i file di test ");
        System.out.println();
        copyRead(_a);
        System.out.println();
        System.out.println("-----");
        System.out.println();
        copyRead(_b);

        a.searcher();
        b.searcher();

        System.out.println("-----");
        System.out.println("vediamo lo stato della ricerca");
        a.show();
        System.out.println();
        System.out.println("-----");
        System.out.println();
        b.show();
    }

    private static String newFile(boolean b) {
        ++prove;
        String path2 = "C:/" + "prova" + prove + ".txt";

        try {
            File file = new File(path2);
            if (file.exists()) {
                double num = Math.random();
                String path3 = num + path2;
                return newFile(path3);
            }
            if (file.createNewFile()) {
                setNewFile(path2, b);
                return path2;
            } else {
                return newFile(path2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return newFile(txt);
        }
    }

    private static void setNewFile(String path, boolean b) {
        if (b) {
            System.out.println("Stiamo creando un file che contiene le parole da cercare");
            setFind(path);
        } else {
            System.out.println("Stiamo creando un file senza le parole da cercare");
            setNotFind(path);
        }
    }

    private static void setFind(String path) {
        System.out.println("testiamo lo stato Find:");
        if ((Math.random() % 2) == 0) {
            String[] arr = new String[] { "ciao ", "prova ", "ingegneria", "topolino" };
        } else {
            String[] arr = new String[] { "esame", " universita' ", "appelli  ", "software" };
        }

        copyWrite(path, arr);
    }

    private static void setNotFind(String path) {
        System.out.println("testiamo lo stato NotFind");
        String[] arr = new String[] { "ciao ", "prova ", "Pippo", "topolino" };
        copyWrite(path, arr);
    }

    private static void copyWrite(String path, String[] arr) {
        try {
            // apre il file in scrittura
            FileWriter fileout = new FileWriter(path);

            do {
                String str;
                /*
                 * System.out.print("Scrivi una stringa (vuota per terminare): ");
                 * str = Input.readLine();
                 */

                for (int j = 0; j < 5; j++) {
                    str = arr[j];
                    // il ciclo scrive ogni carattere delle stringa nel file
                    for (int i = 0; i < str.length(); i++)
                        fileout.write(str.charAt(i));
                    fileout.write('\n');
                }

            } while (str.length() > 0);

            fileout.close(); // chiude il file

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void copyRead(String path) {

        try {
            // apre il file in lettura
            FileReader filein = new FileReader(path);

            int next;
            do {
                next = filein.read(); // legge il prossimo carattere

                if (next != -1) { // se non e' finito il file
                    char nextc = (char) next;
                    System.out.print(nextc); // stampa il carattere
                }

            } while (next != -1);

            filein.close(); // chiude il file

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
