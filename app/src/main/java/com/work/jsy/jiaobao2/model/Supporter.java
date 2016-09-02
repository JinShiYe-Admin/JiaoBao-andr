package com.work.jsy.jiaobao2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShangLinMo on 2016/9/1.
 */
public class Supporter {
    private int number;
    private List allSupporters = new ArrayList();

//    public Supporter(int number, List allSupporters) {
//        this.number = number;
//        this.allSupporters = allSupporters;
//    }

    public List getAllSupporters() {
        return allSupporters;
    }

    public void setAllSupporters(List allSupporters) {
        this.allSupporters = allSupporters;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
