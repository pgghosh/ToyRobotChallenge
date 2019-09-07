package interview.test.zone.toyrobot;

public class TableGrid {

    private final int maxXCoordinate;
    private final int maxYCoordinate;

    private RobotPosition robotPosition;

    public TableGrid(int maxX, int maxY) {
        this.maxXCoordinate = maxX - 1;
        this.maxYCoordinate = maxY - 1;
    }

    public void setRobotPosition(RobotPosition robotPosition) {
        this.robotPosition = robotPosition;
    }

    public RobotPosition getRobotPosition() {
        return robotPosition;
    }
}
