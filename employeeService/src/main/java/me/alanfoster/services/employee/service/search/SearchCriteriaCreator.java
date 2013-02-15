package me.alanfoster.services.employee.service.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple search criteria creator. It provides a nice simple DSL in order
 * to produce criterias which can be passed to DAOs for searching.
 * <br />
 * Note:: All operations which are passed null or empty as a string will NOT be added
 * to the internally tracked list of search criteria objects.
 * <br />
 * When you have finished chaining this object to create the criteria, you can retrieve
 * the completed search criterias with getSearchCriterias();
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.services.employee.service.IEmployeeService
 */
public class SearchCriteriaCreator {
    /**
     * The private list of search criteria which gets added to.
     */
    private List<ISearchCriteria> searchCriterias;

    /**
     * Create a new instance of the SearchCriteriaCreator.
     */
    public SearchCriteriaCreator() {
        this.searchCriterias = new ArrayList<ISearchCriteria>();
    }

    /**
     * Adds a case <strong>sensitive</strong> requirement to the search.
     *
     * @param fieldName The name of the field represented in the object
     * @param value     The value which the field should be (Including % for wild card)
     * @return The SearchCriteriaCreator object in order to allow chaining
     */
    public final SearchCriteriaCreator like(final String fieldName, final String value) {
        return addLike(fieldName, value, true);
    }

    /**
     * Adds a case <strong>insensitive</strong> requirement to the search.
     *
     * @param fieldName The name of the field represented in the object
     * @param value     The value which the field should be (Including % for wild card)
     * @return The SearchCriteriaCreator object in order to allow chaining
     */
    public final  SearchCriteriaCreator ilike(final String fieldName, final String value) {
        return addLike(fieldName, value, true);
    }

    /**
     * Adds a wild card requirement to the search
     * Used internally by this class. You should use the ilike and like operations if you wish
     * to make use of this search requirement
     *
     * @param fieldName       The name of the field represented in the object
     * @param value           The value which the field should be (Including % for wild card)
     * @param caseInsenstiive whether the search should be caseinsenstiive or not
     * @return The SearchCriteriaCreator object in order to allow chaining
     */
    private SearchCriteriaCreator addLike(final String fieldName, final String value, final boolean caseInsenstiive) {
        if (value != null && !value.isEmpty()) {
            searchCriterias.add(new SearchCriteriaLike(fieldName, value, caseInsenstiive));
        }
        return this;
    }

    /**
     * Adds a search criteria for the field name value to be between the min and max value given
     * Note, if the max value is null then this class assumes that the maxValue is equal to
     * the min value.
     *
     * @param fieldName The name of the field represented in the object
     * @param minValue  The minimum value
     * @param maxValue  The maximum value. If the maximum value is null then this will equal the min value
     * @return The SearchCriteriaCreator object in order to allow chaining
     */
    public final SearchCriteriaCreator between(final String fieldName, final Integer minValue, final Integer maxValue) {
        if (minValue != null) {
            searchCriterias.add(new SearchCriteriaBetween(fieldName, minValue, maxValue == null ? minValue : maxValue));
        }
        return this;
    }

    /**
     * Adds a search criteria for the field value to be exactly that given.
     *
     * @param fieldName The name of the field represented in the object
     * @param value     The value the field should be equal to
     * @return The SearchCriteriaCreator object in order to allow chaining
     */
    public final SearchCriteriaCreator eq(final String fieldName, final Object value) {
        if (value != null) {
            searchCriterias.add(new SearchCriteriaEq(fieldName, value));
        }
        return this;
    }


    /**
     * When you have finished chaining this object to create the criteria, you can retrieve
     * the completed search criterias with this operation
     *
     * @return The list of search criteria
     */
    public final List<ISearchCriteria> getSearchCriterias() {
        return this.searchCriterias;
    }
}
