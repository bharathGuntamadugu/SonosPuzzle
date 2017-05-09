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

public class FireHydrantsTimerImplQ2Test {
    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;
    // One tester with capabilities as 2s to complete each task, maximum of 5 tasks in 12s rolling period.
    @Before
    public void setUp() throws Exception {
        implType = FireHydrantsImplType.TIMER;
        TesterPolicy policy = new TesterPolicy.Builder()
                .withNumTestsPerRollingPeriod(5)
                .withRollingPeriodInSecs(12)
                .withTimeToCompleteWorkInSecs(2)
                .build();
        List<Tester> testers = new LinkedList<>();
        testers.add(TesterFactory.getTester(policy, implType));
        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);
    }

    // Rolling period limit test failure.
    @Test
    public void testcase1() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2500);
        /**
         * Above 5 Tests should be successful as they were called after every two seconds.
         * Rolling period has not exceeded as it is 12 secs.
         * Below one should be FALSE, as it has crossed the rolling period limit.
         **/
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(2050);

    }

    // Rolling period limit test success.
    @Test
    public void testcase2() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);

    }

    // Two Rolling period limit test success.
    @Test
    public void testcase3() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);

        Thread.sleep(2750);

        /** Above 5 Tests should be successful as they were called after every two seconds.
         * Rolling period has exceeded as it is 12 secs, as we made the thread sleep for 2.7s.
         * Next request will be successful as it is of the ßnext rolling period.
         */

        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }

    // Rolling period limit with random time requests.
    @Test
    public void testcase4() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(50);
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(2100);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(3050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(3050);

        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(3050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }
}

