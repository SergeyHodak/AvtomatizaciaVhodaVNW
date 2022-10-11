package cursor_move;

import java.awt.*;

public class DirectHypotenuse {
    public void moveCursorAlongStraightLineInHypotenuse(int setX, int setY) { // пряме переміщення курсору гіпотенузою
        boolean flag = true; // датчик завершення переміщення (не завершено)
        double a = 0, b = 0;  // датчик реагування на вплив користувача під час переміщення курсору
        while (flag) { // виконувати пока датчик не зафіксував завершення переміщення
            int[] cursorGlobalPosition = getCursorGlobalPosition();
            double x = cursorGlobalPosition[0]; // поточне положеняя курсору по ширині
            double y = cursorGlobalPosition[1]; // поточне положення курсору по висоті
            if (x == setX & y == setY) {  // якщо курсор в потрібному положенні
                flag = false; // активувати датчик
            } else { // курсор потрібно перемістити
                if (x == (int) a & y == (int) b) { // користувач не впливає на курсор під час програмного руху
                    double distance_on_x = setX - x; // відстань по Х між зараз та потрібно
                    double distance_on_y = setY - y; // відстань по У між зараз та потрібно
                    double step = Math.sqrt(Math.pow(distance_on_x, 2) + Math.pow(distance_on_y, 2)); // скільки потрібно виконати кроків до кінцевого призначення
                    a += distance_on_x / step; b += distance_on_y / step; // перемістити курсор на один крок

                    setCursorInGlobalPosition((int) a, (int) b); // встановити курсор
                } else { // вмішався користувач чи це початок руху
                    a = x; b = y;
                }
            }
        }
    }

    public int[] getCursorGlobalPosition() { // отримати глобальні координати знаходження курсору
        int[] result = new int[2];
        result[0] = MouseInfo.getPointerInfo().getLocation().x; // ширина
        result[1] = MouseInfo.getPointerInfo().getLocation().y; // висота
        return result;
    }

    public void setCursorInGlobalPosition(int setX, int setY) { // встановити глобальну позицію курсора
        try {
            new Robot().mouseMove(setX, setY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
