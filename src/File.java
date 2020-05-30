public class File {
    private String filename;
    private int filemode;
    private String fileowner;
    private String filegroup;
    private long filesize;
    private long fileLastModification;
    private int fileChecksum;
    private short fileLinkIndicator;
    private String linkedFileName;
    private byte[] content;

    public File(String filename, int filemode, String fileowner, String filegroup, long filesize, long fileLastModification, int fileChecksum, short fileLinkIndicator, String linkedFileName, byte[] content) {
        this.filename = filename;
        this.filemode = filemode;
        this.fileowner = fileowner;
        this.filegroup = filegroup;
        this.filesize = filesize;
        this.fileLastModification = fileLastModification;
        this.fileChecksum = fileChecksum;
        this.fileLinkIndicator = fileLinkIndicator;
        this.linkedFileName = linkedFileName;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getFilemode() {
        return filemode;
    }

    public void setFilemode(int filemode) {
        this.filemode = filemode;
    }

    public String getFileowner() {
        return fileowner;
    }

    public void setFileowner(String fileowner) {
        this.fileowner = fileowner;
    }

    public String getFilegroup() {
        return filegroup;
    }

    public void setFilegroup(String filegroup) {
        this.filegroup = filegroup;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public long getFileLastModification() {
        return fileLastModification;
    }

    public void setFileLastModification(long fileLastModification) {
        this.fileLastModification = fileLastModification;
    }

    public int getFileChecksum() {
        return fileChecksum;
    }

    public void setFileChecksum(int fileChecksum) {
        this.fileChecksum = fileChecksum;
    }

    public short getFileLinkIndicator() {
        return fileLinkIndicator;
    }

    public void setFileLinkIndicator(short fileLinkIndicator) {
        this.fileLinkIndicator = fileLinkIndicator;
    }

    public String getLinkedFileName() {
        return linkedFileName;
    }

    public void setLinkedFileName(String linkedFileName) {
        this.linkedFileName = linkedFileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
