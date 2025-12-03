package day_2;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Id {

    long beginningId, endingId;
    ArrayList<Long> intList;

    public Id(String beginning, String ending) {
        beginningId = Long.parseLong(beginning);
        endingId = Long.parseLong(ending);
        intList = getIdList();
    }

    private ArrayList<Long> getIdList() {
        return LongStream.rangeClosed(beginningId, endingId).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Long> getInvalidIds() {
        return intList.stream().filter(x -> String.valueOf(x).length() % 2 == 0 && String.valueOf(x).substring(0, String.valueOf(x).length() / 2)
                .equals(String.valueOf(x).substring(String.valueOf(x).length() / 2))).collect(Collectors.toCollection(ArrayList::new));
    }

    public Long getInvalidIdsSum() {
        return getInvalidIds().stream().mapToLong(Long::longValue).sum();
    }

    public Long getInvalidIdsSumPro() {
        return getInvalidIdsPro().stream().mapToLong(Long::longValue).sum();
    }

    public ArrayList<Long> getInvalidIdsPro() {
        return intList.stream().filter(x -> checkForInvalidIdPro(String.valueOf(x))).collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean checkForInvalidIdPro(String number){
        int numberLength = number.length();
        int currentNumber = 1;
        while (currentNumber<=numberLength/2){
            if(checkOption(currentNumber,1,number)) return true;
            currentNumber++;
        }
        return false;
    }

    private boolean checkOption(int digitNumber, int iteration, String id){
        if (iteration * digitNumber == id.length()) return true;
        if ((iteration + 1) * digitNumber > id.length()) return false;
        if (id.substring(digitNumber*(iteration-1),iteration*digitNumber).equals(id.substring(digitNumber*iteration,(iteration+1)*digitNumber))){
            return checkOption(digitNumber, iteration + 1, id);
        }
        return false;
    }
}
