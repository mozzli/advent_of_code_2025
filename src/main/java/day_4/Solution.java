package day_4;

import utilities.Point;
import utilities.PointUtilities;

import java.util.ArrayList;

class Solution {

    ArrayList<String> input;
    int maxToiletPaperInbound = 4;

    public Solution(ArrayList<String> input) {
        this.input = input;
        System.out.println(getAccessibleToiletPaperNumberWithRemovalOn(1) + " toilet paper can be accessed");
        System.out.println(getAccessibleToiletPaperNumberWithRemovalOn(9999) + " toilet paper can be accessed");
    }

    private ArrayList<Point> getElevatorPlan(ArrayList<String> input) {
        int lineNumber = 0;
        ArrayList<Point> points = new ArrayList<>();
        for (String line : input) {
            int columnNumber = 0;
            for (char character : line.toCharArray()) {
                points.add(new Point(lineNumber, columnNumber, String.valueOf(character)));
                columnNumber++;
            }
            lineNumber++;
        }
        return points;
    }

    private int getAccessibleToiletPaperNumberWithRemovalOn(int maxLoop) {
        ArrayList<Point> elevatorPlan = getElevatorPlan(input);
        ArrayList<Point> pointsForRemoval = new ArrayList<>();
        int removedPaper = 0;
        int loop = 0;
        while (loop < maxLoop) {
            for (Point point : elevatorPlan) {
                ArrayList<String> removablePaper = PointUtilities.getNeighborValues(point, elevatorPlan);
                if (removablePaper.stream().filter(i -> i.equals("@")).count() < maxToiletPaperInbound && !removablePaper.isEmpty()) {
                    pointsForRemoval.add(point);
                }
            }
            if (pointsForRemoval.isEmpty()) break;
            removedPaper += pointsForRemoval.size();
            for (Point pointToRemove : elevatorPlan) {
                if (pointsForRemoval.contains(pointToRemove)) pointToRemove.setValue(".");
            }
            pointsForRemoval.clear();
            loop++;
        }
        return removedPaper;
    }
}
