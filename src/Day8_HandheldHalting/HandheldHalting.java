package Day8_HandheldHalting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by shivangipatwardhan on 12/13/20.
 */
public class HandheldHalting {

    int ACCUMULATOR = 0;

    private void IdentifyFix(ArrayList<String> input) {
        String newInst = "";

        for(int i = 0; i < input.size(); i++) {
            String origInst = input.get(i);
            String[] data = origInst.split(" ");
            String inst = data[0];
            switch(inst) {
                case "nop":
                    newInst = "jmp " + data[1];

                    input.remove(i);
                    input.add(i, newInst);

                    if(!HasInfinitieLoop(input)) {
                        System.out.println("Infinite Loop Fixed: ["+ ACCUMULATOR +"]");
                        return;
                    }
                    ACCUMULATOR = 0;
                    input.remove(i);
                    input.add(i, origInst);
                    continue;
                case "jmp":
                    newInst = "nop " + data[1];

                    input.remove(i);
                    input.add(i, newInst);

                    if(!HasInfinitieLoop(input)) {
                        System.out.println("Infinite Loop Fixed: ["+ ACCUMULATOR +"]");
                        return;
                    }
                    ACCUMULATOR = 0;
                    input.remove(i);
                    input.add(i, origInst);
                    continue;
            }
        }
        return;
    }

    private boolean HasInfinitieLoop(ArrayList<String> input) {

        HashSet<Integer> visitedInstructions = new HashSet<>();
        int curIndex = 0;

        while(!visitedInstructions.contains(curIndex) && curIndex < input.size()) {
            visitedInstructions.add(curIndex);
            String[] data = input.get(curIndex).split(" ");

            String inst = data[0];
            Integer count = Integer.parseInt(data[1]);

            switch (inst) {
                case "acc":
                    ACCUMULATOR += count;
                    curIndex++;
                    continue;
                case "jmp":
                    curIndex += count;
                    continue;
                case "nop":
                    curIndex++;
                    continue;
            }
        }
        return visitedInstructions.contains(curIndex);
    }

    public ArrayList<String> ReadAndFormatInformation(ArrayList<String> input) throws IOException {

        File file = new File("src/Day8_HandheldHalting/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String instruction;

        while ((instruction = reader.readLine()) != null) {
            input.add(instruction);
        }

        return input;
    }

    public static void main(String[] input) throws IOException {
        HandheldHalting halt = new HandheldHalting();
        ArrayList<String> results = new ArrayList<>();
        halt.ReadAndFormatInformation(results);
        if(halt.HasInfinitieLoop(results)) {
            System.out.println("INFINITE LOOP FOUND: ["+ halt.ACCUMULATOR + "]");
        }

        halt.IdentifyFix(results);
    }

}
