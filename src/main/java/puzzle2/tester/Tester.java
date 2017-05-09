package puzzle2.tester;

import puzzle2.Utils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by bharath on 4/29/17.
 */

public class Tester {
    protected Deque<Long> deque;
    protected int jobsCounter;
    protected long firstRequestTimeStamp;
    protected TesterPolicy policy;

    public Tester(final TesterPolicy policy) {
        this.policy = policy;
        this.deque = new LinkedList<>();
    }

    public void doWork() {
        long currTime = policy.getTicker().read();
        deque.addLast(currTime);
        if (jobsCounter == 0) {
            firstRequestTimeStamp = currTime;
        }
        jobsCounter++;
    }

    public boolean isAvailable() {
        cleanUpDeque();
        resetAvgCounter();
        return canWork();
    }

    protected boolean canWork() {
        if (deque.isEmpty()) {
            return true;
        }
        if (hasReachedAverageLimit()) {
            return false;
        }
        if (deque.size() >= policy.getNumTestsPerRollingPeriod()) {
            return false;
        }
        return true;
    }

    protected boolean hasReachedAverageLimit() {
        return jobsCounter >= policy.getAvgNumOfTests() * policy.getAvgTimePeriodInSecs()/policy.getRollingPeriodInSecs();
    }

    protected void cleanUpDeque() {
        long currentTime = policy.getTicker().read();
        while (!deque.isEmpty() && Utils.durationInSecs(deque.getFirst(), currentTime) >= policy.getRollingPeriodInSecs()) {
            deque.pollFirst();
        }
    }

    private void resetAvgCounter() {
        long currentTime = policy.getTicker().read();
        if (Utils.durationInSecs(firstRequestTimeStamp, currentTime) >= policy.getAvgTimePeriodInSecs()) {
            jobsCounter = 0;
        }
    }
}
