package me.alanfoster.services.employee.service;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SearchCriteriaLike implements ISearchCriteriaLike {
    private String fieldName;
    private String requiredValue;
    private boolean caseInsensitive;

    public SearchCriteriaLike(){
    }

    public SearchCriteriaLike(String fieldName, String requiredValue, boolean caseInsensitive) {
        this.fieldName = fieldName;
        this.requiredValue = requiredValue;
        this.caseInsensitive = caseInsensitive;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getRequiredValue() {
        return requiredValue;
    }

    @Override
    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    @Override
    public String toString() {
        return "SearchCriteriaLike{" +
                "fieldName='" + fieldName + '\'' +
                ", requiredValue='" + requiredValue + '\'' +
                ", caseInsensitive=" + caseInsensitive +
                '}';
    }
}

