package me.alanfoster.services.employee.service.search;

/**
 * Requires for a field to be between the min and max values given
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ISearchCriteriaBetween extends ISearchCriteria {
    /**
     * Accessor Method.
     * @return the min value that the field can be
     */
    Integer getMin();

    /**
     * Accessor Method.
     * @return The max value that the field can be
     */
    Integer getMax();
}
