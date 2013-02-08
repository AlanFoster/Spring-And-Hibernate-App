package me.alanfoster.services.employee.service;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ISearchCriteriaLike extends ISearchCriteria {
    String getRequiredValue();
    boolean isCaseInsensitive();
}
