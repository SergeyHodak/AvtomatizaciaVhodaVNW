package cursor_move;

import random.RandomInt;

import java.awt.*;
import java.awt.event.InputEvent;

// Клік
public class Click {

    // лівою кнопкою миші
    public void clickLMB() {
        try {
            Robot robot = new Robot();
            RandomInt delay = new RandomInt(); // випадкові числа

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // затиснути кнопку миші
            Thread.sleep(delay.getInt(100, 200)); // затримка

            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // відпустити кнопку миші
            Thread.sleep(delay.getInt(100, 200)); // затримка

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
