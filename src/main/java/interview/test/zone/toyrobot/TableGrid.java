package interview.test.zone.toyrobot;

public class TableGrid {

    private final int maxXCoordinate;
    private final int maxYCoordinate;

    private RobotPosition robotPosition;

    public TableGrid(int maxX, int maxY) {
        this.maxXCoordinate = maxX - 1;
        this.maxYCoordinate = maxY - 1;
    }

    public void setRobotPosition(int x, int y, Direction direction) {
        x = x >= maxXCoordinate ? maxXCoordinate : x <= 0 ? 0 : x;
        y = y >= maxXCoordinate ? maxYCoordinate : y <= 0 ? 0 : y;

        if (this.robotPosition == null) {
            this.robotPosition = new RobotPosition(x, y, direction);
        } else {
            this.robotPosition.setPosition(x , y, direction);
        }
    }

    public RobotPosition getRobotPosition() {
        return robotPosition;
    }

    public boolean isPlaced() {
        return robotPosition != null;
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= maxXCoordinate && y >= 0 && y <= maxYCoordinate;
    }

}
