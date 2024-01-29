package service;

import java.util.ArrayList;
import java.util.List;

import domain.Car;
import strategy.NumberGenerator;
import ui.InputResult;
import ui.OutputResult;


public class RacingGame {

    private final List<Car> cars;
    private static final int RANGE_NUMBER = 10;
    private final NumberGenerator numberGenerator;

    public RacingGame(int carCount, NumberGenerator numberGenerator) {
        this.cars = new ArrayList<>();
        this.numberGenerator = numberGenerator;
        initializeCars(carCount);
    }

    public void play(InputResult inputResult, OutputResult outputResult) {
        int tryCount = inputResult.getTryCount();
        outputResult.printMessage("\n실행 결과:");
        moveCars(tryCount, outputResult);
    }


    public void initializeCars(int carCount) {
        for (int i = 0; i < carCount; i++) {
            cars.add(new Car());
        }
    }

    private void moveCars(int tryCount, OutputResult outputResult) {
        for (int move = 0; move < tryCount; move++) {
            moveIfRandomValueGreaterThan();
            outputResult.printCarStates(getCarPositions());
        }
    }

    private void moveIfRandomValueGreaterThan() {
        for (Car car: cars) {
            int randomValue = numberGenerator.generateRandomValue(RANGE_NUMBER);
            car.move(randomValue);
        }
    }

    private int[] getCarPositions() {
        return cars.stream().mapToInt(Car::getPosition).toArray();
    }
}