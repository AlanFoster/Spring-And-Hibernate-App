package me.alanfoster.services.employee.service.search;

/**
 * Requires for a field to be exactly the given value.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ISearchCriteriaEq extends ISearchCriteria {
    /**
     * Accessor Method.
     *
     * @return The expected value
     */
    Object getValue();
}
