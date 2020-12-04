package Day4PassportProcessing;

import java.util.Arrays;

import common.*;
import java.util.List;

public class PassportProcessor {

    private static final List<String> mandatoryFields = Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid");

    public static void main(String[] args){
        int numValid = numValidPassports(new Adventofile("day4.txt"));
        System.out.println(numValid + " passports were... \"valid\"");
    }
    
    private static int numValidPassports(ListFeeder feed){
        int counter = 0;

        while(feed.hasNext()){
            boolean[] checklist = new boolean[mandatoryFields.size()];
            String current;
            while(feed.hasNext() && !(current = feed.next()).isEmpty()){
                String[] tokens = current.split(" ");
                for (String token: tokens){
                    int index = indexOfField(token);
                    if (index >= 0)
                        checklist[index] = true;
                }
            }
            if (allFieldsChecked(checklist))
                counter++;
        }

        return counter;
    }
    private static int indexOfField(String text){
        String[] tokens = text.split(":");
        return mandatoryFields.indexOf(tokens[0]);
    }
    private static boolean allFieldsChecked(boolean[] checklist){
        for (boolean checkedField: checklist){
            if (!checkedField)
                return false;
        }
        return true;
    }
}
