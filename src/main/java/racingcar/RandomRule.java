package racingcar;

import java.util.Random;

public class RandomRule implements Rule {

    private final Random random;

    public RandomRule(int seed) {
        this.random = new Random(seed);
    }

    public boolean isStop() {
        return random.nextInt(10) < 4;
    }
}