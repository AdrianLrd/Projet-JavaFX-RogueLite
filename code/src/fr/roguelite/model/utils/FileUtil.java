package fr.roguelite.model.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    /**
     * Create a new file if it does not exists
     * @param path The path to the file
     */
    public static void createIfDoesNotExists(String path) {
        try {
            File file = new File(path);
            if(file.exists()) return;

            if(file.getParentFile() != null) file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the content of the given file
     * @param path The path to the file
     * @return The content of the file
     */
    public static String readFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        return "";
    }

    /**
     * Save the content of the given file
     * @param path The path to the file
     * @param content The content of the file
     */
    public static void saveFile(String path, String content) {
        try {
            createIfDoesNotExists(path);

            Files.writeString(Paths.get(path), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
