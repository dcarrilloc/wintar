import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class Tar {
    String filename;
    List<File> fileList = new ArrayList<File>();

    // Constructor
    public Tar(String filename) {
        this.filename = filename;
    }

    // Torna un array amb la llista de fitxers que hi ha dins el TAR
    public String[] list() throws Exception {

        return null;
    }

    // Torna un array de bytes amb el contingut del fitxer que té per nom
    // igual a l'String «name» que passem per paràmetre
    public byte[] getBytes(String name) throws Exception {
        InputStream is = new FileInputStream(this.filename);
        DataInputStream dis = new DataInputStream(is);
        return null;
    }

    // Expandeix el fitxer TAR dins la memòria
    public void expand() throws Exception {

    }
}
