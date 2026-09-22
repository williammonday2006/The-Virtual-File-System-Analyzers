public class FileItem implements FileSystemItem {
    private String name;
    private int sizeInKB;

    public FileItem(String name, int sizeInKB) {
        this.name = name;
        this.sizeInKB = sizeInKB;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSizeInKB() {
        return sizeInKB;
    }
}