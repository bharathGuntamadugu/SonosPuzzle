package puzzle2.firehydrants;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import puzzle2.tester.Tester;

/**
 * Created by bharath on 5/8/17.
 */
public class FireHydrantsBasicImpl implements FireHydrants {
    private ReadWriteLock lock;
    private List<Tester> testers;

    public FireHydrantsBasicImpl(final List<Tester> testers) {
        this.testers = testers;
        // Using reentrant read write lock to allow multiple reads over testers list,
        // and only one write to access the list.
        lock = new ReentrantReadWriteLock(true);
    }

    @Override
    public boolean canSellHydrants() {
        boolean canSell = false;
        lock.readLock().lock();
        for (Tester tester: testers) {
            if (tester.isAvailable()) {
                canSell = true;
                break;
            }
        }
        lock.readLock().unlock();
        return canSell;
    }

    @Override
    public boolean sellHydrants() {
        boolean sell = false;
        lock.writeLock().lock();
        for(int i = 0; i < testers.size(); i++) {
            if (testers.get(i).isAvailable()) {
                Tester availableTester = testers.remove(i);
                availableTester.doWork();
                testers.add(availableTester);
                sell = true;
                break;
            }
        }
        lock.writeLock().unlock();
        return sell;
    }
}
