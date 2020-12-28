package Day5_BinaryBoarding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by shivangipatwardhan on 12/7/20.
 */
public class BinaryBoarding {

    //Use binary search
    private static int binarySearch(String directions, int start, int end, Character lowerChar, Character highChar) {
        int rowStart = start;
        int rowEnd = end;

        for(Character ch : directions.toCharArray()) {

            if(ch.equals(lowerChar))
            {
                rowEnd = (rowEnd + rowStart)/2;
            }
            else if(ch.equals(highChar))
            {
                rowStart = (rowEnd + rowStart)/2 + 1;
            }
        }

        return rowStart;
    }

    private static void findMissingSeat(List<Integer> ids) {
        Collections.sort(ids);
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i < ids.size() - 2; i++) {
            Integer curr = ids.get(i);
            Integer next = ids.get(i+1);
            if (next - curr == 2) {
                candidates.add(curr+1);
            }
        }
        System.out.println("Missing seat is: " + candidates);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> seatIds = new ArrayList<>();
        int maxSeatId = 0;

        // Part 1
        try {
            File file = new File("src/Day5_BinaryBoarding/input.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                int row = binarySearch(line.substring(0, 7), 0, 127, 'F', 'B');
                int seat = binarySearch(line.substring(7), 0, 7, 'L', 'R');
                int seatId = (row * 8) + seat;
                seatIds.add(seatId);
                maxSeatId = Math.max(seatId, maxSeatId);
            }
            input.close();
            System.out.println(maxSeatId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Part 2
        findMissingSeat(seatIds);
    }

}
