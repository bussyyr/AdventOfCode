package y2k24.day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RedNosedReports {
    public static List<List<Integer>> readReport(String filePath){
        List<List<Integer>> reports = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] parts = line.split("\\s+");
                List<Integer> reportList = new ArrayList<>();

                for(String part : parts){
                    int number = Integer.parseInt(part);
                    reportList.add(number);
                }
                reports.add(reportList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return reports;
    }

    public static boolean isSafe(List<Integer> report){
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for(int i = 1; i < report.size(); i++){
            int difference = Math.abs(report.get(i) - report.get(i-1));
            if(difference < 1 || difference > 3){
                return false;
            }
            if(report.get(i) < report.get(i-1)){
                isIncreasing = false;
            }
            if(report.get(i) > report.get(i-1)){
                isDecreasing = false;
            }
        }
        return isIncreasing || isDecreasing;
    }

    public static boolean isSafeWithoutOneLevel(List<Integer> report){
        if(isSafe(report)){
            return true;
        }

        for(int i = 0; i < report.size(); i++){
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if(isSafe(modifiedReport)){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args){
        List<List<Integer>> reports = readReport("src/main/java/y2k24/day02/input.txt");
        int safeNumber = 0;
        int fakeSafeNumber = 0;
        for(int i = 0; i < reports.size(); i++){
            if(isSafeWithoutOneLevel(reports.get(i))){
                fakeSafeNumber++;
                if(isSafe(reports.get(i))){
                    safeNumber++;
                }
            }
        }

        System.out.println("Total original safe reports: " + safeNumber);
        System.out.println("Total safe reports after removing one level: " + fakeSafeNumber);
    }
}
