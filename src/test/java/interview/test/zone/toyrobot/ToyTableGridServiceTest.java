package interview.test.zone.toyrobot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static interview.test.zone.toyrobot.Direction.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Created by parthaghosh on 07/09/2019.
 */
class ToyTableGridServiceTest {

    private static final int MAX_X = 5;
    private static final int MAX_Y = 5;

    private static final EnumMap<Direction, Direction> moveRightmap = new EnumMap<>(Direction.class);
    private static final EnumMap<Direction, Direction> moveLeftmap = new EnumMap<>(Direction.class);


    private ToyRobotService toyRobotService;

    @BeforeAll
    static void setUpEnumMaps() {
        moveRightmap.put(SOUTH, WEST);
        moveRightmap.put(WEST, NORTH);
        moveRightmap.put(NORTH, EAST);
        moveRightmap.put(EAST, SOUTH);

        moveLeftmap.put(SOUTH, EAST);
        moveLeftmap.put(EAST, NORTH);
        moveLeftmap.put(NORTH, WEST);
        moveLeftmap.put(WEST, SOUTH);
    }

    @BeforeEach
    void setUp() {
        toyRobotService = new ToyRobotService(MAX_X, MAX_Y);

    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("positions")
    void shouldPerformPlaceCommandWhenRobotIsPlacedAndDestinationWithinGrid(String testName, RobotPosition destination) {

        RobotPosition randomPositionWithinGrid = getRandomPositionInsideGrid();
        toyRobotService.place(randomPositionWithinGrid.getX(), randomPositionWithinGrid.getY(), randomPositionWithinGrid.getDirection().name());

        toyRobotService.place(destination.getX(), destination.getY(), destination.getDirection().name());


        RobotPosition robotPosition = toyRobotService.getTableGrid().getRobotPosition();

        if (destination.getX() >=0 &&
                destination.getX() < MAX_X &&
                destination.getY() >= 0 &&
                destination.getY() < MAX_Y) {
            assertThat(robotPosition.getX(), is(destination.getX()));
            assertThat(robotPosition.getY(), is(destination.getY()));
            assertThat(robotPosition.getDirection(), is(destination.getDirection()));
        } else {
            assertThat(robotPosition.getX(), is(randomPositionWithinGrid.getX()));
            assertThat(robotPosition.getY(), is(randomPositionWithinGrid.getY()));
            assertThat(robotPosition.getDirection(), is(randomPositionWithinGrid.getDirection()));
        }
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("positions")
    void shouldPerformPlaceCommandWhenRobotIsNotPlacedAndDestinationWithinGrid(String testName, RobotPosition destination) {

        toyRobotService.place(destination.getX(), destination.getY(), destination.getDirection().name());

        RobotPosition robotPosition = toyRobotService.getTableGrid().getRobotPosition();

        if (destination.getX() >=0 &&
                destination.getX() < MAX_X &&
                destination.getY() >= 0 &&
                destination.getY() < MAX_Y) {
            assertThat(robotPosition.getX(), is(destination.getX()));
            assertThat(robotPosition.getY(), is(destination.getY()));
            assertThat(robotPosition.getDirection(), is(destination.getDirection()));
        } else {
            assertThat(robotPosition, is(nullValue()));
        }
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("validPositions")
    void shouldPerformValidMoveCommandWhenRobotIsPlaced(String testName, RobotPosition initialPosition) {
        toyRobotService.place(initialPosition.getX(), initialPosition.getY(), initialPosition.getDirection().name());

        toyRobotService.move();

        RobotPosition robotPosition = toyRobotService.getTableGrid().getRobotPosition();

        assertThat(robotPosition.getDirection(), is(initialPosition.getDirection()));

        int initialX = initialPosition.getX();
        int initialY = initialPosition.getY();
        switch (initialPosition.getDirection()) {
            case EAST:
                assertThat(robotPosition.getX(), is(initialX < MAX_X - 1 ? initialX + 1 : initialX));
                assertThat(robotPosition.getY(), is(initialY));
                break;
            case WEST:
                assertThat(robotPosition.getX(), is(initialX > 0 ? initialX - 1 : initialX));
                assertThat(robotPosition.getY(), is(initialY));
                break;
            case NORTH:
                assertThat(robotPosition.getX(), is(initialX));
                assertThat(robotPosition.getY(), is(initialY < MAX_Y - 1 ? initialY + 1 : initialY));
                break;
            case SOUTH:
                assertThat(robotPosition.getX(), is(initialX));
                assertThat(robotPosition.getY(), is(initialY > 0 ? initialY - 1 : initialY));
                break;
        }

    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("validPositions")
    void shouldPerformValidRightCommandWhenRobotIsPlaced(String testName, RobotPosition initialPosition) {
        toyRobotService.place(initialPosition.getX(), initialPosition.getY(), initialPosition.getDirection().name());

        toyRobotService.right();

        RobotPosition robotPosition = toyRobotService.getTableGrid().getRobotPosition();

        assertThat(robotPosition.getX(), is(initialPosition.getX()));
        assertThat(robotPosition.getY(), is(initialPosition.getY()));
        assertThat(robotPosition.getDirection(), is(moveRightmap.get(initialPosition.getDirection())));

    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("validPositions")
    void shouldPerformValidLeftCommandWhenRobotIsPlaced(String testName, RobotPosition initialPosition) {
        toyRobotService.place(initialPosition.getX(), initialPosition.getY(), initialPosition.getDirection().name());

        toyRobotService.left();

        RobotPosition robotPosition = toyRobotService.getTableGrid().getRobotPosition();

        assertThat(robotPosition.getX(), is(initialPosition.getX()));
        assertThat(robotPosition.getY(), is(initialPosition.getY()));
        assertThat(robotPosition.getDirection(), is(moveLeftmap.get(initialPosition.getDirection())));

    }

    static Stream<Arguments> validPositions() {
      return IntStream.range(0, MAX_X)
              .mapToObj(i -> IntStream.range(0, MAX_Y)
                      .mapToObj(j -> Arrays.stream(Direction.values())
                              .map(d -> Arguments.of("[" + i + "," + j + "," + d + "]",new RobotPosition(i, j, d)))))
              .flatMap(Function.identity())
              .flatMap(Function.identity());
    }

    static Stream<Arguments> allPositions() {
        return IntStream.range(0 - (MAX_X + 5), MAX_X + 5)
                .mapToObj(i -> IntStream.range(0 - (MAX_Y + 5), MAX_Y + 5)
                        .mapToObj(j -> Arrays.stream(Direction.values())
                                .map(d -> Arguments.of("[" + i + "," + j + "," + d + "]",new RobotPosition(i, j, d)))))
                .flatMap(Function.identity())
                .flatMap(Function.identity());
    }
    private RobotPosition getRandomPositionInsideGrid() {
        Random rand = new Random();
        Direction[] directions = Direction.values();
        return new RobotPosition(rand.nextInt(MAX_X), rand.nextInt(MAX_Y), directions[rand.nextInt(directions.length)]);
    }

    public static void main(String[] args) {
        ToyTableGridServiceTest test = new ToyTableGridServiceTest();
        System.out.println(test.getRandomPositionInsideGrid());
    }

}