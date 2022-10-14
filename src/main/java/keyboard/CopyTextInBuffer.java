package keyboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;

public class CopyTextInBuffer {
    public void copyTheTextToTheClipboard(Type type, String fileName) {
        String data = "Щось не те!";
        switch (type) {
            case login: {
                data = ReadFile.readFile(fileName)[0]; // отримуємо логін з файлу
                break;
            }
            case password: {
                data = ReadFile.readFile(fileName)[1]; // отримуємо пароль з файлу
                break;
            }
        }
        // далі зберігаємо його в буфері
        StringSelection stringSelection = new StringSelection(data);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public enum Type {
        login, password
    }

    private static class ReadFile {
        public static String[] readFile(String fileName) {
            StringBuilder result = new StringBuilder();
            try (FileReader reader = new FileReader(fileName)) {
                int c;
                while ((c = reader.read()) != -1) {
                    result.append((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] rows = new String[2];
            StringBuilder row = new StringBuilder();
            int count = 0;
            for (byte symbol : result.toString().getBytes()) {
                if(count > 1) break;
                if ((char) symbol == '\n') {
                    rows[count++] = row.toString().strip();
                    row = new StringBuilder();
                } else {
                    row.append((char) symbol);
                }
            }
            return rows;
        }
    }
}
