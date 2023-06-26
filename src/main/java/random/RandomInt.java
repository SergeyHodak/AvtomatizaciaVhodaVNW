package random;

import java.util.Random;

public class RandomInt {

    // Отримати ціле випадкове значення, у вказаному діапазоні
    public int getInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
