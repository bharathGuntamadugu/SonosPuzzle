package puzzle2;

import com.google.common.base.Ticker;

import java.util.concurrent.TimeUnit;

/**
 * Created by bharath on 5/8/17.
 */
public class FakeTicker extends Ticker {

    private long fakeTime = 0L;

    @Override
    public long read() {
        return fakeTime;
    }

    public void setTimeInSecs(long sec) {
        fakeTime = TimeUnit.SECONDS.toNanos(sec);

    }


}
