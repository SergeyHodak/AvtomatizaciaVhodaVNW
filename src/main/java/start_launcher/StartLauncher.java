package start_launcher;

import cursor_move.Click;
import cursor_move.DirectHypotenuse;
import keyboard.CopyTextInBuffer;
import keyboard.Keyboard;
import screen.Screen;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class StartLauncher {
    public StartLauncher() {
        init();
    }

    public void init() {
        DirectHypotenuse directHypotenuse = new DirectHypotenuse();
        Click click = new Click();
        Keyboard keyboard = new Keyboard();
        CopyTextInBuffer copyTextInBuffer = new CopyTextInBuffer();

        int[] xyByLauncher; // коорденати іконки запущеного лаунчера
        do {
            startCommand(); // запуск лаунчера
            xyByLauncher = fragmentSearch(
                    "запущений лаунчер але його вікно не активне без курсора над іконкою 2022.10.10.png",
                    5
            ); // координати іконки лаунчера
        } while (xyByLauncher[2] != 1);
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyByLauncher[0], xyByLauncher[1], false); // пересунути туди курсор
        click.clickLMB(); // клацнути там ЛКМ
        int[] xyLogin = fragmentSearch(
                "поле вводу логіна 2022.10.11.png",
                2
        ); // коорденати поля вводу логіна

        // TODO. вікно блокує, всі робо дії. коли воно активне.
        // TODO. потрібно запустити інтеледжі від адміна, та активувати рух курсора за допомогою клавіатури
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyLogin[0] + 138, xyLogin[1] + 150, true); // пересунути туди курсор
        click.clickLMB(); // клацнути там ЛКМ
        keyboard.highlightAll(); // виділити там все
        copyTextInBuffer.copyTheTextToTheClipboard(CopyTextInBuffer.Type.login, "person_1.txt"); // копіювати в буфер логін
        keyboard.pasteFromBuffer(); // вставити з буфера
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyLogin[0] + 138, xyLogin[1] + 150 + 63, true); // пересунути туди курсор
        click.clickLMB(); // клацнути там ЛКМ
        keyboard.highlightAll(); // виділити там все
        copyTextInBuffer.copyTheTextToTheClipboard(CopyTextInBuffer.Type.password, "person_1.txt"); // копіювати в буфер пароль
        keyboard.pasteFromBuffer(); // вставити з буфера
        keyboard.pressEnter(); // натиснути ентер
        int[] xyPlay = new int[3];
        while (xyPlay[2] == 0) {
            xyPlay = fragmentSearch(
                    "є кнопка грати.png",
                    1
            ); // координати кнопки грати
        }
        keyboard.pressEnter(); // натиснути ентер
    }

    private void startCommand() { // запуск лаунчера
        try {
            File file=new File("C:/Users/Serge/Desktop/Сломалось что то, нужно править.lnk");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] fragmentSearch(String filename, int waitMinutes) {
        Screen screen = new Screen();
        LocalDateTime timeStart = LocalDateTime.now(); // час від запуску
        int[] result;
        while (true) {
            result = screen.getXYByImage(filename); // знайти фрагмент зображення
            if (result[2] == 1) { // якщо є відповідність
                break;
            }
            if (timeStart.plusMinutes(waitMinutes).isBefore(LocalDateTime.now())) {
                break;
            }
            try {
                Thread.sleep(100); // почикати 100 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
