package puzzle2;

import java.util.concurrent.TimeUnit;

/**
 * Created by bharath on 4/29/17.
 */

public class Utils {

    public static long nanosToSecs(long nanos) {
        return TimeUnit.NANOSECONDS.toSeconds(nanos);
    }

    public static long durationInSecs(long startNanos, long endNanos) {
        return nanosToSecs(endNanos) - nanosToSecs(startNanos);
    }
}
