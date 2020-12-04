package Day4PassportProcessing;

import common.*;

public class PassportProcessorPart2 {

    public static void main(String[] args){
        int numValid = numValidPassports(new Adventofile("day4.txt"));
        System.out.println(numValid + " passports were... \"valid\"");
    }
    
    private static int numValidPassports(ListFeed feed){
        int counter = 0;

        while(feed.hasNext()){
            Checklist checklist = new Checklist();
            String line;
            while(feed.hasNext() && !(line = feed.next()).isEmpty()){
                String[] fields = line.split(" ");
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

        boolean byr, iyr, eyr, hgt, hcl, ecl, pid;

        private void check(String field, String value){
            switch(field){
                case "byr": byr = value.matches("^(19[2-9][0-9]|200[0-2])$"); break;
                case "iyr": iyr = value.matches("^20(1[0-9]|20)$"); break;
                case "eyr": eyr = value.matches("^20(2[0-9]|30)$"); break;
                case "hgt": hgt = value.matches("^(1([5-8][0-9]|9[0-3])cm|(59|6[0-9]|7[0-6])in)$"); break;
                case "hcl": hcl = value.matches("#([0-9]|[a-f]){6}"); break;
                case "ecl": ecl = value.matches("^(amb|blu|brn|gry|grn|hzl|oth)$"); break;
                case "pid": pid = value.matches("^[0-9]{9}$"); break;
            }
        }
        private boolean allValid(){
            return byr && iyr && eyr && hgt && hcl && ecl && pid;
        }
    }
}