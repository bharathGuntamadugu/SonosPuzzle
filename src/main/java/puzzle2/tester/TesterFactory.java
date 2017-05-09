package puzzle2.tester;

import puzzle2.FireHydrantsImplType;

/**
 * Created by bharath on 4/29/17.
 */

// Used to create different tester implementation objects based on their type.
public class TesterFactory {
    public static Tester getTester(TesterPolicy policy, FireHydrantsImplType type) {
        if (type == FireHydrantsImplType.TIMER) {
            return new TesterTimer(policy);
        } else if (type == FireHydrantsImplType.BASIC) {
            return new TesterBasic(policy);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
