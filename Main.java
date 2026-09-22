public class Main {

    public static void main(String[] args) {
        Folder root = new Folder("Root");

        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder vacations = new Folder("Vacations");

        documents.addItem(new FileItem("Resume.pdf", 250));
        documents.addItem(new FileItem("Notes.txt", 50));

        pictures.addItem(new FileItem("Profile.png", 500));
        pictures.addItem(vacations);

        vacations.addItem(new FileItem("Beach.jpg", 1200));
        vacations.addItem(new FileItem("Mountain.jpg", 1800));
        vacations.addItem(new FileItem("Family.jpg", 900));

        root.addItem(documents);
        root.addItem(pictures);

        FileSystemAnalyzer.printHierarchy(root, "");

        int fileCount = FileSystemAnalyzer.countFilesRecursive(root);
        int totalSize = FileSystemAnalyzer.calculateTotalSizeRecursive(root);
        FileItem largest = FileSystemAnalyzer.findLargestFileRecursive(root);

        System.out.println();
        System.out.println("Total files: " + fileCount);
        System.out.println("Total storage: " + totalSize + " KB");

        if (largest != null) {
            System.out.println("Largest file: " + largest.getName());
            System.out.println("Largest file size: " + largest.getSizeInKB() + " KB");
        } else {
            System.out.println("No files found.");
        }
    }
}