package service;

import java.util.ArrayList;
import java.util.List;

import domain.Car;
import strategy.NumberGenerator;

import static ui.OutputResult.*;


public class RacingGame {

    private final List<Car> cars = new ArrayList<>();
    private static final int RANGE_NUMBER = 10;
    private final NumberGenerator numberGenerator;

    public RacingGame(NumberGenerator numberGenerator, String[] carNames) {
        this.numberGenerator = numberGenerator;
        initializeCars(carNames);
    }

    public void play(int tryCount) {
        moveCars(tryCount);
    }


    public void initializeCars(String[] carNames) {
        for (String carName: carNames) {
            cars.add(new Car(carName));
        }
    }

    private void moveCars(int tryCount) {
        for (int move = 0; move < tryCount; move++) {
            carMovesAccordingRandomValues();
            printCars(getCarNames(), getCarPositions());
        }
        printWinner(determineWinner(cars));
    }

    private void carMovesAccordingRandomValues() {
        for (Car car: cars) {
            int randomValue = numberGenerator.generateRandomValue();
            car.move(randomValue);
        }
    }

    private String[] getCarNames() {
        String[] carNames = new String[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            carNames[i] = cars.get(i).getName().toString();
        }
        return carNames;

    }

    private int[] getCarPositions() {
        return cars.stream().mapToInt(Car::getPosition).toArray();
    }
}
