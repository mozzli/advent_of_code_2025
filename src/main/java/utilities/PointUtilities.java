package utilities;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PointUtilities {

    public static ArrayList<Integer> getDistance(Point a, Point b) {
        return new ArrayList<>(List.of(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY())));
    }

    public static ArrayList<String> getNeighborValues(Point point, ArrayList<Point> points) {
        ArrayList<String> values = new ArrayList<>();
        if (point.getValue().equals("@")) {
        for (Directions directions : Directions.values()) {
                for (Point globalPoint : points) {
                    if (point.getX() + directions.getRow() == globalPoint.getX() && point.getY() + directions.column == globalPoint.getY()) {
                        values.add(globalPoint.getValue());
                        break;
                    }
                }
            }
        }
        return values;
    }

    @AllArgsConstructor
    @Getter
    public enum Directions {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        LEFT_UP(-1, -1),
        LEFT_DOWN(1, -1),
        RIGHT_UP(-1, 1),
        RIGHT_DOWN(1, 1);

        final int row;
        final int column;
    }
}
