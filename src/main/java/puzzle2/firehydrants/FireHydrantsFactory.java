package puzzle2.firehydrants;

import java.util.List;

import puzzle2.tester.Tester;
import puzzle2.FireHydrantsImplType;

/**
 * Created by bharath on 5/8/17.
 */
public class FireHydrantsFactory {
    public static FireHydrants getFireHydrants(List<Tester> testers, FireHydrantsImplType type) {
        if (type == FireHydrantsImplType.TIMER) {
            return new FireHydrantsTimerImpl(testers);
        } else if (type == FireHydrantsImplType.BASIC) {
            return new FireHydrantsBasicImpl(testers);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
