package me.alanfoster.services.employee.service.search;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SearchCriteriaBetween implements ISearchCriteriaBetween {
    /**
     * the min value that the field can be.
     */
    private Integer min;
    /**
     * the max value that the field can be.
     */
    private Integer max;
    /**
     * The field name that should match the criteria.
     */
    private String fieldName;

    public SearchCriteriaBetween(String fieldName, Integer min, Integer max) {
        this.min = min;
        this.max = max;
        this.fieldName = fieldName;
    }

    @Override
    public Integer getMin() {
        return min;
    }

    @Override
    public Integer getMax() {
        return max;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toString() {
        return "SearchCriteriaBetween{" +
                "min=" + min +
                ", max=" + max +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
