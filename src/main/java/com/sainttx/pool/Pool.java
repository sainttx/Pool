package com.sainttx.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Matthew on 01/11/2014.
 */
public class Pool<T> {

    /*
     * A map which contains all the values and their pool amounts
     */
    private Map<T, Integer> pooling = new HashMap<T, Integer>();

    /**
     * A random used for picking a winner
     */
    private Random random = new Random();

    /**
     * Adds an Object to the Pool
     *
     * @param add    The Object to add
     * @param amount The amount of tickets the Object has in the Pool
     */
    public void add(T add, Integer amount) {
        pooling.put(add, amount);
    }

    /**
     * Removes an Object from the Pool
     *
     * @param remove The Object to remove
     */
    public void remove(T remove) {
        pooling.remove(remove);
    }

    /**
     * Chooses an Object from the Pool
     *
     * @return The winner of the Pool
     */
    public T pick() {
        int size = random.nextInt(this.getSize());

        for (Map.Entry<T, Integer> poolEntry : pooling.entrySet()) {
            if ((size -= poolEntry.getValue()) <= 0) {
                return poolEntry.getKey();
            }
        }

        return pooling.keySet().iterator().next();
    }
    
    /**
     * Returns the total amount of tickets inside the Pool
     *
     * @return
     */
    public int getSize() {
        int ret = 0;

        for (Integer amount : pooling.values()) {
            ret += amount;
        }

        return ret;
    }

    @Override
    public int hashCode() {
        return 31 * pooling.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pool)) {
            return false;
        }

        Pool<?> pool = (Pool) o;
        return this.pooling.equals(pool.pooling);
    }
}
