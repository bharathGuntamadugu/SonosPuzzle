package puzzle2.firehydrants;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import puzzle2.tester.Tester;
import puzzle2.tester.TesterTimer;


/**
 * Created by bharath on 4/29/17.
 */
public class FireHydrantsTimerImpl implements FireHydrants {
    private Deque<Tester> availableTesters;
    private ReadWriteLock lock;
    private ScheduledThreadPoolExecutor threadPool;

    public FireHydrantsTimerImpl(final List<Tester> testers) {
        availableTesters = new LinkedList<>();
        availableTesters.addAll(testers);

        //Using reentrant read write lock to allow multiple reads over the availableTesters list,
        // and only one write to access the list.
        lock = new ReentrantReadWriteLock(true);
        threadPool = new ScheduledThreadPoolExecutor(testers.size());
    }

    @Override
    public boolean canSellHydrants() {
        boolean canSell;
        lock.readLock().lock();
        canSell = !availableTesters.isEmpty();
        lock.readLock().unlock();
        return canSell;
    }

    @Override
    public boolean sellHydrants() {
        TesterTimer tester;
        lock.writeLock().lock();
        tester = (TesterTimer) availableTesters.pollFirst();
        lock.writeLock().unlock();
        if (tester == null) {
            return false;
        }
        tester.registerCallback(this);
        threadPool.execute(tester);
        return true;
    }

    //This is called from the tester to add into the availableTesters list, and make itself available for next requests.
    public void callback(Tester tester) {
        if (tester != null) {
            lock.writeLock().lock();
            availableTesters.add(tester);
            lock.writeLock().unlock();
        }
    }
}
