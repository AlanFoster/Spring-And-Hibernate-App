package me.alanfoster.services.employee.service.search;

/**
 * Requires for the field to be like the required string.
 * IE, this should support the character '%' for wildcard
 * full text support.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ISearchCriteriaLike extends ISearchCriteria {
    /**
     * Accessor Method.
     * @return The string pattern that it should be like
     */
    String getRequiredValue();

    /**
     * Accesor Method.
     * @return True if case sensitive, false otherwise
     */
    boolean isCaseInsensitive();
}
