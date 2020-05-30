import java.util.Scanner;

public class Main {
    private Tar tar;

    // Programa que empra la clase Tar
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Benvolgut. Quina operació vols fer?");
        System.out.printf("%-5s %s %s \n", "1-", "Carregar un fitxer tar a memòria.", "(RECOMANABLE EL PRIMER PIC)");
        System.out.printf("%-5s %s \n", "2-", "Llistar els nombres dels arxius que hi ha dins el fitxer.");
        System.out.printf("%-5s %s \n", "3-", "Conéixer el tamany dels arxius");
        System.out.printf("%-5s %s \n", "4-", "Conéixer el propietari dels arxius");
        System.out.printf("%-5s %s \n", "5-", "Conéixer el grup dels arxius");
        System.out.printf("%-5s %s \n", "6-", "Extreure el fitxer a una destinació");

        int decisio = sc.nextInt();
        switch(decisio) {
            case 1:
                load();
                break;
            case 2:
                list();
                break;
            case 3:
                size();
            case 4:
                owner();
                break;
            case 5:
                group();
                break;
            case 6:
                extract();
                break;
        }
    }

    // Carrega l'arxiu desde la ruta que ens proporciona l'usuari
    public void load(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix la ruta del fitxer: ");
        String path = sc.next();
        this.tar = new Tar(path);
        this.tar.expand();
        System.out.println("Fitxer carregat amb éxit. \n \n \n");
    }

    // Llista els fitxers que hi ha dins el tar
    public void list(){
        String[] list = this.tar.list();
        for (String s : list) {
            System.out.println(s);
        }
    }

    // Llista els tamanys dels fitxers que hi ha dins el tar
    public void size(){}

    // Llista els propietaris dels fitxers que hi ha dins el tar
    public void owner(){}

    // Llista els grups dels fitxers que hi ha dins el tar
    public void group(){}

    //  Extreu un fitxer del tar a una destinació que li passem
    public void extract(){}
}
