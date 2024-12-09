package y2k24.day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class HistorianHysteria {
    public static void readColumn(String filePath, List<Integer> leftList, List<Integer> rightList){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] parts = line.split("\\s+");
                leftList.add(Integer.parseInt(parts[0]));
                rightList.add(Integer.parseInt(parts[1]));
            }

            leftList.sort(Comparator.naturalOrder());
            rightList.sort(Comparator.naturalOrder());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int similarityScore(List<Integer> leftList, List<Integer> rightList){
        int howMany = 0;
        int total = 0;
        for(int i = 0; i < leftList.size(); i++){
            int number = leftList.get(i);
            for(int j = 0; j < rightList.size(); j++){
                if(number == rightList.get(j)){
                    howMany++;
                }
            }
            total += (number * howMany);
            howMany = 0;
        }
        return total;
    }

    public static int totalDistance(List<Integer> leftList, List<Integer> rightList){
        int total = 0;
        for(int i = 0; i < leftList.size(); i++){
            total += Math.abs(leftList.get(i) - rightList.get(i));
        }
        return total;
    }

    public static void main(String[] args){
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        readColumn("src/main/java/y2k24/day01/input.txt", leftList, rightList);

        System.out.println("Total distance is: " + totalDistance(leftList, rightList));
        System.out.println("Similarity score is: " + similarityScore(leftList, rightList));
    }
}
