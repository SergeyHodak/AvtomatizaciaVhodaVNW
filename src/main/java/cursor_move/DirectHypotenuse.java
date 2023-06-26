package cursor_move;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class DirectHypotenuse {

    // пряме переміщення курсору гіпотенузою
    public void moveCursorAlongStraightLineInHypotenuse(int setX, int setY, String windowTitle) {
        boolean flag = true; // датчик завершення переміщення (не завершено)
        double a = 0, b = 0;  // датчик реагування на вплив користувача під час переміщення курсору
        while (flag) { // виконувати поки датчик не зафіксував завершення переміщення
            int[] cursorGlobalPosition = getCursorGlobalPosition();
            double x = cursorGlobalPosition[0]; // поточне положення курсору по ширині
            double y = cursorGlobalPosition[1]; // поточне положення курсору по висоті
            if (x == setX & y == setY) {  // якщо курсор в потрібному положенні
                flag = false; // активувати датчик
            } else { // курсор потрібно перемістити
                if (x == (int) a & y == (int) b) { // користувач не впливає на курсор під час програмного руху
                    double distance_on_x = setX - x; // відстань по Х між зараз та потрібно
                    double distance_on_y = setY - y; // відстань по У між зараз та потрібно
                    double step = Math.sqrt(Math.pow(distance_on_x, 2) + Math.pow(distance_on_y, 2)); // скільки потрібно виконати кроків до кінцевого призначення
                    a += distance_on_x / step;
                    b += distance_on_y / step; // перемістити курсор на один крок
//                  moveCursorWithKeys((int) x, (int) y, (int) a, (int) b); // встановити курсор (кнопками)
//                  setCursorInGlobalPosition((int) a, (int) b); // встановити курсор (емулятор миші)
                    setCursorJNA((int) a, (int) b, windowTitle);
                } else { // вмішався користувач чи це початок руху
                    a = x;
                    b = y;
                }
            }
        }
    }

    // отримати глобальні координати знаходження курсору
    public int[] getCursorGlobalPosition() {
        int[] result = new int[2];
        result[0] = MouseInfo.getPointerInfo().getLocation().x; // ширина
        result[1] = MouseInfo.getPointerInfo().getLocation().y; // висота
        return result;
    }

    // встановити глобальну позицію курсора, за допомогою бібліотеки "awt"
    public void setCursorInGlobalPosition(int setX, int setY) {
        try {
            new Robot().mouseMove(setX, setY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    //TODO. Для запуску режиму емуляції, натисніть послідовне поєднання клавіш: Left Alt + Left Shift + NumLock.
    private void moveCursorWithKeys(int startX, int startY, int finishX, int finishY) {
        try {
            Robot robot = new Robot();
            if (startX > finishX & startY > finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD7);
                robot.keyRelease(KeyEvent.VK_NUMPAD7);
            } else if (startX == finishX & startY > finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD8);
                robot.keyRelease(KeyEvent.VK_NUMPAD8);
            } else if (startX < finishX & startY > finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD9);
                robot.keyRelease(KeyEvent.VK_NUMPAD9);
            } else if (startX > finishX & startY == finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD4);
                robot.keyRelease(KeyEvent.VK_NUMPAD4);
            } else if (startX < finishX & startY == finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD6);
                robot.keyRelease(KeyEvent.VK_NUMPAD6);
            } else if (startX > finishX & startY < finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD1);
                robot.keyRelease(KeyEvent.VK_NUMPAD1);
            } else if (startX == finishX & startY < finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD2);
                robot.keyRelease(KeyEvent.VK_NUMPAD2);
            } else if (startX < finishX & startY < finishY) {
                robot.keyPress(KeyEvent.VK_NUMPAD3);
                robot.keyRelease(KeyEvent.VK_NUMPAD3);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    static final String DESKTOP_TITLE = "Desktop"; // назва вікна робочого столу
    static final String UPDATE_LAUNCHER = "Neverwinter"; // назва першого вікна входу, через який завантажуються оновлення

    /** за назвою вказаного імені вікна, його робить активним, а курсор встановлює за глобальними координатами */
    // встановити курсор в координати, за допомогою бібліотек "jna" та "jna-platform"
    private static void setCursorJNA(int x, int y, String windowTitle) {
        if (Platform.isWindows()) { // якщо використовувана система є віндовсом
            User32 user32 = User32.INSTANCE; // екземпляр для доступу до взаємодії з системою
            WinDef.HWND hwnd = // вказівник на вікно
                    windowTitle.equals(DESKTOP_TITLE) ? user32.GetDesktopWindow() // якщо робочий стіл
                            : user32.FindWindow(null, windowTitle); // інші вікна
            if (hwnd != null && hwnd.getPointer() != null) { // перевірка, чи вдалося знайти вікно
                user32.SetCursorPos(x, y); // перемістити курсор по екрану, у вказані координати
                user32.UpdateWindow(hwnd); // оновлює вказане раніше вікно
                user32.SetForegroundWindow(hwnd); // виводить вказане вікно на передній план
            } else {
                System.out.println("Вікно \"" + windowTitle + "\" не знайдено.");
            }
        } else {
            System.out.println("Система не є Windows");
        }
    }

    // отримати список назв активних вікон
    public List<String> getTitle() {
        List<String> result = new ArrayList<>();
        User32 user32 = User32.INSTANCE; // екземпляр для доступу до взаємодії з системою
        user32.EnumWindows((hwnd, pointer) -> {
            if (user32.IsWindowVisible(hwnd)) {
                char[] windowText = new char[512];
                user32.GetWindowText(hwnd, windowText, 512);
                String windowTitle = Native.toString(windowText).trim();
                if (!windowTitle.isEmpty()) {
                    result.add(windowTitle);
                }
            }
            return true;
        }, null);
        return result;
    }

    // розширення масиву з текстових одиниць
    public String[] expandTextArray(String[] input, String text) {
        String[] output = new String[input.length + 1]; // створити новий масив на 1 більше вхідного
        System.arraycopy(input, 0, output, 0, input.length); // заповнити вихідний вхідним
        output[output.length - 1] = text; // додати в останню позицію новий запис
        return output;
    }

    // отримати розмір екрану
    public int[] getScreenSizes() {
        int[] result = new int[2];
        result[0] = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(); // ширина
        result[1] = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight(); // висота
        return result;
    }
}