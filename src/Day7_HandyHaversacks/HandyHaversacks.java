package Day7_HandyHaversacks;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shivangipatwardhan on 12/11/20.
 */
public class HandyHaversacks {

    String SHINY_GOLD = "shiny gold";

    private int ContainsAtLeastOneGolden(HashMap<String, ArrayList<String>> input) {
        //How many bag colors can eventually contain at least one shiny gold bag?
        HashSet<String> soln = new HashSet<>();
        Stack<String> bagsToExplore = new Stack<>();

        bagsToExplore.add(SHINY_GOLD);

        while(!bagsToExplore.empty()) {
            // all the bags that can contain a golden bag
            String curBagToExplore = bagsToExplore.pop();
            boolean foundTopParentBag = false;
            // for all the options
            for (Map.Entry<String, ArrayList<String>> entry : input.entrySet()) {
                // if current bag is one of the possible options, store the current key to explore more
                String parentBag = entry.getKey();
                ArrayList<String> bagOptions = entry.getValue();

                if(bagOptions.contains(curBagToExplore))
                {
                    bagsToExplore.add(parentBag);
                }
                else
                {
                    foundTopParentBag = true;
                }
            }

            if(foundTopParentBag) {
                soln.add(curBagToExplore);
            }
        }

        return soln.size() - 1;
    }

    public void PartOne() throws IOException {
        HashMap<String, String[]> data = ReadHaversacksInfo();
        HashMap<String, ArrayList<String>> processedInput = new HashMap<>();

        for(Map.Entry<String, String[]> entry: data.entrySet()) {
            ArrayList<String> nestedBagConditions = new ArrayList<>();
            for(String bagCondition: entry.getValue()) {
                nestedBagConditions.add(formatNestedConditionalBagInput(bagCondition, false));
            }
            processedInput.put(entry.getKey(), nestedBagConditions);
        }

        System.out.println(ContainsAtLeastOneGolden(processedInput));
    }

    private int CalculateBags(HashMap<String, ArrayList<String>> input, String bag) {

        Stack<String> bagsToExplore = new Stack<>();
        bagsToExplore.add(bag);

        Stack<Integer> numBags = new Stack<>();
        numBags.add(1);

        int count = 1;

        while(!bagsToExplore.isEmpty()) {
            String curBag = bagsToExplore.pop();
            Integer curCount = numBags.pop();
            int totalNestedBags = 0;

            ArrayList<String> nestedBags = input.get(curBag);

            for(int i = 0; i < nestedBags.size(); i++) {
                Pair<Integer, String> bagAndCount = getCountAndString(nestedBags.get(i));
                String nestedBag = bagAndCount.getValue();

                if(nestedBag == null) {
                    break;
                }

                // should still explore bags
                int quantity = bagAndCount.getKey();
                totalNestedBags += quantity;

                numBags.add(quantity * curCount);
                bagsToExplore.add(nestedBag);
            }

            count += (curCount * totalNestedBags);

        }
        return count-1;
    }

    public void PartTwo() throws IOException {
        HashMap<String, String[]> data = ReadHaversacksInfo();
        HashMap<String, ArrayList<String>> processedInput = new HashMap<>();

        for(Map.Entry<String, String[]> entry: data.entrySet()) {
            ArrayList<String> nestedBagConditions = new ArrayList<>();
            for(String bagCondition: entry.getValue()) {
                String bagInfo = formatNestedConditionalBagInput(bagCondition, true);
                nestedBagConditions.add(bagInfo);
            }
            processedInput.put(entry.getKey(), nestedBagConditions);
        }

        System.out.println(CalculateBags(processedInput, SHINY_GOLD));
    }


    // SHARED HELPER FUNCTIONS
    private String formatNestedConditionalBagInput(String bagCondition, boolean includeInt) {
        String bagReplacedCondition = bagCondition.replaceAll("bag(s)?", "");
        bagReplacedCondition = bagReplacedCondition.replaceAll("\\.", "").trim();
        if(includeInt){
            return bagReplacedCondition;
        }

        Pattern p = Pattern.compile("^\\S+\\s+(.+)$");
        Matcher m = p.matcher(bagReplacedCondition);
        return m.find() ? m.replaceAll("$1") : bagReplacedCondition;
    }

    private Pair<Integer, String> getCountAndString (String bagReplacedCondition) {
        if(bagReplacedCondition.contains("no other")) {
            return new Pair<Integer, String>(1, null);
        }

        Integer count = Integer.parseInt(bagReplacedCondition.split(" ")[0]);
        Pattern p = Pattern.compile("^\\S+\\s+(.+)$");
        Matcher m = p.matcher(bagReplacedCondition);
        String data = m.find() ? m.replaceAll("$1") : bagReplacedCondition;
        return new Pair<Integer, String>(count, data);
    }

    public HashMap<String, String[]> ReadHaversacksInfo() throws IOException {
        HashMap<String, String[]> input = new HashMap<>();

        File file = new File("src/Day7_HandyHaversacks/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String condition;

        while ((condition = reader.readLine()) != null) {
            String[] keyAndNestedConditions = condition.split("contain");
            String keyBag = keyAndNestedConditions[0].replaceAll("bag(s)?", "").trim();
            String[] nestedConditions = keyAndNestedConditions[1].split(",");
            input.put(keyBag, nestedConditions);
        }
        return input;
    }


    public static void main(String[] args) throws IOException {
        HandyHaversacks handyHaversacks = new HandyHaversacks();
        handyHaversacks.PartOne();
        handyHaversacks.PartTwo();
    }

}
