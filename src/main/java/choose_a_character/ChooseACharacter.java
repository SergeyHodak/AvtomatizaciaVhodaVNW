package choose_a_character;

import cursor_move.Click;
import cursor_move.DirectHypotenuse;
import feature.ReadFile;
import feature.SearchForAFragmentWithinTheSpecifiedTime;
import keyboard.Keyboard;

public class ChooseACharacter {
    public ChooseACharacter() {
        init();
    }

    private void init() {
        SearchForAFragmentWithinTheSpecifiedTime fragmentSearch = new SearchForAFragmentWithinTheSpecifiedTime();
        DirectHypotenuse directHypotenuse = new DirectHypotenuse();
        Click click = new Click();
        ReadFile readFile = new ReadFile();
        Keyboard keyboard = new Keyboard();

        // зробити вікно активним
        int[] xyByLauncher; // коорденати іконки
        do {
            xyByLauncher = fragmentSearch.get(
                    "запущений лаунчер але його вікно не активне без курсора над іконкою 2022.10.10.png",
                    2
            ); // координати іконки лаунчера
        } while (xyByLauncher[2] != 1);
        directHypotenuse.moveCursorAlongStraightLineInHypotenuse(xyByLauncher[0], xyByLauncher[1]); // пересунути туди курсор
        click.clickLMB(); // клацнути там ЛКМ

        //обрати персонажа
        String text = readFile.readFile("person_1.txt");
        String[] rows = readFile.parserRows(text);
        int[] xyCharacter;
        do {
            xyCharacter = fragmentSearch.get(
                    rows[2] + ".png",
                    1
            );
            if (xyCharacter[2] != 1) {
                keyboard.pressArrowDown();
            }
        } while (xyCharacter[2] != 1);

        // натиснути ентер
        keyboard.pressEnter();
    }
}
