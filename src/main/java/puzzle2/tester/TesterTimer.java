package puzzle2.tester;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import puzzle2.Utils;
import puzzle2.firehydrants.FireHydrants;
import puzzle2.firehydrants.FireHydrantsTimerImpl;

/**
 * Created by bharath on 4/29/17.
 */
public class TesterTimer extends Tester implements Runnable{
    private Timer timer;
    private FireHydrants subscriber;

    public TesterTimer(TesterPolicy policy) {
        super(policy);
        this.timer = new Timer();
    }

    /**
     * fireHydrantsImpl.sellHydrant() calls doWork() when this tester is available.
     *
     * Tester stores the timeStamp in the deque and schedule the timer task after @timeToCompleteWorkInSecs.
    **/

    @Override
    public void run() {
        doWork();
    }

    @Override
    public void doWork() {
        super.doWork();
        scheduleCanWork(policy.getTimeToCompleteWorkInSecs());
    }

    /**
     * TimerTask do the following tasks after scheduled timer time exceeds.
     *  -  Cleans up the deque
     *      -  Looks into the timestamps of deque starting from the first item
     *         and removes them, if they have exceeded the rolling period.
     *  -  Checks if it can be available to take next request.
     *      -  Checks if has exceeded the rolling average.
     *      -  Else, checks if it can take next request.
     *  -  Schedules the timer recursively if required.
     */

    public void scheduleCanWork (long timeToScheduleInSecs) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cleanUpDeque();
                if (canWork()) {
                    ((FireHydrantsTimerImpl)subscriber).callback(TesterTimer.this);
                } else {
                    long timeToCallBackAgain;
                    if (hasReachedAverageLimit()) {
                        timeToCallBackAgain = policy.getAvgTimePeriodInSecs() -
                                Utils.durationInSecs(firstRequestTimeStamp, deque.peekLast());
                        jobsCounter = 0;
                    } else {
                        timeToCallBackAgain = policy.getRollingPeriodInSecs() -
                                Utils.durationInSecs(deque.peekFirst(), policy.getTicker().read());
                    }
                    scheduleCanWork(timeToCallBackAgain);
                }
            }
        }, TimeUnit.SECONDS.toMillis(timeToScheduleInSecs));
    }

    public void registerCallback(final FireHydrants subscriber) {
        this.subscriber = subscriber;
    }
}
