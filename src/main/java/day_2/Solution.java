package day_2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Solution {

    ArrayList<Id> ids;

    public Solution(ArrayList<String> input) {
        ids = getIds(input);
        System.out.println("Invalid ids sum: " + getInvalidIdNumber(ids));
        System.out.println("All invalid Ids sum: " + getInvalidIdNumberPro(ids));
    }

    public ArrayList<Id> getIds(ArrayList<String> input){
        String regex = "\\b\\d+-\\d+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input.get(0));
        ArrayList<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches.stream().map(x-> new Id(x.substring(0,x.indexOf("-")), x.substring(x.indexOf("-")+1))).collect(Collectors.toCollection(ArrayList<Id>::new));
    }

    private Long getInvalidIdNumber(ArrayList<Id> ids){
        return ids.stream().map(Id::getInvalidIdsSum).mapToLong(Long::longValue).sum();
    }

    private Long getInvalidIdNumberPro(ArrayList<Id> ids){
        return ids.stream().map(Id::getInvalidIdsSumPro).mapToLong(Long::longValue).sum();
    }
}
