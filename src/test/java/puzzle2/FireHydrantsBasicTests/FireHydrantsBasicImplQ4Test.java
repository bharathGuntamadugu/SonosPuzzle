package puzzle2.FireHydrantsBasicTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import puzzle2.FakeTicker;
import puzzle2.FireHydrantsImplType;
import puzzle2.firehydrants.FireHydrants;
import puzzle2.firehydrants.FireHydrantsFactory;
import puzzle2.tester.Tester;
import puzzle2.tester.TesterFactory;
import puzzle2.tester.TesterPolicy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bharath on 5/8/17.
 */
public class FireHydrantsBasicImplQ4Test {
    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;
    private FakeTicker fakeTicker;
    // One tester with capabilities as 5 mins to complete each task, maximum of 10 tasks in 60 min rolling period, but with
    //  avg of 5 tasks per 60 mins in 2 hrs avg time period.
    @Before
    public void setUp() throws Exception {
        implType = FireHydrantsImplType.BASIC;
        fakeTicker = new FakeTicker();
        TesterPolicy policy = new TesterPolicy.Builder()
                .withNumTestsPerRollingPeriod(10)
                .withRollingPeriodInSecs(1 * 60 * 60)
                .withTimeToCompleteWorkInSecs(5 * 60)
                .withAvgNumOfTests(5)
                .withAvgTimePeriodInSecs(2 * 60 * 60)
                .withTicker(fakeTicker)
                .build();
        List<Tester> testers = new LinkedList<>();
        testers.add(TesterFactory.getTester(policy, implType));
        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);
    }

    @Test
    public void testAverage() throws Exception {
        /**
         * Takes 10 request in one complete period, but can only take 5 in another period because of avg tasks limit.
         * avg of 5 tasks per 1hr in 2hrs avg time period.
         * So first 1hr, he served 10 requests,
         * But in the already served 10 request, so to maintain the average of 5 per hour over 2 hr avg period, he cannot serve anymore requests.
         * Hence the last request fails.
         */

        //First 60 mins
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(10 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(15 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(20 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(25 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(30 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(35 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(40 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(45 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());

        //11th request fails, as it crossed the limit of 10 requests in 60 mins.
        Assert.assertFalse(fireHydrants.canSellHydrants());

        fakeTicker.setTimeInSecs(60 * 60);
        //Next 60 mins
        //Fails as it reached the avg limit of 5 per hour.
        Assert.assertFalse(fireHydrants.sellHydrants());
    }

    @Test
    public void testAverage2() throws Exception {
        /**
         * Takes 10 request in one complete period, but can only take 5 in another period because of avg tasks limit.
         * avg of 5 tasks per 1hr in 2hrs avg time period.
         * So first 1hr, he served 10 requests,
         * But in the already served 10 request, so to maintain the average of 5 per hour over 2 hr avg period, he connot server anymore requests for 2 hrs.
         * Will start serving after 2 hrs.
         */

        //First 60 mins
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(10 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(15 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(20 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(25 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(30 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(35 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(40 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(45 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());

        //11th request fails, as it crossed the limit of 10 requests in 60 mins.
        Assert.assertFalse(fireHydrants.canSellHydrants());
        //Will start serving after 120 mins...hence asserts true.
        fakeTicker.setTimeInSecs(120 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(125 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
    }

}
