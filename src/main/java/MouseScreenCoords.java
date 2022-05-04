import java.awt.*;
import java.util.Arrays;

public class MouseScreenCoords {
    public static int[] getGlobalCursorPosition() { // получить глобальную позицию курсора
        int x = MouseInfo.getPointerInfo().getLocation().x; // слева на право (ширина)
        int y = MouseInfo.getPointerInfo().getLocation().y; // сверху вниз (высота)
        return new int[]{x, y};
    }

    public static void setGlobalCursorPosition(int setX, int setY) { // установить глобальную позицию курсора
        try {
            new Robot().mouseMove(setX, setY);
        } catch (AWTException e) {
            System.out.println("Ошибка установки курсора в х=" + setX + " у=" + setY);
            e.printStackTrace();
        }}

    public void moveCursorInStraightLine(int setX, int setY) throws InterruptedException { // перемещать по гипотенузе
        int flag = 0;
        double a = 0, b = 0;  // для различий, повлиял ли пользователь на курсор во время программного движения
        while (flag == 0) { // пока флаг равен нулю выполняем цикл
            int[] globalCursorPosition = getGlobalCursorPosition();//получаем текущее положение курсора
            double x = globalCursorPosition[0]; double y = globalCursorPosition[1];
            if (x == setX & y == setY) {  // если курсор уже где нужно
                flag += 1; // возвести флаг
            } else { // курсор нужно перемещать
                if (x == (int) a & y == (int) b) { // пользователь не двигает курсор во время программного движения
                    double distance_on_x = setX - x; // дистанция по иксу между сейчас и надо
                    double distance_on_y = setY - y; // дистанция по игреку между сейчас и надо
                    double step = Math.sqrt(Math.pow(distance_on_x, 2) + Math.pow(distance_on_y, 2)); // шаги
                    a += distance_on_x / step; b += distance_on_y / step; // сместить курсор на один шаг
                    setGlobalCursorPosition((int) a, (int) b); // установить курсор
                } else { // двинул или начало движения
                    a = x; b = y; // если повлиял, или это начало
                }}}}

    public static void main(String[] args) throws InterruptedException {
        MouseScreenCoords test = new MouseScreenCoords();
        setGlobalCursorPosition(14, 112);
        System.out.println(Arrays.toString(getGlobalCursorPosition()));
        test.moveCursorInStraightLine(10, 100);
        System.out.println(Arrays.toString(getGlobalCursorPosition()));
        test.moveCursorInStraightLine(800, 800);
        System.out.println(Arrays.toString(getGlobalCursorPosition()));
    }
}