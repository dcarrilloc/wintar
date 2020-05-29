import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Tar {
    String tarName;
    List<File> fileList = new ArrayList<File>();

    // Constructor
    public Tar(String tarName) {
        this.tarName = tarName;
    }

    // Torna un array amb la llista de fitxers que hi ha dins el TAR
    public String[] list(){
        String[] list = new String[this.fileList.size()];
        int counter = 0;
        for (File f: this.fileList) {
            list[counter] = f.getFilename();
            counter++;
        }
        return list;
    }

    // Torna un array de bytes amb el contingut del fitxer que té per nom
    // igual a l'String «name» que passem per paràmetre
    public byte[] getBytes(String name){
        byte[] content = null;
        for (File f:this.fileList) {
            if (f.getFilename().equals(name)) {
                content = f.getContent();
                break;
            }
        }
        return content;
    }

    // Expandeix el fitxer TAR dins la memòria
    public void expand() {
        try {
            InputStream is = new FileInputStream(this.tarName);
            DataInputStream dis = new DataInputStream(is);

            while(true) {
                String filename = new String(dis.readNBytes(100)).trim();
                if(filename.equals("")) break;
                int filemode = Integer.parseInt(new String(dis.readNBytes(8)).trim());
                String fileowner = new String(dis.readNBytes(8)).trim();
                String filegroup = new String(dis.readNBytes(8)).trim();
                long filesize = Integer.parseInt(new String(dis.readNBytes(12)).trim(), 8);
                long fileLastModification = Integer.parseInt(new String(dis.readNBytes(12)).trim(), 8);
                int fileChecksum = Integer.parseInt(new String(dis.readNBytes(8)).trim());
                short linkIndicator = Short.parseShort(new String(dis.readNBytes(1)));
                String linkedFileName = new String(dis.readNBytes(100)).trim();
                dis.skipBytes(255);
                byte[] content = dis.readNBytes((int) filesize);

                // Cream l'objecte File amb tota la informació recopilada anteriorment
                this.fileList.add(new File(filename, filemode, fileowner, filegroup, filesize, fileLastModification, fileChecksum, linkIndicator, linkedFileName, content));

                dis.skipBytes((int) (((Math.ceil(filesize / 512)) * 512) - filesize + 512));
            }
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer especificat no s'ha trobat.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Enhorabona! Has passat la prova. Vina a fer feina com a tester amb nosaltres. Envia CV a danielcarrillocardus@gmail.com");
            e.printStackTrace();
        }
    }
}
