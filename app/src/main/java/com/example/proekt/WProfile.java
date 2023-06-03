package com.example.proekt;

import java.util.ArrayList;
import java.util.List;


public class WProfile {
    private int number;
    List<List<String>> mass1 = new ArrayList<>();
    List<String> task1 = new ArrayList<>();

    List<String> answer1 = new ArrayList<>();
    List<String> answer2 = new ArrayList<>();



    public WProfile() {
        task1.add("@drawable/task_w_1_1");
        task1.add("@drawable/task_w_1_2");
        answer1.add("12,5");
        answer1.add("1,5");
        answer1.add("0,1");
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAnswerNumber(int number) {
        return answer1.get(number - 1).toString();
    }

}
