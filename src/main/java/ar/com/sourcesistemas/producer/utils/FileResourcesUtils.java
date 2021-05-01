package ar.com.sourcesistemas.producer.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FileResourcesUtils {

    private List<String> allLines;
    public  FileResourcesUtils(String fileName) throws IOException, URISyntaxException {
        System.out.println("getResourceAsStream : " + fileName);
        allLines = new ArrayList<>();
        getFileFromResource(fileName);
    }

    private void getFileFromResource(String fileName) throws URISyntaxException{

        InputStream in = getClass().getResourceAsStream("/"+fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                allLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // print a file
    public List<String> getLines() {
        if (allLines.size() > 0) {
            return allLines;
        }else {
            System.exit(3);
        }
        return null;
    }

}