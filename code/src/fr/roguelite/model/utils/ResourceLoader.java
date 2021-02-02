package fr.roguelite.model.utils;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TreeMap;

public class ResourceLoader {
    static TreeMap<String, Object> resources = new TreeMap<>();

    /**
     * Loads or gets the image, used to limit disk usage
     * @param location The path to the image
     * @return The image for the given path
     */
    public static Image getImage(String location) {
        if (!resources.containsKey(location)) {
            try {
                resources.put(location, new Image(new FileInputStream(location)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return (Image) resources.get(location);
    }
}
