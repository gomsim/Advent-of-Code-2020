package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Adventofile{

    BufferedReader reader;
    String nextLine;

    public Adventofile(String file){
        try{
            reader = new BufferedReader(new FileReader("input/" + file));
            nextLine = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String readLine(){
        String line = "";
        try{
            line = nextLine;
            nextLine = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return line;
    }
    public boolean hasNext(){
        return nextLine != null;
    }
    public void close(){
        try{
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static List<String> asList(String file){
        Adventofile filePump = new Adventofile(file);
        List<String> list = new ArrayList<>();

        while(filePump.hasNext()){
            list.add(filePump.readLine());
        }
        filePump.close();

        return list;
    }
    public static char[] asCharArray(String file){
        Adventofile filePump = new Adventofile(file);
        List<Character> list = new ArrayList<>();
        
        while(filePump.hasNext()){
            for (char c: filePump.readLine().toCharArray()){
                list.add(c);
            }
        }
        char[] array = new char[list.size()];
        for (int i = 0; i < array.length; i++){
            array[i] = list.get(i).charValue();
        }
        filePump.close();

        return array;
    }

    public static char[][] asCharMatrix(String file){
        List<String> list = asList(file);
        
        char[][] matrix = new char[list.get(0).length()][list.size()];
        for (int y = 0; y < list.size(); y++){
            for (int x = 0; x < list.get(0).length(); x++){
                matrix[x][y] = list.get(y).charAt(x);
            }   
        }

        return matrix;
    }
}