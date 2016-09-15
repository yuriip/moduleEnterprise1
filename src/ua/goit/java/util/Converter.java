package ua.goit.java.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    private final static int TO_BORDER = 3;

    public List<String> convertMapToString(LinkedHashMap<String, List<String>> resultTable) {
        int lengthArray = 0;
        for (Map.Entry<String, List<String>> entry : resultTable.entrySet()) {
            if (lengthArray < entry.getValue().size()) {
                lengthArray = entry.getValue().size();
            }
        }

        int[] maxWidthColumns = new int[lengthArray + 1];
        for (Map.Entry<String, List<String>> entry : resultTable.entrySet()) {
            int i = 0;
            if (maxWidthColumns[i] < entry.getKey().toString().length()) {
                maxWidthColumns[i] = entry.getKey().toString().length();
            }
            i++;
            for (String s : entry.getValue()) {
                if (maxWidthColumns[i] < s.length()) {
                    maxWidthColumns[i] = s.length();
                }
                i++;
            }
        }
        int lengthLine = 0;

        String stringFormat = "|%-";
        for (int length : maxWidthColumns) {
            lengthLine = lengthLine + length + TO_BORDER;
            stringFormat = stringFormat + (length + TO_BORDER) + "s|%-";
        }
        stringFormat = stringFormat.substring(0, stringFormat.length() - 2);

        String stringRow = null;

        int maxListsize = 0;
        for (Map.Entry<String, List<String>> entry : resultTable.entrySet()) {
            if (maxListsize < entry.getValue().size()) {
                maxListsize = entry.getValue().size();
            }
        }

        for (Map.Entry<String, List<String>> entry : resultTable.entrySet()) {
            if (entry.getValue().size() != maxListsize) {
                for (int i = entry.getValue().size(); i < maxListsize; i++){
                    entry.getValue().add(i, "");
                }
            }
        }


        String line = new String(new char[lengthLine + TO_BORDER * TO_BORDER]).replace("\0", "-");

        List<String> listRows = new ArrayList<>();

        listRows.add(line);
        for (Map.Entry<String, List<String>> entry : resultTable.entrySet()) {
            stringRow = String.format(stringFormat, entry.getKey().toString(),
                    entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2),
                    entry.getValue().get(3), entry.getValue().get(4), entry.getValue().get(5),
                    entry.getValue().get(6));
            listRows.add(stringRow);
            listRows.add(line);

        }

        return listRows;
    }
}
