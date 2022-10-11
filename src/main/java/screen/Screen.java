package screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {
    public int[] getXYByImage(String filename) {
        int[] result = new int[3]; // координата ширини, висоти, та інформація що співпадінь нема позначена нульом
        try {
            Robot robot = new Robot(); // клас для автоматизованих тестів по управлінню клавою та мишею
            BufferedImage screen = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())); // скрін монітору
            BufferedImage image = ImageIO.read(new File("src/main/java/img/" + filename)); // зображення яке шукаєм
            boolean screenMatches = false; // чи є повне співпадання
            int screenX=0; // результат. x - ширина
            int screenY=0; // результат. y - висота
            for (int i = 0; i < screen.getWidth() - image.getWidth(); i++) { // пробіжка по ширині скріншота
                for (int j = 0; j < screen.getHeight() - image.getHeight(); j++) { // пробіжка по висоті скріншота
                    boolean matches = true; // початок що є співпадання, піксель співпадає з відшукуваним
                    for (int x = 0; x < image.getWidth(); x++) { // пробіжка по ширині відшукуваного фрагмента зображення
                        for (int y = 0; y < image.getHeight(); y++) { // пробіжка по висоті відшукуваного зображення
                            if (screen.getRGB(i + x,j + y) != image.getRGB(x, y)) { //якщо піксель не відповідає відшукуваному
                                matches = false; // не співпадає
                                break; // прервати цей внутрішній цикл фор
                            }
                        }
                        if(!matches)break; // якщо піксель не відповідає шуканому, то прервати цей внутрішній цикл
                    }
                    if(matches) { //якщо відповідає шуканому
                        screenMatches = true; // є повне співпадання
                        screenX = i; // зберегди значення першого пікселю зліва, який співпав
                        screenY = j; // зберегти значення першого пікселю зверху, який співпав
                        break; // прервати цикл фор пробіжки по висоті скріншота
                    }
                }
                if(screenMatches)break; // якщо є повне співпадання, то прервати цикл пробіжки ширині скріншота
            }
            if(screenMatches) { // якщо є співпадання
                result[0] = screenX; // рестрація ширини
                result[1] = screenY; // рестрація висоти
                result[2] = 1; // реєстрація що це справді було знайдено
            }
        } catch (AWTException | IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return result;
    }
}
