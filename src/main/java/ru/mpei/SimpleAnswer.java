package ru.mpei;

import java.util.Arrays;

public class SimpleAnswer {

    private final String pathList;
    private final double pathLength;
    private final boolean isFound;

    public SimpleAnswer(String answerContent) {
        String[] msgParts = answerContent.split("/");
        pathList = msgParts[0];
        pathLength = countPath(msgParts[1]);
        isFound = Boolean.parseBoolean(msgParts[2]);
    }

    public String getPathList() {
        return pathList;
    }

    public boolean isFound() {
        return isFound;
    }
    private double countPath(String pathWeightList){
        double toReturn = 0;
        String[] weights = pathWeightList.split("-");
        if (weights.length == 0){
            return Double.parseDouble(pathWeightList);
        }
        for (String w: weights){
            toReturn += Double.parseDouble(w);
        }
        return toReturn;
    }

    public double getPathLength() {
        return pathLength;
    }

    public void print(){
        System.out.println("Path: " + pathList);
        System.out.println("Path length: " + pathLength);
    }

}
