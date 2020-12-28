package Day6_CustomCustoms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by shivangipatwardhan on 12/11/20.
 */
public class CustomCustoms {

    // Part One
    public int GetNumberOfUniqueQuestions(BufferedReader br, String st) throws IOException {
        int lineCount = 0;

        StringBuffer formResults = new StringBuffer("");

        while ((st = br.readLine()) != null) {
            if (st.trim().isEmpty()) {
                lineCount += processGroupPartOne(formResults.toString());
                formResults.setLength(0);
            } else {
                formResults.append(st);
            }
        }
        return lineCount;
    }

    private int processGroupPartOne(String input) {
        HashSet<Character> uniqueQuestions = new HashSet<>();

        for(Character ch : input.toCharArray()) {
            uniqueQuestions.add(ch);
        }

        return uniqueQuestions.size();
    }

    // Part Two
    public int GetNumberOfSameAnswersPerGroups(BufferedReader br, String st) throws IOException  {
        int lineCount = 0;

        ArrayList<String> groupInputs = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            if (st.trim().isEmpty()) {
                lineCount += processGroupPartTwo(groupInputs);
                groupInputs = new ArrayList<>();
            } else {
                groupInputs.add(st);
            }
        }
        return lineCount;
    }

    private int processGroupPartTwo(ArrayList<String> input) {
        int uniqueQuestions = 0;
        int numPeopleInGroup = input.size();

        for(char i = 'a'; i <= 'z'; i++) {
            int count = 0;

            for(String item : input) {
                if(item.indexOf(i) == -1) {
                    break;
                }
                count++;
            }
            if(count == numPeopleInGroup) {
                uniqueQuestions++;
            }
        }
        return uniqueQuestions;
    }

    public static void main(String[] arg) throws IOException {
        CustomCustoms CustomsProcessor = new CustomCustoms();

        File file = new File("src/Day6_CustomCustoms/input.txt");
        System.out.println(CustomsProcessor.GetNumberOfUniqueQuestions(new BufferedReader(new FileReader(file)), new String()));
        System.out.println(CustomsProcessor.GetNumberOfSameAnswersPerGroups(new BufferedReader(new FileReader(file)), new String()));

    }


}
