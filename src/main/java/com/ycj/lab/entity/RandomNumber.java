package com.ycj.lab.entity;

import java.util.Random;

public class RandomNumber {
    public int randInt() {
        Random r = new Random();
        return r.nextInt(100);
    }
}
