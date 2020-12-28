package Day10_AdapterArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by shivangipatwardhan on 12/14/20.
 */
public class AdapterArray {

    private void ProcessArrayArrangment(ArrayList<Integer> inputData) {
        int buildInJolts = inputData.get(inputData.size()-1) + 3;
        int numDiffOne = 0;
        int numDiffThree = 0;
        int prevJolt = 0;

        while(!inputData.isEmpty() && Math.abs(buildInJolts-prevJolt) != 3) {
            int joltOption = inputData.get(0);

            int curDiff = joltOption - prevJolt;

            prevJolt = joltOption;
            inputData.remove(0);

            switch(curDiff) {
                case 1:
                    numDiffOne += 1;
                    continue;
                case 3:
                    numDiffThree += 1;
                    continue;
            }
        }

        System.out.println(numDiffOne * (numDiffThree+1));
    }

    private void AllPossibleCombinations(ArrayList<Integer> inputData) {

    }

    private void ProcessIncomingData(ArrayList<Integer> inputData) throws IOException {
        File file = new File("src/Day10_AdapterArray/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String instruction;

        while ((instruction = reader.readLine()) != null) {
            inputData.add(Integer.parseInt(instruction));
        }

        Collections.sort(inputData);
    }

    public static void main(String[] arg) throws IOException {
        AdapterArray arr = new AdapterArray();
        ArrayList<Integer> input = new ArrayList<>();
        arr.ProcessIncomingData(input);
        arr.ProcessArrayArrangment(input);
    }


}
