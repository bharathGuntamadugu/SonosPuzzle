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
 * Created by bharath 5/8/17.
 */
public class FireHydrantsBasicImplQ2Test {
    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;
    private FakeTicker fakeTicker;
    // One tester with capabilities as 5 mins to complete each task, maximum of 5 tasks in 60 mins rolling period.
    @Before
    public void setUp() throws Exception {
        implType = FireHydrantsImplType.BASIC;
        fakeTicker = new FakeTicker();
        TesterPolicy policy = new TesterPolicy.Builder()
                .withNumTestsPerRollingPeriod(5)
                .withRollingPeriodInSecs(1 * 60 * 60)
                .withTimeToCompleteWorkInSecs(5 * 60)
                .withTicker(fakeTicker)
                .build();
        List<Tester> testers = new LinkedList<>();
        testers.add(TesterFactory.getTester(policy, implType));
        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);
    }

    // Rolling period limit test.
    @Test
    public void testcase1() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(10 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(15 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(20 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());

        /**
         * Above 5 Tests should be successful as they were called after every 5 mins. Rolling period has not exceeded as it is 60 mins.
         * Below one should be FALSE, as it has crossed the rolling period limit.
         **/
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
    }

    // Rolling period limit test success with only 5 tests only..
    @Test
    public void testcase2() throws Exception {
        for (int i = 1; i < 5; i++) {
            Assert.assertTrue(fireHydrants.canSellHydrants());
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs((i * 5) * 60);
        }

    }

    // Two Rolling period limit test success.
    @Test
    public void testcase3() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(10 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(15 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(20 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());

        // Reached the limit, hence should return false.
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());


        /** Above 5 Tests should be successful as they were called after every 5 mins.
         * Rolling period has exceeded as it is 60 mins
         * Next request will be successful as it is of the next rolling period.
         */
        fakeTicker.setTimeInSecs(60 * 60);

        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(65 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(70 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(75 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(80 * 60);
    }

    // Rolling period limit with random time requests.
    @Test
    public void testcase4() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(4 * 60);
        Assert.assertFalse(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(15 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(20 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(55 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(60 * 60);

        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(66 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(72 * 60);
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(75 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
    }
}
