package me.alanfoster.services.employee.service.search;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ISearchCriteriaBetween extends ISearchCriteria {
    Integer getMin();
    Integer getMax();
}
