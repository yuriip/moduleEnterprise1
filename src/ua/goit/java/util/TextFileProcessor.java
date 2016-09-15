package ua.goit.java.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class TextFileProcessor {
    public void saveText(String filePath, List<List<String>> resultTables) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new FileOutputStream(filePath), Charset.forName("UTF-8") ))){

            for (List<String> list : resultTables) {
                for (String s : list) {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
