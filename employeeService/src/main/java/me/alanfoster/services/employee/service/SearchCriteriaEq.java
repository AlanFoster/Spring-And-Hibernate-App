package me.alanfoster.services.employee.service;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SearchCriteriaEq implements ISearchCriteriaEq {
    private String fieldName;
    private Object value;

    public SearchCriteriaEq(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }


    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SearchCriteriaEq{" +
                "fieldName='" + fieldName + '\'' +
                ", value=" + value +
                '}';
    }
}
