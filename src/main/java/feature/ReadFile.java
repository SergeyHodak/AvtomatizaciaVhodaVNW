package feature;

import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public String readFile(String fileName) {
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String[] parserRows(String text) { // парсинг тексту по рядкам
        String[] split = text.split("\n");
        String[] result = new String[0];
        for (String s : split) {
            String strip = s.strip();
            if (!strip.equals("")) {
                String[] buffer = result.clone();
                result = new String[result.length+1];
                for (int i = 0; i < result.length; i++) {
                    if (i != result.length-1) {
                        result[i] = buffer[i];
                    } else {
                        result[i] = strip;
                    }
                }
            }
        }
        return result;
    }
}
