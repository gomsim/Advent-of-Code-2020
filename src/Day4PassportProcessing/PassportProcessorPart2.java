package Day4PassportProcessing;

import common.*;

public class PassportProcessorPart2 {

    public static void main(String[] args){
        int numValid = numValidPassports(new Adventofile("day4.txt"));
        System.out.println(numValid + " passports were... \"valid\"");
    }
    
    private static int numValidPassports(ListFeed feed){
        Checklist checklist = new Checklist();
        int counter = 0;

        while(feed.hasNext()){
            checklist.clear();
            String current;
            while(feed.hasNext() && !(current = feed.next()).isEmpty()){
                String[] fields = current.split(" ");
                for (String field: fields){
                    String[] fieldValuePair = field.split(":");
                    checklist.check(fieldValuePair[0], fieldValuePair[1]);
                }
            }
            if (checklist.allValid())
                counter++;
        }

        return counter;
    }

    private static class Checklist{

        boolean[] list;

        private void check(String field, String value){
            switch(field){
                case "byr": list[0] = value.matches("^(19[2-9][0-9]|200[0-2])$"); break;
                case "iyr": list[1] = value.matches("^20(1[0-9]|20)$"); break;
                case "eyr": list[2] = value.matches("^20(2[0-9]|30)$"); break;
                case "hgt": list[3] = value.matches("^(1([5-8][0-9]|9[0-3])cm|(59|6[0-9]|7[0-6])in)$"); break;
                case "hcl": list[4] = value.matches("#([0-9]|[a-f]){6}"); break;
                case "ecl": list[5] = value.matches("^(amb|blu|brn|gry|grn|hzl|oth)$"); break;
                case "pid": list[6] = value.matches("^[0-9]{9}$"); break;
            }
        }
        private void clear(){
            list = new boolean[7];
        }
        private boolean allValid(){
            for (boolean checkedField: list){
                if (!checkedField)
                    return false;
            }
            return true;
        }
    }
}