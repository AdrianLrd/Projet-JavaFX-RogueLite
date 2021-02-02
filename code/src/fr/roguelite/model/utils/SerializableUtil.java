package fr.roguelite.model.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SerializableUtil {
    /**
     * Serialize an Objet that implements Serializable
     * @param obj The Object to serialize
     * @return The serialized String
     */
    public static String serialize(Object obj) {
        try (
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)
        ) {
            outputStream.writeObject(obj);

            return new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serialize a String
     * @param string The serialized String
     * @return The deserialized object
     */
    public static Object deserialize(String string) {
        byte[] data = Base64.getDecoder().decode(string);

        try (
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream)
        ) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
