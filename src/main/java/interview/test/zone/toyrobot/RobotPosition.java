package interview.test.zone.toyrobot;

public class RobotPosition {
    private final int x;
    private final int y;
    private final Direction direction;

    public RobotPosition(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

}
