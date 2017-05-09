package puzzle2.tester;

import com.google.common.base.Ticker;

import static puzzle2.Constants.DEFAULT_AVG_NUM_TESTS_OVER_TIME_PERIOD;
import static puzzle2.Constants.DEFAULT_AVG_TIME_PERIOD_SECS;
import static puzzle2.Constants.DEFAULT_MIN_TIME_TO_WORK_SECS;
import static puzzle2.Constants.DEFAULT_NUM_TESTS_PER_HOUR;
import static puzzle2.Constants.DEFAULT_ROLLING_SECS;

/**
 * Created by bharath on 5/8/17.
 */
public class TesterPolicy {
    private int numTestsPerRollingPeriod;
    private long rollingPeriodInSecs;
    private long timeToCompleteWorkInSecs;
    private int avgNumOfTests;
    private long avgTimePeriodInSecs;
    private Ticker ticker;

    public TesterPolicy(final int numTestsPerRollingPeriod, final long rollingPeriodInSecs, final long timeToCompleteWorkInSecs,
                       final int avgNumOfTests, final long avgTimePeriodInSecs, final Ticker ticker) {
        this.numTestsPerRollingPeriod = numTestsPerRollingPeriod;
        this.rollingPeriodInSecs = rollingPeriodInSecs;
        this.timeToCompleteWorkInSecs = timeToCompleteWorkInSecs;
        this.avgNumOfTests = avgNumOfTests;
        this.avgTimePeriodInSecs = avgTimePeriodInSecs;
        this.ticker = ticker;
    }

    public int getNumTestsPerRollingPeriod() {
        return numTestsPerRollingPeriod;
    }

    public long getRollingPeriodInSecs() {
        return rollingPeriodInSecs;
    }

    public long getTimeToCompleteWorkInSecs() {
        return timeToCompleteWorkInSecs;
    }

    public int getAvgNumOfTests() {
        return avgNumOfTests;
    }

    public long getAvgTimePeriodInSecs() {
        return avgTimePeriodInSecs;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public static class Builder {
        private int numTestsPerRollingPeriod;
        private long rollingPeriodInSecs;
        private long timeToCompleteWorkInSecs;
        private int avgNumOfTests;
        private long avgTimePeriodInSecs;
        private Ticker ticker;

        public Builder withNumTestsPerRollingPeriod(final int numTestsPerRollingPeriod) {
            this.numTestsPerRollingPeriod = numTestsPerRollingPeriod;
            return this;
        }

        public Builder withRollingPeriodInSecs(final long rollingPeriodInSecs) {
            this.rollingPeriodInSecs = rollingPeriodInSecs;
            return this;
        }

        public Builder withTimeToCompleteWorkInSecs(final long timeToCompleteWorkInSecs) {
            this.timeToCompleteWorkInSecs = timeToCompleteWorkInSecs;
            return this;
        }

        public Builder withAvgNumOfTests(final int avgNumOfTests) {
            this.avgNumOfTests = avgNumOfTests;
            return this;
        }

        public Builder withAvgTimePeriodInSecs(final long avgTimePeriodInSecs) {
            this.avgTimePeriodInSecs = avgTimePeriodInSecs;
            return this;
        }

        public Builder withTicker(final Ticker ticker) {
            this.ticker = ticker;
            return this;
        }

        public TesterPolicy build() {
            this.numTestsPerRollingPeriod = numTestsPerRollingPeriod <= 0 ? DEFAULT_NUM_TESTS_PER_HOUR : numTestsPerRollingPeriod;
            this.rollingPeriodInSecs = rollingPeriodInSecs <= 0 ? DEFAULT_ROLLING_SECS : rollingPeriodInSecs;
            this.timeToCompleteWorkInSecs = timeToCompleteWorkInSecs <= 0 ? DEFAULT_MIN_TIME_TO_WORK_SECS : timeToCompleteWorkInSecs;
            this.avgNumOfTests = avgNumOfTests <= 0 ? DEFAULT_AVG_NUM_TESTS_OVER_TIME_PERIOD : avgNumOfTests;
            this.avgTimePeriodInSecs = avgTimePeriodInSecs <= 0 ? DEFAULT_AVG_TIME_PERIOD_SECS : avgTimePeriodInSecs;
            this.ticker = ticker == null ? Ticker.systemTicker() : ticker;

            return new TesterPolicy(numTestsPerRollingPeriod, rollingPeriodInSecs, timeToCompleteWorkInSecs,
                    avgNumOfTests, avgTimePeriodInSecs, ticker);
        }
    }

    @Override
    public String toString() {
        return "TesterPolicy{" +
                "numTestsPerRollingPeriod=" + numTestsPerRollingPeriod +
                ", rollingPeriodInSecs=" + rollingPeriodInSecs +
                ", timeToCompleteWorkInSecs=" + timeToCompleteWorkInSecs +
                ", avgNumOfTests=" + avgNumOfTests +
                ", avgTimePeriodInSecs=" + avgTimePeriodInSecs +
                '}';
    }
}
