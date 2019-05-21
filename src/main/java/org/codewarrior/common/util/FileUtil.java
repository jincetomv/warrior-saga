package org.codewarrior.common.util;

import java.io.*;

public class FileUtil {
    private FileUtil() {
    }

    public static void createDirectory(final String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

    }

    public static void saveObject(final Object object, String path, String name) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = path.concat("/").concat(name);
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (fos != null) {
                fos.close();
            }


        }


    }

    public static <T> T loadSavedObject(String filePath, Class<T> objectType) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException(String.format("No such file found : %s ", filePath));
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            return (T) ois.readObject();


        } catch (ClassNotFoundException ex)

        {
            throw ex;
        } finally

        {
            ois.close();
            fis.close();


        }


    }
}
