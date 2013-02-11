package me.alanfoster.services.employee.service.search;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SearchCriteriaBetween implements ISearchCriteriaBetween {
    private Integer min;
    private Integer max;
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
