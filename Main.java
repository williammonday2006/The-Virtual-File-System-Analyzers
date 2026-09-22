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

        int recursiveCount = FileSystemAnalyzer.countFilesRecursive(root);
        int iterativeCount = FileSystemAnalyzer.countFilesIterative(root);

        System.out.println("Recursive file count: " + recursiveCount);
        System.out.println("Iterative file count: " + iterativeCount);

        if (recursiveCount == iterativeCount) {
            System.out.println("Verification: Counts match.");
        } else {
            System.out.println("Verification: Counts do not match.");
        }
    }
}