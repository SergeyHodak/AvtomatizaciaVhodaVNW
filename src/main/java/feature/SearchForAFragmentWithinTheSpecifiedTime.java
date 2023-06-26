package feature;

import screen.Screen;

import java.time.LocalDateTime;

/** пошук фрагменту протягом вказаного часу */
public class SearchForAFragmentWithinTheSpecifiedTime {
    public int[] get(String filename, int waitMinutes) {
        Screen screen = new Screen();
        LocalDateTime timeStart = LocalDateTime.now(); // час від запуску
        int[] result;
        while (true) {
            result = screen.getXYByImage(filename); // знайти фрагмент зображення
            if (result[2] == 1) { // якщо є відповідність
                break;
            }
            if (timeStart.plusMinutes(waitMinutes).isBefore(LocalDateTime.now())) { // якщо пройшов вказаний час процесу
                break;
            }
            try {
                Thread.sleep(100); // почикати 100 мілісекунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
