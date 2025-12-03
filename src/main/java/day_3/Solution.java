package day_3;

import java.util.ArrayList;

class Solution {

    ArrayList<String> batteries;

    public Solution(ArrayList<String> batteries) {
        this.batteries = batteries;
        System.out.println("2 battery joltage: " + getMaxBatteriesJoltage(2));
        System.out.println("12 battery joltage: " + getMaxBatteriesJoltage(12));
    }

    private long getMaxBatteriesJoltage(int batteriesCount) {
        return batteries.stream().map(x -> calculateMaxJoltage(x, batteriesCount)).mapToLong(Long::longValue).sum();
    }

    private long calculateMaxJoltage(String battery, int batteryLength) {
        StringBuilder maxVoltage = new StringBuilder();
        String latestBiggestDecimal = "0";
        int latestHighestVoltagePlacement = -1;
        while (maxVoltage.length() < batteryLength) {
            for (int i = latestHighestVoltagePlacement + 1; i < battery.length() - (batteryLength - maxVoltage.length() - 1); i++) {
                if (Integer.parseInt(battery.substring(i, i + 1)) > Integer.parseInt(latestBiggestDecimal)) {
                    latestBiggestDecimal = (battery.substring(i, i + 1));
                    latestHighestVoltagePlacement = i;
                    if (latestBiggestDecimal.equals("9")) {
                        break;
                    }
                }
            }
            maxVoltage.append(latestBiggestDecimal);
            latestBiggestDecimal = "0";
        }
        return Long.parseLong(maxVoltage.toString());
    }
}
