package keyboard;

import feature.ReadFile;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

// зберегти в буфері інформацію
public class CopyTextInBuffer {

    public void copyTheTextToTheClipboard(Type type, String fileName) {
        ReadFile readFile = new ReadFile();
        String text = readFile.readFile(fileName);
        String[] rows = readFile.parserRows(text);
        String data = "Error reading from file";
        switch (type) {
            case login: {
                data = rows[0]; // отримуємо логін з файлу
                break;
            }
            case password: {
                data = rows[1]; // отримуємо пароль з файлу
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
}
