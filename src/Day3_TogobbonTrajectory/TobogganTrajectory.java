package Day3_TogobbonTrajectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by shivangipatwardhan on 12/6/20.
 */
public class TobogganTrajectory {
    //    Toboggan Trajectory
    // reset x keep y the same, until y = maxLength to replicate the idea of forever continuing hill side.
    private static int calculateTrajectory(ArrayList<ArrayList<Character>> slopes, int xSlope, int ySlope) {
        int trees = 0;

        int curRow = 0;
        int numRows = slopes.size();
        int curCol = 0;
        int numCol = slopes.get(0).size();

        while(curRow < numRows) {
            // find current location
            ArrayList<Character> row = slopes.get(curRow);
            Character curLocation = row.get(curCol);

            // if current location is a #, you hit a tree
            if(curLocation.equals('#')) {
                trees++;
            }

            curRow += ySlope;
            curCol += xSlope;

            if(numCol <= curCol){
                curCol = curCol % numCol;
            }

        }

        return trees;
    }

    public static void main(String[] input) throws IOException {

        // read in the input
        File file = new File("src/Day3_TogobbonTrajectory/input.txt");

        BufferedReader br = null;
        String st;
        br = new BufferedReader(new FileReader(file));

        ArrayList<ArrayList<Character>> slopes = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            ArrayList<Character> slope = new ArrayList<>();
            char[] slopeRow = st.toCharArray();

            for(int i = 0; i < slopeRow.length; i++) {
                slope.add(slopeRow[i]);
            }
            slopes.add(slope);
        }
//        for(int i = 0; i < slopes.size(); i++) {
//            System.out.println(slopes.get(i));
//        }
        int slope1 = calculateTrajectory(slopes, 3, 1);
        int slope2 = calculateTrajectory(slopes, 1, 1);
        int slope3 = calculateTrajectory(slopes, 5, 1);
        int slope4 = calculateTrajectory(slopes, 7, 1);
        int slope5 = calculateTrajectory(slopes, 1, 2);
        BigInteger soln = BigInteger.valueOf(slope1 * slope2);
        BigInteger soln2 = BigInteger.valueOf(slope3 * slope4 * slope5);

        System.out.println(soln.multiply(soln2));
    }
}
