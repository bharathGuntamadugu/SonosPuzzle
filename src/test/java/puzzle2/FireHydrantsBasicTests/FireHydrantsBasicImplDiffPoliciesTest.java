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
public class FireHydrantsBasicImplDiffPoliciesTest {
    private FireHydrants fireHydrants;
    private FireHydrantsImplType implType;
    private FakeTicker fakeTicker;
    // Two testers with capabilities as 5mins to complete each task, maximum of 5 tasks in 60 mins rolling period.
    @Before
    public void setUp() throws Exception {
        implType = FireHydrantsImplType.BASIC;
        fakeTicker = new FakeTicker();
        TesterPolicy policy1 = new TesterPolicy.Builder()
                .withNumTestsPerRollingPeriod(5)
                .withRollingPeriodInSecs(1 * 60 * 60)
                .withTimeToCompleteWorkInSecs(5 * 60)
                .withTicker(fakeTicker)
                .build();
        TesterPolicy policy2 = new TesterPolicy.Builder()
                .withNumTestsPerRollingPeriod(10)
                .withRollingPeriodInSecs(1 * 60 * 60)
                .withTimeToCompleteWorkInSecs(2 * 60)
                .withTicker(fakeTicker)
                .build();
        List<Tester> testers = new LinkedList<>();
        testers.add(TesterFactory.getTester(policy1, implType));
        testers.add(TesterFactory.getTester(policy2, implType));

        fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);
    }

    //Different request served as per the tester policies. Policy 2 tester serves more.
    @Test
    public void testTwoTesters1() throws Exception {
        //policy1 tester takes the request.
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        //policy2 tester takes the request.
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(2 * 60);
        //Policy2 tester takes the request.
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(4 * 60);
        //Policy2 tester takes the request.
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        //policy1 tester takes the request.
        Assert.assertTrue(fireHydrants.sellHydrants());
    }

    //Random request to testers
    @Test
    public void testTwoTesters2() throws Exception {
        //policy1 tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        fakeTicker.setTimeInSecs(1 * 60);
        //policy2 tester
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(5 * 60);
        //policy1 tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        fakeTicker.setTimeInSecs(6 * 60);
        //policy2 tester
        Assert.assertTrue(fireHydrants.canSellHydrants());
        Assert.assertTrue(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.sellHydrants());
        Assert.assertFalse(fireHydrants.canSellHydrants());
    }
}
