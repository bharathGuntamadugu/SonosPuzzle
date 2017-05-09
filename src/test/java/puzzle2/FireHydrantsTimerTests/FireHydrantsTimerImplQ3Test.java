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


public class FireHydrantsTimerImplQ3Test {
    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;

    // Two testers with capabilities as 2s to complete each task, maximum of 5 tasks in 12s rolling period.
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
        testers.add(TesterFactory.getTester(policy, implType));

        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);
    }


    @Test
    public void testTwoTesters1() throws Exception {
        //First tester takes the request.
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        //Second tester takes the request.
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
        //Again First tester takes the request.
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        //Again Second tester takes the request.
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }

    @Test
    public void testTwoTesters2() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1030);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1030);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1030);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }

    @Test
    public void testTwoTesters3() throws Exception {

        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Thread.sleep(2020);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Thread.sleep(2050);
    }

    @Test
    public void testTwoTesters4() throws Exception {
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(1030);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2030);
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Thread.sleep(500);
        Assert.assertFalse(fireHydrants.sellHydrants());
        Thread.sleep(1700);
        Assert.assertTrue(fireHydrants.sellHydrants());
        Thread.sleep(2050);
    }

}
