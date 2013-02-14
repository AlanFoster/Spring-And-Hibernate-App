package me.alanfoster.services.employee.service.search;

/**
 * Concrete Implementation of ISearchCriteriaLike.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SearchCriteriaLike implements ISearchCriteriaLike {
    /**
     * The field name that should match the criteria.
     */
    private String fieldName;
    /**
     * The required value that may contain '%' for wild card searches
     */
    private String requiredValue;
    /**
     * Whether the search should be case insensitive or not
     */
    private boolean caseInsensitive;

    /**
     * Constructs a new instance of SearchCriteriaLike
     *
     * @param fieldName       The field name that should match the criteria.
     * @param requiredValue   The required value that may contain '%' for wild card searches
     * @param caseInsensitive Whether the search should be case insensitive or not
     */
    public SearchCriteriaLike(final String fieldName, final String requiredValue, final boolean caseInsensitive) {
        this.fieldName = fieldName;
        this.requiredValue = requiredValue;
        this.caseInsensitive = caseInsensitive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getFieldName() {
        return fieldName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getRequiredValue() {
        return requiredValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    @Override
    public final String toString() {
        return "SearchCriteriaLike{" +
                "fieldName='" + fieldName + '\'' +
                ", requiredValue='" + requiredValue + '\'' +
                ", caseInsensitive=" + caseInsensitive +
                '}';
    }
}

