package Day4_PassportProcessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shivangipatwardhan on 12/6/20.
 */
public class PassportProcessing {

    private static Set<String> getPasswordFields() {
        Set<String> categories = new HashSet<>();
        categories.add("byr");
        categories.add("iyr");
        categories.add("eyr");
        categories.add("hgt");
        categories.add("hcl");
        categories.add("ecl");
        categories.add("pid");
        categories.add("cid");
        return categories;
    }

    private static final ArrayList<String> eyeColour = new ArrayList<String>() {{
        add("amb");
        add("blu");
        add("brn");
        add("gry");
        add("grn");
        add("hzl");
        add("oth");
    }};


    public static void main(String[] input) throws IOException {

        // read in the input
        File file = new File("src/Day4_PassportProcessing/input.txt");

        BufferedReader br = null;
        String st;
        br = new BufferedReader(new FileReader(file));

        StringBuffer passport = new StringBuffer("");
        int count = 0;
        while ((st = br.readLine()) != null) {

            // if empty line process passport and reset for new passport
            if (st.trim().isEmpty()) {
                if (isPassportValid(passport.toString(), true)) {
                    count++;
                }
                passport.setLength(0);
            } else {
                passport.append(" ");
                passport.append(st);
            }
        }

        System.out.println(count);
    }

    private static boolean isPassportValid(String passport, boolean extraValidation) {

        String[] allData = passport.trim().split(" ");
        int numCategories = allData.length;

        Set<String> categories = getPasswordFields();

        //if the array size isn't 7 something is missing or more -- must be invalid
        if (numCategories != 7 && numCategories != 8) {
            return false;
        }

        for (int i = 0; i < numCategories; i++) {
            String[] dataKeyValue = allData[i].split(":");
            String key = dataKeyValue[0];
            String value = dataKeyValue[1];

            if (!categories.contains(key) || (extraValidation && !validValue(key, value) )) {
                return false;
            }

            categories.remove(key);
        }

        return categories.size() == 0 || (categories.size() == 1 && categories.contains("cid")) ? true : false;
    }

    private static boolean validValue(String key, String value) {
        switch (key) {
            case "byr":
                return validateNumberInRange(value, 1920, 2002);
            case "iyr":
                return validateNumberInRange(value, 2010, 2020);
            case "eyr":
                return validateNumberInRange(value, 2020, 2030);
            case "hgt":
                return validateHeight(value);
            case "hcl":
                return validateHairColour(value);
            case "ecl":
                return eyeColour.contains(value);
            case "pid":
                return validatePasswordNum(value);
        }
        return true;
    }

    private static boolean validateNumberInRange(String value, int minVal, int maxVal) {
        int year = Integer.parseInt(value);
        return year >= minVal && year <= maxVal;
    }

    private static boolean validateHeight(String value) {
        int len = value.length();
        String units = value.substring(len-2);
        String height = value.substring(0, len-2);
        switch (units) {
            case "in":
                return validateNumberInRange(height, 59, 76);
            case "cm":
                return validateNumberInRange(height, 150, 193);
            default:
                return false;

        }
    }

    private static boolean validateHairColour(String value) {
        //hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        String hex = value.substring(1);
        if(hex.length() == 6 && value.charAt(0) == '#'){
            return hex.matches("[0-9A-Fa-f]+");
        }
        return false;
    }

    private static boolean validatePasswordNum(String value) {
        //pid (Passport ID) - a nine-digit number, including leading zeroes.
        if(value.length() == 9) {
            return value.matches("[0-9]+");
        }
        return false;
    }

}

