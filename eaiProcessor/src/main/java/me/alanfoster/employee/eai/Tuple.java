package me.alanfoster.employee.eai;

 /**
 * Represents a basic 2-ary tuple.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @param <T1> The type of the first item
 * @param <T2> The type of the second item
 */
public class Tuple<T1, T2> {
    /**
     * The first Item.
     */
    private final T1 itemOne;
    /**
     * The second item.
     */
    private final T2 itemTwo;

    /**
     * Creates a new tuple of the required types.
     *
     * @param itemOne The first item
     * @param itemTwo The second Item
     */
    public Tuple(final T1 itemOne, final T2 itemTwo) {
        this.itemOne = itemOne;
        this.itemTwo = itemTwo;
    }

    /**
     * Get the first item.
     *
     * @return The first Item
     */
    public final T1 getItemOne() {
        return itemOne;
    }

    /**
     * Get the second item.
     *
     * @return The second Item
     */
    public final T2 getItemTwo() {
        return itemTwo;
    }
}
