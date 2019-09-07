package interview.test.zone.toyrobot;

public class ToyRobotService {

    private final TableGrid tableGrid;

    public ToyRobotService(int maxGridX, int maxGridY) {
        this.tableGrid = new TableGrid(maxGridX, maxGridY);
    }

    public void place(int x, int y, String direction){

    }

    public void move() {

    }

    public void left() {

    }

    public void right() {

    }

    public String report() {
        return null;
    }

    TableGrid getTableGrid() {
        return tableGrid;
    }
}
