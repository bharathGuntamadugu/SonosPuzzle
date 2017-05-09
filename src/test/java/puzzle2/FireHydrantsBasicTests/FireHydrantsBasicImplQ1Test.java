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
public class FireHydrantsBasicImplQ1Test {
        private FireHydrants fireHydrants;
        private FireHydrantsImplType implType;
        private FakeTicker fakeTicker;

        // One tester with capabilities as 5 min to complete each task.

        @Before
        public void setUp() throws Exception {
            implType = FireHydrantsImplType.BASIC;
            fakeTicker = new FakeTicker();
            TesterPolicy policy = new TesterPolicy.Builder()
                    .withNumTestsPerRollingPeriod(100)
                    .withRollingPeriodInSecs(1 * 60 *60)
                    .withTimeToCompleteWorkInSecs(5 * 60)
                    .withTicker(fakeTicker)
                    .build();
            List<Tester> testers = new LinkedList<>();
            testers.add(TesterFactory.getTester(policy, implType));

            fireHydrants = FireHydrantsFactory.getFireHydrants(testers, implType);

        }

        // Sending second request after the time to complete each request.
        @Test
        public void testcase1() throws Exception {
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(5 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());
        }

        //Testing the canSellHydrants method.
        @Test
        public void testcase2() throws Exception {
            Assert.assertTrue(fireHydrants.canSellHydrants());
            Assert.assertTrue(fireHydrants.canSellHydrants());
        }

        // Sending second request before the time to complete each request.
        @Test
        public void testcase3() throws Exception {
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(4 * 60);
            Assert.assertFalse(fireHydrants.canSellHydrants());
            Assert.assertFalse(fireHydrants.sellHydrants());

        }

        // Sending second request before the time to complete each request, but third request after the time to complete each request
        @Test
        public void testcase4() throws Exception {
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(4 * 60);
            Assert.assertFalse(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(5 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());
        }

        //Request every 5 mins.
        @Test
        public void testcase5() throws Exception {
            for (int i = 1; i < 5; i++) {
                Assert.assertTrue(fireHydrants.canSellHydrants());
                Assert.assertTrue(fireHydrants.sellHydrants());
                fakeTicker.setTimeInSecs((5 * i) * 60);
            }
        }

        // Random requests at different times.
        @Test
        public void testcase6() throws Exception {
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(5 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(9 * 60 + 58);
            Assert.assertFalse(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(15 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(19 * 60 + 59);
            Assert.assertFalse(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(20 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());

        }

        // Random requests at different times, but with a gap of 5 mins.
        @Test
        public void testcase7() throws Exception {
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(5 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(10 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(15 * 60);
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(20 * 60 );
            Assert.assertTrue(fireHydrants.sellHydrants());

        }

        // Random requests to both sellHydrants() and canSellHydrants() at different times.
        @Test
        public void testcase8() throws Exception {
            Assert.assertTrue(fireHydrants.canSellHydrants());
            Assert.assertTrue(fireHydrants.canSellHydrants());
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(5 * 60);
            Assert.assertTrue(fireHydrants.canSellHydrants());
            Assert.assertTrue(fireHydrants.sellHydrants());
            Assert.assertFalse(fireHydrants.canSellHydrants());
            fakeTicker.setTimeInSecs(10 * 60);
            Assert.assertTrue(fireHydrants.canSellHydrants());
            Assert.assertTrue(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(13 * 60);
            Assert.assertFalse(fireHydrants.canSellHydrants());
            Assert.assertFalse(fireHydrants.canSellHydrants());
            Assert.assertFalse(fireHydrants.sellHydrants());
            fakeTicker.setTimeInSecs(15 * 60);
            Assert.assertTrue(fireHydrants.canSellHydrants());
            Assert.assertTrue(fireHydrants.sellHydrants());
        }
}
