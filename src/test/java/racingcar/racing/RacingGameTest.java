package racingcar.racing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.entry.Car;
import racingcar.entry.MovePolicy;
import racingcar.mock.AlwaysMovePolicy;
import racingcar.ui.ConsoleRacingUi;
import racingcar.ui.RacingUi;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {

    @Test
    @DisplayName("게임을 시작하면, 각 경주 참가자가 이동 횟수만큼 이동한다.")
    void start() {
        int entryCount = 3;
        int moveCount = 5;
        RacingUi racingUi = new TestRacingUi(entryCount, moveCount);
        RacingPreference preference = racingUi.inputPreference();
        MovePolicy movePolicy = new AlwaysMovePolicy();

        RacingGame racingGame = new RacingGame(preference, movePolicy);
        RacingResult result = racingGame.race();
        racingUi.showResult(result);

        List<RacingEntries> racingEntriesList = result.getRacingEntriesList();
        RacingEntries lastEntries = result.getRacingEntriesList().get(racingEntriesList.size() - 1);
        for (Car car: lastEntries.getEntries()) {
            assertThat(car.currentPosition()).isEqualTo(moveCount);
        }
    }


    static class TestRacingUi implements RacingUi {

        private final int carCount;
        private final int moveCount;

        public TestRacingUi(int carCount, int moveCount) {
            this.carCount = carCount;
            this.moveCount = moveCount;
        }

        @Override
        public RacingPreference inputPreference() {
            return new RacingPreference(carCount, moveCount);
        }

        @Override
        public void showResult(RacingResult result) {
            //noop
            new ConsoleRacingUi().showResult(result);
        }
    }

}