package utilities;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class InputConverter {

    @SneakyThrows
    public static ArrayList<String> getInputList(String inputSource) {
        String line;
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader text = new BufferedReader(new FileReader(inputSource));
        while ((line = text.readLine()) != null) lines.add(line);
        return lines;
    }
}
