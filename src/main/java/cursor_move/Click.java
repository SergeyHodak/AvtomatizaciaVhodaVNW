package cursor_move;

import java.awt.*;
import java.awt.event.InputEvent;

public class Click {
    public void clickLMB() {
        try {
            Robot robot = new Robot();
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // нажима кнопку миші
            robot.delay(500); // затримка
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // відпуска кнопку миші
            robot.delay(500); // затримка
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
