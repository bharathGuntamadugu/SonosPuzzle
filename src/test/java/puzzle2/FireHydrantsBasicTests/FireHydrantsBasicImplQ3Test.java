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
public class FireHydrantsBasicImplQ3Test {
    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;
    private FakeTicker fakeTicker;
    // Two testers with capabilities as 5mins to complete each task, maximum of 5 tasks in 60 mins rolling period.
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
        fakeTicker.setTimeInSecs(5 * 60 + 10);
        //Again First tester takes the request.
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        //Again Second tester takes the request.
        Assert.assertTrue(fireHydrants.sellHydrants());

    }

    @Test
    public void testTwoTesters2() throws Exception {
        //First tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(1 * 60);
        //Second tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        //First tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(6 * 60);
        //Second tester
        Assert.assertTrue(fireHydrants.sellHydrants());

    }

    //Multiple request with in 5 mins.
    @Test
    public void testTwoTesters3() throws Exception {
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        Assert.assertTrue(fireHydrants.canSellHydrants());
    }

    //Random request to testers
    @Test
    public void testTwoTesters4() throws Exception {
        //First tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(1 * 60);
        //Second tester
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        //First tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(6 * 60);
        //Second tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(10 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(11 * 60);
        Assert.assertTrue(fireHydrants.sellHydrants());
    }
}
