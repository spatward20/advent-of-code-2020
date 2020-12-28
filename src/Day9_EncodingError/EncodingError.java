package Day9_EncodingError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shivangipatwardhan on 12/13/20.
 */
public class EncodingError {

    private static boolean validSumation(List<BigInteger> input, BigInteger sumTotal) {
        ArrayList<BigInteger> soln = new ArrayList<>();

        for(int i = 0; i < input.size(); i++) {
            BigInteger curNum = input.get(i);
            BigInteger value = sumTotal.subtract(curNum);
            if(soln.contains(value)) {
                return true;
            }
            else
            {
                soln.add(curNum);
            }
        }
        return false;
    }

    public BigInteger FlawInTheSystem(ArrayList<BigInteger> inputData, int preembleSize) {

        for(int i = preembleSize; i < inputData.size(); i++) {
            BigInteger curNumber = inputData.get(i);
            List<BigInteger> subList = inputData.subList(i-preembleSize, i);
            if(!validSumation(subList, inputData.get(i))){
                return curNumber;
            }
        }

        return new BigInteger("0");
    }


    //subSequence that adds up to specified total
    //find a contiguous set of at least two numbers
    private void BreakTheCode(ArrayList<BigInteger> inputData, BigInteger total) {
        BigInteger curTotal = new BigInteger("0");

        int winStart = 0;
        int curIndex = 0;

        while(winStart < inputData.size()) {
            BigInteger curNum;


            while( curTotal.compareTo(total) < 0) {
                curNum = inputData.get(curIndex);
                curTotal = curTotal.add(curNum);
                curIndex++;
            }

            if(curTotal.compareTo(total) == 0) {
                List<BigInteger> subArray = inputData.subList(winStart, curIndex);
                Collections.sort(subArray);
                System.out.println("SOLN: " + subArray.get(0).add(subArray.get(subArray.size()-1)));
                break;
            }

            winStart++;
            curIndex = winStart;
            curTotal = new BigInteger("0");
        }
    }

    private void ProcessIncomingData(ArrayList<BigInteger> inputData) throws IOException {
        File file = new File("src/Day9_EncodingError/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String instruction;

        while ((instruction = reader.readLine()) != null) {
            inputData.add(new BigInteger(instruction));
        }
    }

    public static void main(String[] arg) throws IOException {
        EncodingError err = new EncodingError();
        ArrayList<BigInteger> inputData = new ArrayList<>();
        err.ProcessIncomingData(inputData);
        BigInteger flawedNumber = err.FlawInTheSystem(inputData, 25);
        System.out.println(flawedNumber);
        err.BreakTheCode(inputData, flawedNumber);
    }
}
