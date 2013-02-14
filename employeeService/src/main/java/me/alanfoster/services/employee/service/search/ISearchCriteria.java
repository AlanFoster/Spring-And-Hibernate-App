package me.alanfoster.services.employee.service.search;

/**
 * The base interface for Search Criteria which will be given
 * to the DAO to perform actions on appropiately
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ISearchCriteria {
    /**
     * Accesosr method.
     * @return The field name that should match the criteria
     */
    String getFieldName();
}
