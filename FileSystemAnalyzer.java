public class FileSystemAnalyzer {

    public static int countFilesRecursive(FileSystemItem item) {
        if (item instanceof FileItem) {
            return 1;
        }

        Folder folder = (Folder) item;
        int count = 0;

        for (FileSystemItem child : folder.getItems()) {
            count += countFilesRecursive(child);
        }

        return count;
    }

    public static int calculateTotalSizeRecursive(FileSystemItem item) {
        if (item instanceof FileItem) {
            return item.getSizeInKB();
        }

        Folder folder = (Folder) item;
        int total = 0;

        for (FileSystemItem child : folder.getItems()) {
            total += calculateTotalSizeRecursive(child);
        }

        return total;
    }

    public static FileItem findLargestFileRecursive(FileSystemItem item) {
        if (item instanceof FileItem) {
            return (FileItem) item;
        }

        Folder folder = (Folder) item;
        FileItem largest = null;

        for (FileSystemItem child : folder.getItems()) {
            FileItem childLargest = findLargestFileRecursive(child);

            if (childLargest != null &&
                (largest == null || childLargest.getSizeInKB() > largest.getSizeInKB())) {
                largest = childLargest;
            }
        }

        return largest;
    }

    public static void printHierarchy(FileSystemItem item, String indent) {
        if (item instanceof FileItem) {
            FileItem file = (FileItem) item;
            System.out.println(indent + file.getName() + " (" + file.getSizeInKB() + " KB)");
            return;
        }

        Folder folder = (Folder) item;
        System.out.println(indent + folder.getName() + "/");

        for (FileSystemItem child : folder.getItems()) {
            printHierarchy(child, indent + "  ");
        }
    }
}