package com.example.proekt;

import java.util.ArrayList;
import java.util.List;

public class TProfile {

    public int number;

    List<String> answer1 = new ArrayList<>();
    List<String> answer2 = new ArrayList<>();

    public TProfile() {
        answer1.add("5");
        answer2.add("3");
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAnswer1Number(int number) {
        return answer1.get(number - 1).toString();
    }

    public String getAnswer2Number(int number) {
        return answer2.get(number - 1).toString();
    }

}
