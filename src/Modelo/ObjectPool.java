package Modelo;

import java.util.Calendar;
import java.util.Stack;
import java.util.UUID;


public class ObjectPool {
    private final int minInstances;
    private final int maxInstances;
    private final int waitTime;
    private final Stack<PooledObjectStatus> fullStack = new Stack<>();
    private final Stack<PooledObjectStatus> useStack = new Stack<>();
    private final Stack<PooledObjectStatus> freeStack = new Stack<>();

    public ObjectPool(int minInstances, int maxInstances, int waitTime) {
        System.out.println("=========== STARTING ============");
        this.minInstances = minInstances;
        this.maxInstances = maxInstances;
        this.waitTime = waitTime;
        initPool();
        System.out.println("=========== FINISH ============");
    }

    private void initPool() {
        for (int c = fullStack.size(); c < minInstances; c++) {
            PooledObjectStatus createNewPooledObject = createNewPooledObject();
            freeStack.push(createNewPooledObject);
        }
    }

    private static class PooledObjectStatus {
        boolean used;
        UUID uuid;
        Bolita pooledObject;

        public PooledObjectStatus(Bolita pooledObject) {
            this.used = false;
            this.uuid = UUID.randomUUID();
            this.pooledObject = pooledObject;
        }
    }

    private Bolita getInternalObject() throws PoolException {
        synchronized (freeStack) {
            if (!freeStack.isEmpty()) {
                PooledObjectStatus first = this.freeStack.pop();
                first.used = true;
                System.out.println("Provisioning1 Object > "    + first.uuid.toString());
                useStack.push(first);
                return first.pooledObject;
            }
            synchronized (fullStack) {
                if (fullStack.size() < maxInstances) {
                    PooledObjectStatus returnObject = createNewPooledObject();
                    returnObject.used = true;
                    System.out.println("Provisioning2 Object > "    + returnObject.uuid.toString());
                    useStack.push(returnObject);
                    return returnObject.pooledObject;
                }
                else {
                    return null;
                }
            }
        }
    }

    public Bolita getObject() throws PoolException {
        Bolita internalObject = getInternalObject();
        if (internalObject != null) {
            return internalObject;
        }
        return waitForResource();
    }

    private Bolita waitForResource() throws PoolException {
        Calendar future = Calendar.getInstance();
        future.add(Calendar.MILLISECOND, waitTime);

        do {
            PooledObjectStatus returnObject = null;
            synchronized (freeStack) {
                if (!freeStack.isEmpty() && !freeStack.peek().used) {
                    returnObject = freeStack.pop();
                    returnObject.used = true;
                    useStack.push(returnObject);
                    System.out.println("Provisioning3 Object > "    + returnObject.uuid.toString());
                    return returnObject.pooledObject;
                }
            }

            if (returnObject == null || returnObject.used) {
                if (waitTime != 0 && System.currentTimeMillis()
                        >= future.getTimeInMillis()) {
                    throw new PoolException("Tiempo de espera agotado");
                }
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {
                }
            }
        }

        while (true);
    }

    private PooledObjectStatus createNewPooledObject() {
        Bolita newObject = new Bolita(); //Cracion de Bolita basica
        PooledObjectStatus pooled = new PooledObjectStatus(newObject);
        fullStack.push(pooled);
        System.out.println("New PoolableObject{UUID=" + pooled.uuid.toString() + ", poolSize=" + fullStack.size() + "}");
        return pooled;
    }

    public void releaceObject(Bolita pooledObject) {
        for (PooledObjectStatus item : this.fullStack) {
            if (item.pooledObject == pooledObject) {
                if (fullStack.size() < maxInstances) {
                    freeStack.push(item);
                    useStack.remove(item);
                    item.used = false;
                    System.out.println("Object returned > "
                            + item.uuid.toString());
                    return;
                }
                else {
                    System.out.println("Object Invalidate ==> "+ item.uuid.toString());
                    fullStack.remove(item);
                    useStack.remove(item);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "AbstractObjectPool ==> currentSize > '"+fullStack.size()+"', "
                + "free > '"+freeStack.size()+"', used > '"+useStack.size()+"'";
    }
}