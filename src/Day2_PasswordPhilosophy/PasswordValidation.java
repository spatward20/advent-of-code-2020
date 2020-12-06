package Day2_PasswordPhilosophy;

import java.io.*;

/**
 * Created by shivangipatwardhan on 12/6/20.
 */
public class PasswordValidation {

    public static boolean isValidPasswordPolicyOne(int min, int max, char c, String password) {
        int count = 0;

        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == c) {
               count++;
            }
        }

        return (count >= min && count <= max) ? true : false;
    }

    public static boolean isValidPasswordPolicyTwo(int one, int two, char c, String password) {
        return ((password.charAt(one) == c && password.charAt(two) != c) ||
                (password.charAt(one) != c && password.charAt(two) == c)) ? true : false;
    }


    public static void main(String[] args) throws IOException {
        // read in the input
        File file = new File("src/Day2_PasswordPhilosophy/input.txt");

        BufferedReader br = null;
        String st;
        br = new BufferedReader(new FileReader(file));

        int validPasswords = 0;

        while ((st = br.readLine()) != null) {
            //3-15 d: dpdgmdnfsdbgdddbdmcd
            String[] result = st.split(":");

            String[] constraints = result[0].split(" ");

            String[] counts = constraints[0].split("-");
            int minInt = Integer.parseInt(counts[0]);
            int maxInt = Integer.parseInt(counts[1]);

            char letter = constraints[1].charAt(0);

            String password = result[1].trim();

//            if(isValidPasswordPolicyOne(minInt, maxInt, letter, password)) {
//                validPasswords++;
//            }
            if(isValidPasswordPolicyTwo(minInt-1, maxInt-1, letter, password)) {
                validPasswords++;
            }

        }

        System.out.println(validPasswords);
    }
}

