import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    private static Tar tar;

    // Programa que empra la clase Tar
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Benvolgut. Quina operació vols fer?");
        do {
            System.out.printf("%-5s %s %s %n", "1-", "Carregar un fitxer tar a memòria.", "(OBLIGATORI EL PRIMER PIC)");
            System.out.printf("%-5s %s %n", "2-", "Llistar els nombres dels arxius que hi ha dins el fitxer.");
            System.out.printf("%-5s %s %n", "3-", "Conéixer el tamany dels arxius.");
            System.out.printf("%-5s %s %n", "4-", "Conéixer el propietari dels arxius.");
            System.out.printf("%-5s %s %n", "5-", "Conéixer el grup dels arxius.");
            System.out.printf("%-5s %s %n", "6-", "Extreure el fitxer a una destinació.");
            System.out.printf("%-5s %s %n", "7-", "Sortir.");

            int decisio = sc.nextInt();
            switch (decisio) {
                case 1:
                    tar = load();
                    break;
                case 2:
                    if (tar == null) {
                        System.out.println("Per favor, assegura't de que primer s'ha carregat l'arxiu a memòria. " +
                                "Per fer això, empra '1'");
                    } else {
                        list();
                    }
                    break;
                case 3:
                    if (tar == null) {
                        System.out.println("Per favor, assegura't de que primer s'ha carregat l'arxiu a memòria. " +
                                "Per fer això, empra '1'");
                    } else {
                        size();
                    }
                    break;
                case 4:
                    if (tar == null) {
                        System.out.println("Per favor, assegura't de que primer s'ha carregat l'arxiu a memòria. " +
                                "Per fer això, empra '1'");
                    } else {
                        owner();
                    }
                    break;
                case 5:
                    if (tar == null) {
                        System.out.println("Per favor, assegura't de que primer s'ha carregat l'arxiu a memòria. " +
                                "Per fer això, empra '1'");
                    } else {
                        group();
                    }
                    break;
                case 6:
                    if (tar == null) {
                        System.out.println("Per favor, assegura't de que primer s'ha carregat l'arxiu a memòria. " +
                                "Per fer això, empra '1'");
                    } else {
                        extract();
                    }
                    break;
                case 7:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    // Carrega l'arxiu desde la ruta que ens proporciona l'usuari
    public static Tar load(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix la ruta del fitxer: ");
        String path = sc.next();
        Tar t = new Tar(path);
        t.expand();
        System.out.println("Fitxer carregat amb éxit.");
        return t;
    }

    // Llista els fitxers que hi ha dins el tar
    public static void list(){
        // Recorrem la llista de fitxers que té el nostre objecte Tar imprimint per pantalla el nom de cada fitxer
        String[] list = tar.list();
        for (String s : list) {
            System.out.println(s);
        }
    }

    // Llista els tamanys dels fitxers que hi ha dins el tar
    public static void size(){
        // Recorrem la llista de fitxers que té el nostre objecte Tar imprimint per pantalla el tamany de cada fitxer
        long totalsize = 0;
        for (File f : tar.fileList) {
            System.out.printf("%-50s : %s bytes %n", f.getFilename(), f.getFilesize());
            totalsize += f.getFilesize();
        }
        System.out.printf("%-50S : %s bytes %n %n %n", "total size", totalsize);
    }

    // Llista els propietaris dels fitxers que hi ha dins el tar
    public static void owner(){
        // Recorrem la llista de fitxers que té el nostre objecte Tar imprimint per pantalla el propietari de cada fitxer
        for (File f : tar.fileList) {
            System.out.printf("%-50s Propietari: %s %n", f.getFilename(), f.getFileowner());
        }
        System.out.printf("%n %n %n");
    }

    // Llista els grups dels fitxers que hi ha dins el tar
    public static void group(){
        // Recorrem la llista de fitxers que té el nostre objecte Tar imprimint per pantalla el grup de cada fitxer
        for (File f : tar.fileList) {
            System.out.printf("%-50s Grup: %s %n", f.getFilename(), f.getFilegroup());
        }
        System.out.printf("%n %n %n");
    }

    //  Extreu un fitxer del tar a una destinació que li passem
    public static void extract(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Introdueix la ruta on vols descomprimir el fitxer (assegura't d'acabar amb '/'): ");
            String path = sc.next();
            for (File f : tar.fileList) {
                OutputStream os = new FileOutputStream(path.concat(f.getFilename()));
                DataOutputStream dos = new DataOutputStream(os);
                dos.write(f.getContent());
            }
        } catch (Exception e){
            System.out.println("Algo ha anat malament.");
            e.printStackTrace();
        }
    }
}
