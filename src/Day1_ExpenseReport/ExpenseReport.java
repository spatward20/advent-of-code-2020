package Day1_ExpenseReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by shivangipatwardhan on 12/6/20.
 */
public class ExpenseReport {

    private static void twoSum(ArrayList<Integer> input, int sumTotal) {
        ArrayList<Integer> soln = new ArrayList<>();

        for(int i = 0; i < input.size(); i++) {
            int curNum = input.get(i);
            int value = sumTotal - curNum;
            if(soln.contains(value)) {
                System.out.println(value * curNum);
            }
            else
            {
                soln.add(curNum);
            }
        }
    }

    private static void threeSum(ArrayList<Integer> input, int sumTotal) {

        HashMap<Integer,Integer> soln = new HashMap<>();

        // O(n^2)
        for(int i = 0; i < input.size(); i++) {
            for(int j = i+1; j < input.size(); j++) {
                int val1 = input.get(i);
                int val2 = input.get(j);
                int remainder = 2020 - (val1 + val2);
                soln.put(remainder, val1 * val2);
            }
        }

        // O(n)
        for(int i = 0; i < input.size(); i++) {
            int curNum = input.get(i);

            if(soln.containsKey(curNum)) {
                System.out.println(curNum * soln.get(curNum));
                return;
            }
        }

    }

    //    Find the two entries that sum to 2020; what do you get if you multiply them together?
    public static void main(String[] args) {
        // read all the values from input file one at a time.
        ArrayList<Integer> input = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get("src/Day1_ExpenseReport/input.txt"))) {
            stream.forEach(number -> {
                Integer record = Integer.parseInt(number);
                input.add(record);
            });
            twoSum(input, 2020);
            threeSum(input, 2020);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
