package interview.test.zone.toyrobot;

import java.util.EnumMap;

import static interview.test.zone.toyrobot.Direction.*;

public class ToyRobotService {

    private static final EnumMap<Direction, int[]> DIRECTION_BASED_MOVES = new EnumMap<>(Direction.class);
    static {
        DIRECTION_BASED_MOVES.put(SOUTH, new int[] {0, -1});
        DIRECTION_BASED_MOVES.put(WEST, new int[] {-1, 0});
        DIRECTION_BASED_MOVES.put(NORTH, new int[] {0, 1});
        DIRECTION_BASED_MOVES.put(EAST, new int[] {1, 0});
    }

    private static final Direction[] DIRECTION_VALUES = Direction.values();

    private final TableGrid tableGrid;

    public ToyRobotService(int maxGridX, int maxGridY) {
        this.tableGrid = new TableGrid(maxGridX, maxGridY);
    }

    public void place(int x, int y, String direction){
        if (tableGrid.isValidPosition(x, y)) {
            tableGrid.setRobotPosition(x, y, Direction.valueOf(direction));
        }
    }

    public void move() {
        if (tableGrid.isPlaced()) {
            RobotPosition robotPosition = tableGrid.getRobotPosition();
            int[] moves = DIRECTION_BASED_MOVES.get(robotPosition.getDirection());
            tableGrid.setRobotPosition(robotPosition.getX() + moves[0], robotPosition.getY() + moves[1], robotPosition.getDirection());
        }
    }

    public void left() {
        if (tableGrid.isPlaced()) {
            RobotPosition robotPosition = tableGrid.getRobotPosition();
            int dOrdinal = robotPosition.getDirection().ordinal() - 1;
            dOrdinal = dOrdinal < 0 ? DIRECTION_VALUES.length - 1 : dOrdinal;
            tableGrid.setRobotPosition(robotPosition.getX(), robotPosition.getY(), DIRECTION_VALUES[dOrdinal]);
        }
    }

    public void right() {
        if (tableGrid.isPlaced()) {
            RobotPosition robotPosition = tableGrid.getRobotPosition();
            int dOrdinal = robotPosition.getDirection().ordinal() + 1;
            dOrdinal = dOrdinal > DIRECTION_VALUES.length - 1 ? 0 : dOrdinal;
            tableGrid.setRobotPosition(robotPosition.getX(), robotPosition.getY(), DIRECTION_VALUES[dOrdinal]);
        }
    }

    public String report() {
        RobotPosition robotPosition = tableGrid.getRobotPosition();
        return robotPosition == null ? "" : robotPosition.toString();
    }

    TableGrid getTableGrid() {
        return tableGrid;
    }
}
