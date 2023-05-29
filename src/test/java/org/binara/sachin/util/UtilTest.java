package org.binara.sachin.util;

import junit.framework.TestCase;
import org.junit.Test;

public class UtilTest extends TestCase {

    public void testSleep() {
        int time = 1000;
        long startTime = System.currentTimeMillis();
        Util.sleep(time);
        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime >= time);
    }

    public void testGetRandomNumberLessThan() {
        int max = 10;
        int randomNumber = Util.getRandomNumberLessThan(max);
        assertTrue(randomNumber < max);
    }

    public void testSleepRandomTime() {
        int maxTime = 1000;
        long startTime = System.currentTimeMillis();
        Util.sleepRandomTime(maxTime);
        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime >= 0 && endTime - startTime < maxTime);
    }
}