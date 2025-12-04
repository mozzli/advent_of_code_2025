package day_1;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {

    public Solution(ArrayList<String> input){
        System.out.println("The password: " + countZeros(input));
        System.out.println("the password with 0x434C49434B method: " + countZerosWithPassword(input));
    }

    private int countZeros(ArrayList<String> input){
        AtomicInteger currentWheelNumber = new AtomicInteger(50);
        return Math.toIntExact(input.stream().map(x -> {
            currentWheelNumber.set(turnSafeWheel(currentWheelNumber.get(), x));
            return currentWheelNumber;}
        ).filter(v -> v.get() == 0).count());
    }

    private int countZerosWithPassword(ArrayList<String> input){
        AtomicInteger currentWheelNumber = new AtomicInteger(50);
        AtomicInteger rollOverZero = new AtomicInteger(0);
        return Math.toIntExact(input.stream().map(x -> {
            if (x.length() > 3) rollOverZero.set(rollOverZero.get() + Integer.parseInt(x.substring(1, x.length() - 2)));
            if (checkIfGoOverZero(currentWheelNumber.get(), x)) rollOverZero.set(rollOverZero.get() + 1);
            currentWheelNumber.set(turnSafeWheel(currentWheelNumber.get(), x));
            return currentWheelNumber;}
        ).filter(v -> v.get() == 0).count()) + rollOverZero.get();
    }

    private int turnSafeWheel(int wheelNumber,String code){
        int sum;
        switch (code.charAt(0)){
            case 'R' -> sum = (wheelNumber + Integer.parseInt(code.substring(1))) % 100;
            case 'L' -> sum = (wheelNumber - Integer.parseInt(code.substring(1))) % 100;
            default -> throw new RuntimeException("Wrong wheel code");
        }
        return (sum < 0) ? 100 - Math.abs(sum) : sum;
    }

    private boolean checkIfGoOverZero(int wheelNumber,String code){
        switch (code.charAt(0)){
            case 'R' -> {return (wheelNumber + ((Integer.parseInt(code.substring(1))) % 100)) > 100;}
            case 'L' -> {return wheelNumber > 0 && (wheelNumber - (Integer.parseInt(code.substring(1))) % 100) < 0;}
            default -> throw new RuntimeException("Wrong wheel code");
        }
    }
}