package me.alanfoster.services.employee.service.search;

/**
 * Concrete implementation of ISearchCriteriaEq.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SearchCriteriaEq implements ISearchCriteriaEq {
    /**
     * The field name that should match the criteria.
     */
    private String fieldName;
    /**
     * The expected value
     */
    private Object value;

    /**
     * Constructs a new SearchCriteriaEq instance with the given values
     *
     * @param fieldName The field name that should match the criteria.
     * @param value     The expected value
     */
    public SearchCriteriaEq(final String fieldName, final Object value) {
        this.fieldName = fieldName;
        this.value = value;
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
    public final Object getValue() {
        return value;
    }

    @Override
    public final String toString() {
        return "SearchCriteriaEq{" +
                "fieldName='" + fieldName + '\'' +
                ", value=" + value +
                '}';
    }
}
