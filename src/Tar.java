import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Tar {
    String filename;

    // Constructor
    public Tar(String filename) {
        this.filename = filename;
    }

    // Torna un array amb la llista de fitxers que hi ha dins el TAR
    public String[] list() throws Exception {
        List<String> list = new ArrayList<String>();
        RandomAccessFile raf = new RandomAccessFile(this.filename, "r");
        StringBuilder sb = new StringBuilder();
        String fileName;
        int pointer = 0;

        while (true) {
            // Trobam la mida del fitxer per saber com de gran es el content
            raf.seek(pointer + 124);
            for (int i = 0; i < 11; i++) {
                byte b = raf.readByte();
                sb.append(b - 48);
            }
            String fileSize = sb.toString();
            sb.delete(0, sb.length());
            long endOfFile = Integer.parseInt(fileSize, 8);
            // Trobam el nombre multiple de 512 més proper del tamany
            // de la imatge per saber on termina el content de cada imatge.
            for (int i = 0; i < 512; i++) {
                if(endOfFile % 512 != 0) {
                    endOfFile++;
                } else {
                    endOfFile += 512;
                    break;
                }
            }

            // Trobam el nom del fitxer
            raf.seek(pointer);
            for (int i = 0; i < 100; i++) {
                byte b = raf.readByte();
                sb.append((char) b);
            }
            fileName = sb.toString();
            sb.delete(0, sb.length());
            list.add(fileName);
            raf.seek(pointer);

            pointer += endOfFile;
        }

        //raf.close();
        //String[] listArray;
        //list.toArray(listArray);
        //return listArray;

    }

    // Torna un array de bytes amb el contingut del fitxer que té per nom
    // igual a l'String «name» que passem per paràmetre
    public byte[] getBytes(String name) {
        return null;
    }

    // Expandeix el fitxer TAR dins la memòria
    public void expand() throws Exception {
        //228065
        RandomAccessFile raf = new RandomAccessFile(this.filename, "r");
        raf.seek(15360);
        while (true) {
            byte s = raf.readByte();
            System.out.println(s);
        }
    }
}

