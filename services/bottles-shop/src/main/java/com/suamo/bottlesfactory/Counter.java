package com.suamo.bottlesfactory;

import org.springframework.stereotype.Component;

@Component
public class Counter {
    private int bottlesCount = 0;

    public int getBottlesCount() {
        return bottlesCount;
    }

    public void setBottlesCount(int bottlesCount) {
        this.bottlesCount = bottlesCount;
    }

    public void addBottles(int count) {
        this.bottlesCount += count;
    }

    public void subtractBottles(int count) {
        this.bottlesCount -= count;
    }
}
