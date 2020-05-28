public class File {
    private String fileName;
    private 
    private long size;
    private long endOfFile;

    public File(String name, long size, long endOfFile){
        this.name = name;
        this.size = size;
        this.endOfFile = endOfFile;
    }

    public String getName() {
        return name;
    }

    public long getEndOfFile() {
        return endOfFile;
    }

    public long getSize() {
        return size;
    }
}
