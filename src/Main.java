import java.awt.*;
import java.io.*;
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
            System.out.printf("%-5s %s %n", "7-", "Obrir un arxiu contingut al tar.");
            System.out.printf("%-5s %s %n", "8-", "Sortir.");

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
                    if (tar == null) {
                        System.out.println("Per favor, assegura't de que primer s'ha carregat l'arxiu a memòria. " +
                                "Per fer això, empra '1'");
                    } else {
                        run();
                    }
                    break;
                case 8:
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
        Tar tar = new Tar(path);
        tar.expand();
        return tar;
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
        for (Files f : tar.filesList) {
            System.out.printf("%-50s : %s bytes %n", f.getFilename(), f.getFilesize());
            totalsize += f.getFilesize();
        }
        System.out.printf("%-50S : %s bytes %n %n %n", "total size", totalsize);
    }

    // Llista els propietaris dels fitxers que hi ha dins el tar
    public static void owner(){
        // Recorrem la llista de fitxers que té el nostre objecte Tar imprimint per pantalla el propietari de cada fitxer
        for (Files f : tar.filesList) {
            System.out.printf("%-50s Propietari: %s %n", f.getFilename(), f.getFileowner());
        }
        System.out.printf("%n %n %n");
    }

    // Llista els grups dels fitxers que hi ha dins el tar
    public static void group(){
        // Recorrem la llista de fitxers que té el nostre objecte Tar imprimint per pantalla el grup de cada fitxer
        for (Files f : tar.filesList) {
            System.out.printf("%-50s Grup: %s %n", f.getFilename(), f.getFilegroup());
        }
        System.out.printf("%n %n %n");
    }

    //  Extreu un fitxer del tar a una destinació que li passem
    public static void extract(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.printf("%-5s %s %n", "1-", "Descomprimir a una ubicació nova.");
            System.out.printf("%-5s %s (%s) %n", "2-", "Descomprimir a una ubicació per defecte", tar.tarName);
            int decisio = sc.nextInt();
            String path = "";
            if(decisio == 1) {
                System.out.println("Introdueix la ubicació (Recorda acabar amb '/'): ");
                path = sc.next();
            } else if(decisio == 2) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < tar.tarName.length(); i++) {
                    if(tar.tarName.charAt(i) != '.') {
                        sb.append(tar.tarName.charAt(i));
                    } else break;
                }
                File file = new File(sb.toString());
                if (!file.exists()) {
                    if (file.mkdir()) {
                        System.out.println("Carpeta creada.");
                    } else {
                        System.out.println("Ja existeix una carpetaam aquest nom.");
                    }
                }
                sb.append('/');
                path = sb.toString();
            }

            // Recorrem els arxius i els anam descomprimint amb la ruta adequada
            for (Files f : tar.filesList) {
                OutputStream os = new FileOutputStream(path.concat(f.getFilename()));
                DataOutputStream dos = new DataOutputStream(os);
                dos.write(f.getContent());
                dos.close();
                os.close();
            }
            System.out.println("Descompressió realitzada amb éxit.");
        } catch (Exception e){
            System.out.println("Algo ha anat malament. Torna-ho a intentar un altre pic.");
            e.printStackTrace();
        }
    }

    // Obri un arxiu inclós al tar
    public static void run() {
        try{
            Scanner sc =  new Scanner(System.in);
            System.out.print("Introdueix el nom de l'arxiu que vols obrir: ");
            String name = sc.nextLine();
            String path = "";

            // Descomprimim l'arxiu amb el mateix nombre
            for (Files f : tar.filesList) {
                if (name.equals(f.getFilename())) {
                    StringBuilder sb = new StringBuilder();
                    int counter = 0;
                    for (int i = tar.tarName.length() - 1; i > 0; i--) {
                        if (tar.tarName.charAt(i) == '/' || tar.tarName.charAt(i) == '\\'){
                            break;
                        } else {
                            counter++;
                        }
                    }
                    path = tar.tarName.substring(0, tar.tarName.length() - counter).concat(f.getFilename());

                    OutputStream os = new FileOutputStream(path);
                    DataOutputStream dos = new DataOutputStream(os);
                    dos.write(f.getContent());
                    dos.close();
                    os.close();
                    break;
                }
            }
            if(path.equals("")) {
                System.out.println("L'arxiu no s'ha trobat.");
            }

            // Executam el arxiu
            File file = new File(path);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);

            // Eliminam l'arxiu
            //file.delete();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
