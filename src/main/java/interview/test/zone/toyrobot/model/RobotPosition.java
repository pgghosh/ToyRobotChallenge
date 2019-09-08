package interview.test.zone.toyrobot.model;

public class RobotPosition {
    private int x;
    private int y;
    private Direction direction;

    public RobotPosition(int x, int y, Direction direction) {
        setPosition(x, y, direction);
    }

    public void setPosition(int x, int y, Direction direction) {
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

    @Override
    public String toString() {
        return '{' +
                "x : " + x +
                ", y : " + y +
                ", direction : \"" + direction + "\"" +
                '}';
    }
}
