package cursor_move;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class DirectHypotenuseTest {

    /** чи створюється потрібний масив, тобто на 1 більший, та має доданий запис вкінці */
    @Test
    void expandTextArray() {
        String[] array = {"test0", "test1", "test2"};
        String input = "test3";
        String[] output = {"test0", "test1", "test2", "test3"};
        String[] test = new DirectHypotenuse().expandTextArray(array, input);
        for (int i = 0; i < test.length; i++) {
            Assertions.assertEquals(output[i], test[i]);
        }
    }

    /** цей метод не дістає назв вікон, які в фоновому середовищі, йдеться про робочий стіл "Desktop" */
    @Test
    void getTitle() {
        List<String> title = new DirectHypotenuse().getTitle();
        Assertions.assertNotNull(title); // не повинно бути пустим, бо цю перевірку було запущено з якогось вікна
        System.out.println("title = " + title);
        //todo в моєму випаду 5 останніх в списку не є вікнами, вони десь на фоні
        // {Параметры, Параметры, Microsoft Store, Microsoft Store, Program Manager}
    }

    /** отримати розмір екрану, та він має бути не нульовим */
    @Test
    void getScreenSizes() {
        int[] screenSizes = new DirectHypotenuse().getScreenSizes();
        System.out.println("screenSizes = " + Arrays.toString(screenSizes));
        Assertions.assertTrue(screenSizes[0] > 0);
        Assertions.assertTrue(screenSizes[1] > 0);
    }
}