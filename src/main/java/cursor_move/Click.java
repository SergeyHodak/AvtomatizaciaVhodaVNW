package cursor_move;

import java.awt.*;
import java.awt.event.InputEvent;

public class Click {
    public void clickLMB() {
        try {
            Robot robot = new Robot();
            Thread.sleep(10);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // нажима кнопку миші
            Thread.sleep(33);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // відпуска кнопку миші
            Thread.sleep(100);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
