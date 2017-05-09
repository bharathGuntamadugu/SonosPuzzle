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

public class FireHydrantsTimerImplQ1Test {

    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;

    // One tester with capabilities as 2s to complete each task.
    // Added additional properties to make the testing fast. Have maximum of 100 tasks in 200s rolling period, so each 2s one request.

    @Before
    public void setUp() throws Exception {
        implType = FireHydrantsImplType.TIMER;
        TesterPolicy policy = new TesterPolicy.Builder()
                                .withNumTestsPerRollingPeriod(100)
                                .withRollingPeriodInSecs(200)
                                .withTimeToCompleteWorkInSecs(2)
                                .build();
        List<Tester> testers = new LinkedList<>();
        testers.add(TesterFactory.getTester(policy, implType));

        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);

    }

    // Sending second request after the time to complete each request.
    @Test
    public void testcase1() throws Exception {
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2100);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2100);
    }

    //Testing the canSellHydrants method.
    @Test
    public void testcase2() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Thread.sleep(2100);
    }

    // Sending second request before the time to complete each request.
    @Test
    public void testcase3() throws Exception {
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(500);
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(2100);
    }

    // Sending second request before the time to complete each request, but third request after the time to complete each request
    @Test
    public void testcase4() throws Exception {
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(500);
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(1600);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2100);
    }

    //Request every two secs.
    @Test
    public void testcase5() throws Exception {
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2100);
    }

    // Random requests at different times.
    @Test
    public void testcase6() throws Exception {
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(3050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(50);
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(5000);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1000);
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(2500);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2100);
    }

    // Random requests at different times, but with a gap of two secs.
    @Test
    public void testcase7() throws Exception {
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(3050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(5000);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(3000);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2500);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2100);
    }

    // Random requests to both sellHydrants() and canSellHydrants() at different times.
    @Test
    public void testcase8() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(50);
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2100);
    }
}
