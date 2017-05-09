package puzzle2.tester;

import puzzle2.Utils;

/**
 * Created by bharath on 5/8/17.
 */
public class TesterBasic extends Tester {

    public TesterBasic(TesterPolicy policy) {
        super(policy);
    }

    @Override
    public boolean canWork() {
        boolean canWork = super.canWork();
        if (!deque.isEmpty()) {
            return canWork &&
                    Utils.durationInSecs(deque.peekLast(), policy.getTicker().read()) >= policy.getTimeToCompleteWorkInSecs();
        }
        return canWork;
    }
}
