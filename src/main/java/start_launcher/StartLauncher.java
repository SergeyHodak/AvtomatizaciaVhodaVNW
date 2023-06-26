package start_launcher;

import cursor_move.Click;
import cursor_move.DirectHypotenuse;
import feature.SearchForAFragmentWithinTheSpecifiedTime;
import keyboard.CopyTextInBuffer;
import keyboard.Keyboard;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/** пройти стартове вікно, в якому може бути підтягнуте оновлення гри, до наступного вікна */
public class StartLauncher {
    public StartLauncher() {
        init(); // запустити метод при створенні цього класу.
    }

    // посилання на ярлик гри
    static final String A_LINK_TO_THE_GAME_LAUNCH_ICON = "C:/Users/Serge/Desktop/Сломалось что то, нужно править.lnk";

    public void init() {

        // для руху прямою гіпотенузою
        DirectHypotenuse directHypotenuse = new DirectHypotenuse();

        // для клацання лівою кнопкою миші
        Click click = new Click();

        // для виконання комбінованих дій з клавіатури
        Keyboard keyboard = new Keyboard();

        // для запису в буфер текстової інформації по вказаному параметру
        CopyTextInBuffer copyTextInBuffer = new CopyTextInBuffer();


        SearchForAFragmentWithinTheSpecifiedTime fragmentSearch = new SearchForAFragmentWithinTheSpecifiedTime();

        int[] xyByLauncher; // координати іконки запущеного лаунчера
        do {
            startCommand(); // запуск лаунчера
            xyByLauncher = fragmentSearch.get(
                    "запущений лаунчер але його вікно не активне без курсора над іконкою 2022.10.10.png",
                    5
            ); // координати іконки лаунчера
        } while (xyByLauncher[2] != 1);
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyByLauncher[0], xyByLauncher[1]); // пересунути туди курсор
        click.clickLMB(); // клацнути там ЛКМ
        int[] xyLogin = fragmentSearch.get(
                "поле вводу логіна 2022.10.11.png",
                2
        ); // коорденати поля вводу логіна

        // TODO. вікно блокує, всі робо дії. коли воно активне.
        // TODO. потрібно запустити інтеледжі від адміна, та активувати рух курсора за допомогою клавіатури
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyLogin[0] + 138, xyLogin[1] + 150); // пересунути туди курсор
        click.clickLMB(); // клацнути там ЛКМ
        keyboard.highlightAll(); // виділити там все
        copyTextInBuffer.copyTheTextToTheClipboard(CopyTextInBuffer.Type.login, "person_1.txt"); // копіювати в буфер логін
        keyboard.pasteFromBuffer(); // вставити з буфера
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyLogin[0] + 138, xyLogin[1] + 150 + 63); // пересунути курсор на поле вводу пароля
        click.clickLMB(); // клацнути там ЛКМ
        keyboard.highlightAll(); // виділити там все
        copyTextInBuffer.copyTheTextToTheClipboard(CopyTextInBuffer.Type.password, "person_1.txt"); // копіювати в буфер пароль
        keyboard.pasteFromBuffer(); // вставити з буфера
        keyboard.pressEnter(); // натиснути ентер
        int[] xyPlay = new int[3];
        while (xyPlay[2] == 0) {
            xyPlay = fragmentSearch.get(
                    "є кнопка грати.png",
                    1
            ); // координати кнопки грати
        }
        keyboard.pressEnter(); // натиснути ентер
        int[] xyExit; // коорденати іконки неактивного лаунчера
        do {
            xyExit = fragmentSearch.get(
                    "запущений лаунчер але його вікно не активне без курсора над іконкою 2022.10.10.png",
                    2
            ); // координати іконки лаунчера
        } while (xyExit[2] != 1);
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyExit[0], xyExit[1]); // пересунути курсор на вже неактивну іконку лаунчера щоб він згорнувся

        // зачикати
        try {
            Thread.sleep(30000); // 30 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** запуск ярлика */
    private void startCommand() {
        try {
            File file = new File(A_LINK_TO_THE_GAME_LAUNCH_ICON); // задати розташування файлу
            Desktop.getDesktop().open(file); // виконати запуск файлу за вказаною адресою
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
