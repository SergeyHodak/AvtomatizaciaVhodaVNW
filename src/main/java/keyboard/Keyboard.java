package keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Keyboard {
    public void highlightAll() { // виділити все
        try {
            Robot robot = new Robot();
            robot.delay(100); // затримка
            robot.keyPress(KeyEvent.VK_CONTROL); // зажать контрл
                robot.delay(100); // затримка
                robot.keyPress(KeyEvent.VK_A); // зажать 'a'
                robot.delay(500); // затримка
                robot.keyRelease(KeyEvent.VK_A); // відпустити 'a'
                robot.delay(100); // затримка
            robot.keyRelease(KeyEvent.VK_CONTROL); // відпустити контрл
            robot.delay(100); // затримка
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void pasteFromBuffer() { // вставити все що є в буфері
        try {
            Robot robot = new Robot();
            robot.delay(100); // затримка
            robot.keyPress(KeyEvent.VK_CONTROL); // зажать контрл
            robot.delay(100); // затримка
            robot.keyPress(KeyEvent.VK_V); // зажать 'v'
            robot.delay(500); // затримка
            robot.keyRelease(KeyEvent.VK_V); // відпустити 'v'
            robot.delay(100); // затримка
            robot.keyRelease(KeyEvent.VK_CONTROL); // відпустити контрл
            robot.delay(100); // затримка
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void pressEnter() { // натиснути ентер
        try {
            Robot robot = new Robot();
            robot.delay(100); // затримка
            robot.keyPress(KeyEvent.VK_ENTER); // зажать ентер
            robot.delay(500); // затримка
            robot.keyRelease(KeyEvent.VK_ENTER); // відпустити ентер
            robot.delay(100); // затримка
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
