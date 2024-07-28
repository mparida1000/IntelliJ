package practice;
import java.io.File;
public class folderNames {
    public static void main(String[] args) {
        File lwcFolder = new File("/Users/mp1863/Documents/LWC/lwc");

        if (lwcFolder.exists() && lwcFolder.isDirectory()) {
            File[] subfolders = lwcFolder.listFiles(File::isDirectory);
            if (subfolders != null) {
                for (File folder : subfolders) {
                    File[] innerFolders = folder.listFiles(File::isDirectory);
                    if (innerFolders != null && innerFolders.length > 0) {
                        System.out.println("Folder: " + folder.getName());
                    }
                }
            } else {
                System.out.println("No subfolders found in the lwc folder.");
            }
        } else {
            System.out.println("The lwc folder does not exist or is not a directory.");
        }
    }
}
