import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Folder root = createSampleFileSystem();

        boolean running = true;

        while (running) {
            printMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayStructure(root);
                    break;
                case "2":
                    addFile(root);
                    break;
                case "3":
                    addSubfolder(root);
                    break;
                case "4":
                    runRecursiveAudit(root);
                    break;
                case "5":
                    runIterativeAudit(root);
                    break;
                case "6":
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid menu choice.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static Folder createSampleFileSystem() {
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

        return root;
    }

    private static void printMenu() {
        System.out.println("===== Virtual File System Analyzer =====");
        System.out.println("1. Display File System Structure");
        System.out.println("2. Add File to a Folder");
        System.out.println("3. Add Subfolder");
        System.out.println("4. Run Recursive Audit");
        System.out.println("5. Run Iterative Audit & Verification");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void displayStructure(Folder root) {
        System.out.println("\nFile System Structure:");
        FileSystemAnalyzer.printHierarchy(root, "");
    }

    private static void addFile(Folder root) {
        System.out.print("Enter file name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("File name cannot be empty.");
            return;
        }

        System.out.print("Enter file size in KB: ");
        Integer size = readInteger();

        if (size == null || size < 0) {
            System.out.println("File size must be a non-negative integer.");
            return;
        }

        System.out.print("Enter target folder name: ");
        String folderName = scanner.nextLine().trim();

        Folder folder = FileSystemAnalyzer.findFolder(root, folderName);

        if (folder == null) {
            System.out.println("Folder not found.");
            return;
        }

        folder.addItem(new FileItem(name, size));
        System.out.println("File added successfully.");
    }

    private static void addSubfolder(Folder root) {
        System.out.print("Enter new folder name: ");
        String folderName = scanner.nextLine().trim();

        if (folderName.isEmpty()) {
            System.out.println("Folder name cannot be empty.");
            return;
        }

        System.out.print("Enter parent folder name: ");
        String parentName = scanner.nextLine().trim();

        Folder parent = FileSystemAnalyzer.findFolder(root, parentName);

        if (parent == null) {
            System.out.println("Parent folder not found.");
            return;
        }

        parent.addItem(new Folder(folderName));
        System.out.println("Folder added successfully.");
    }

    private static void runRecursiveAudit(Folder root) {
        int fileCount = FileSystemAnalyzer.countFilesRecursive(root);
        int totalSize = FileSystemAnalyzer.calculateTotalSizeRecursive(root);
        FileItem largest = FileSystemAnalyzer.findLargestFileRecursive(root);

        System.out.println("\n===== Recursive Audit =====");
        System.out.println("File count: " + fileCount);
        System.out.println("Total storage: " + totalSize + " KB");

        if (largest == null) {
            System.out.println("Largest file: No files found.");
        } else {
            System.out.println(
                "Largest file: " +
                largest.getName() +
                " (" +
                largest.getSizeInKB() +
                " KB)"
            );
        }
    }

    private static void runIterativeAudit(Folder root) {
        int recursiveCount = FileSystemAnalyzer.countFilesRecursive(root);
        int iterativeCount = FileSystemAnalyzer.countFilesIterative(root);

        System.out.println("\n===== Iterative Audit =====");
        System.out.println("Iterative file count: " + iterativeCount);
        System.out.println("Recursive file count: " + recursiveCount);

        if (recursiveCount == iterativeCount) {
            System.out.println("Verification: Counts match.");
        } else {
            System.out.println("Verification: Counts do not match.");
        }
    }

    private static Integer readInteger() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}