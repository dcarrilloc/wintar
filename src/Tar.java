import java.io.RandomAccessFile;
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
        List<String> list = new ArrayList<String>();
        List<File> fileList = this.fileList;

        for (File file : fileList) {
            String name = file.getName();
            list.add(name);
        }

        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

    // Torna un array de bytes amb el contingut del fitxer que té per nom
    // igual a l'String «name» que passem per paràmetre
    public byte[] getBytes(String name) throws Exception {
        RandomAccessFile rac = new RandomAccessFile(this.filename, "r");
        List<Byte> list = new ArrayList<Byte>();
        File file = null;
        int counter = 0;
        Iterator<File> it = this.fileList.iterator();
        // Cercam l'arxiu a la llista
        while(it.hasNext()){
            if(it.next().name.equals(name)) {
                file = it.next();
                break;
            }
        }
        // No s'ha trobat cap arxiu
        if (file == null) {
            return null;
        }

        rac.seek(file.endOfFile - file.size);
        while (counter < file.size - 512) {
            Byte b = rac.readByte();
            list.add(b);
            counter += 2;
        }

        // Passam la llista de Bytes[] a un array de bytes[]
        Byte[] aux = (Byte[]) list.toArray();
        byte[] result = new byte[list.size()];

        int i = 0;
        for (Byte b: aux) {
            result[i++] = b;
        }

        return result;
    }

    // Expandeix el fitxer TAR dins la memòria
    public void expand() throws Exception {
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
            if (fileSize.equals("-48-48-48-48-48-48-48-48-48-48-48")) break;
            sb.delete(0, sb.length());
            long size = Integer.parseInt(fileSize, 8);
            long endOfFile = size;
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
                if (b != 0) {
                    sb.append((char) b);
                }
            }
            fileName = sb.toString();
            sb.delete(0, sb.length());
            raf.seek(pointer);
            pointer += endOfFile;

            File f = new File(fileName, size, endOfFile);
            fileList.add(f);
        }

        raf.close();
    }
}

