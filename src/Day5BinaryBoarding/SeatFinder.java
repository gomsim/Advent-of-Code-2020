package Day5BinaryBoarding;

import common.Adventofile;
import java.util.List;

public class SeatFinder {

    private static final char FRONT = 'F', BACK = 'B', RIGHT = 'R', LEFT = 'L';
    private static final int NUM_ROWS = 128, NUM_COLS = 8; 

    public static void main(String[] args){
        List<String> list = Adventofile.asList("day5.txt");
        System.out.println("The highest seatID is " + findHighestSeatID(list));
    }

    private static int findHighestSeatID(List<String> list){
        int highestID = 0;

        for (String line: list){
            int row = findIndex(NUM_ROWS, line.substring(0, 7));
            int col = findIndex(NUM_COLS, line.substring(7));
            int seatID = getSeatID(row, col);
            if (seatID > highestID)
                highestID = seatID;
        }

        return highestID;
    }

    private static int findIndex(int span, String instruction){
        int jumpDistance = span/2;
        int i = jumpDistance;
        int counter = instruction.length();

        for (char c: instruction.toCharArray()){
            counter--;
            jumpDistance /= 2;
            if (c == FRONT || c == LEFT)
                i -= jumpDistance == 0? 1:jumpDistance;
            else if ((c == BACK || c == RIGHT) && counter > 0)
                i += jumpDistance;
        }

        return i;
    }
    private static int getSeatID(int row, int column){
        return row * 8 + column;
    }
}
