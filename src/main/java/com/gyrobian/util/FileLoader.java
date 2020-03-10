package com.gyrobian.util;

import java.io.*;

/**
 * Helps with loading files.
 */
public class FileLoader {

    /**
     * Reads a resource file from the classpath, and returns its contents as a string.
     * @param classPath The class path of the resource.
     * @return The string containing the contents of the file.
     * @throws IOException If an error occurred or the file could not be read.
     */
    public static String readResource(String classPath) throws IOException {
        String newLine = System.getProperty("line.separator");
        InputStream is = FileLoader.class.getClassLoader().getResourceAsStream(classPath);
        if (is == null) {
            throw new IOException("Could not open input stream to resource.");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String line; (line = reader.readLine()) != null; ) {
            result.append(flag? newLine: "").append(line);
            flag = true;
        }
        return result.toString();
    }

    /**
     * Reads a file into a string.
     * @param selectedFile The file object, as it is often selected by a JFileChooser.
     * @return The string containing the file, or an empty string.
     * @throws IOException If the file could not be read.
     */
    public static String readFile(File selectedFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();
    }
}
