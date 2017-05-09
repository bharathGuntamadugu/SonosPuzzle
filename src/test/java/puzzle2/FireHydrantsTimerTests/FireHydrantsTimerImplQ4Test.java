package puzzle2.FireHydrantsTimerTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import puzzle2.FireHydrantsImplType;
import puzzle2.firehydrants.FireHydrants;
import puzzle2.firehydrants.FireHydrantsBasicImpl;
import puzzle2.firehydrants.FireHydrantsFactory;
import puzzle2.tester.Tester;
import puzzle2.tester.TesterFactory;
import puzzle2.tester.TesterPolicy;

/**
 * Created by bharath on 4/29/17.
 */

public class FireHydrantsTimerImplQ4Test {
    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;
    // One tester with capabilities as 1s to complete each task, maximum of 10 tasks in 12s rolling period, but with
    //  avg of 5 tasks per 12s in 36s avg time period.
    @Before
    public void setUp() throws Exception {
        implType = FireHydrantsImplType.TIMER;
        TesterPolicy policy = new TesterPolicy.Builder()
                .withNumTestsPerRollingPeriod(10)
                .withRollingPeriodInSecs(12)
                .withTimeToCompleteWorkInSecs(1)
                .withAvgNumOfTests(5)
                .withAvgTimePeriodInSecs(36)
                .build();
        List<Tester> testers = new LinkedList<>();
        testers.add(TesterFactory.getTester(policy, implType));
        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);
    }

    @Test
    public void testAverage() throws Exception {
        /**
         * Takes 10 request in one complete period, but can only take 5 in another period because of avg tasks limit.
         * avg of 5 tasks per 12s in 36s avg time period.
         * So first 12s, he served 10 requests,
         * In the next 6s, he served another 5 requests, thereby reaching 15 request limit for 36s avg time.
         * Hence the last request fails.
         */

        //First 12s
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);

        //11th request fails, as it crossed the limit of 10 requests in 12s.
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Thread.sleep(2000);

        //Next 6s
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);

        //After 6s. Fails as it reached the avg limit.
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }

    @Test
    public void testAverage2() throws Exception {
        /**
         * Takes 10 request in one complete period, but can only take 5 in another period because of avg tasks limit.
         * avg of 5 tasks per 12s in 36s avg time period.
         * So first 12s, he served 10 requests,
         * In the next 6s, he served another 5 requests, thereby reaching 15 request limit for 36s avg time.
         * Now thread sleeps to complete the 36s.
         * Now enters into next rolling avg time, and hence can accept the request.
         */
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);

        Thread.sleep(2000);

        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1020);

        //16th request fails
        Assert.assertFalse(fireHydrants.sellHydrants());

        Thread.sleep(20000);

        //Rolling avg period crossed, hence serving the new request.
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }

    //Similar test as tesAverage2(), but with two testers.
    @Test
    public void testAverage3() throws Exception {
        TesterPolicy policy = new TesterPolicy.Builder()
                .withNumTestsPerRollingPeriod(10)
                .withRollingPeriodInSecs(12)
                .withTimeToCompleteWorkInSecs(2)
                .withAvgNumOfTests(5)
                .withAvgTimePeriodInSecs(36)
                .build();
        List<Tester> testers = new LinkedList<>();
        testers.add(TesterFactory.getTester(policy, implType));
        testers.add(TesterFactory.getTester(policy, implType));

        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);

        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);

        Thread.sleep(2000);

        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2020);
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());

        Thread.sleep(20000);

        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(500);
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }

}
