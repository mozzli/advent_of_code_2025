package utilities;


import lombok.Getter;

@Getter
public class PointUtilities {

    int xDistance, yDistance;

    public PointUtilities(Point a, Point b){
        xDistance = Math.abs(a.x() - b.x());
        yDistance = Math.abs(a.y() - b.y());
    }
}
