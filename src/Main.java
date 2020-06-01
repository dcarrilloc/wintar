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
                    list();
                    break;
                case 3:
                    size();
                    break;
                case 4:
                    owner();
                    break;
                case 5:
                    group();
                    break;
                case 6:
                    extract();
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
        String[] list = tar.list();
        for (String s : list) {
            System.out.println(s);
        }
    }

    // Llista els tamanys dels fitxers que hi ha dins el tar
    public static void size(){
        long totalsize = 0;
        for (File f : tar.fileList) {
            System.out.printf("%-50s : %s bytes %n", f.getFilename(), f.getFilesize());
            totalsize += f.getFilesize();
        }
        System.out.printf("%-50S : %s bytes %n %n %n", "total size", totalsize);
    }

    // Llista els propietaris dels fitxers que hi ha dins el tar
    public static void owner(){}

    // Llista els grups dels fitxers que hi ha dins el tar
    public static void group(){}

    //  Extreu un fitxer del tar a una destinació que li passem
    public static void extract(){}
}
