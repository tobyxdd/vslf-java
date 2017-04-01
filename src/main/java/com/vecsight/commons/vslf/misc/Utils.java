package com.vecsight.commons.vslf.misc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static boolean waitExecutorTerm(ExecutorService executorService) {
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

}
