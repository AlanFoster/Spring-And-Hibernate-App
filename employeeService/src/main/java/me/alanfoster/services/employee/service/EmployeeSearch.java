package me.alanfoster.services.employee.service;

import org.hibernate.validator.constraints.Range;

/**
 * Represents a generic object which is used to for generic
 * searching in a dataset.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class EmployeeSearch {
    /**
     * The unique employee id.
     */
    @Range(min = 1)
    private Integer id;
    /**
     * The employee's first name.
     */
    private String firstName;
    /**
     * The employee's second name.
     */
    private String secondName;
    /**
     * The minimum desk id value.
     */
    @Range(min = 1)
    private Integer minDeskId;
    /**
     * The maximum desk id value.
     */
    @Range(min = 1)
    private Integer maxDeskId;


    /**
     * Accessor method.
     *
     * @return the first name
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Mutator method.
     *
     * @param firstName the first name
     */
    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Accessor method.
     *
     * @return the first name
     */
    public final String getSecondName() {
        return secondName;
    }

    /**
     * Mutator method.
     *
     * @param secondName the second name
     */
    public final void setSecondName(final String secondName) {
        this.secondName = secondName;
    }


    /**
     * Accessor method.
     *
     * @return the unique employee id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * Mutator method.
     *
     * @param id the unique employee id
     */
    public final void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Accessor method.
     *
     * @return The min desk id
     */
    public final Integer getMinDeskId() {
        return minDeskId;
    }

    /**
     * Mutator method.
     *
     * @param minDeskId THe min desk id
     */
    public final void setMinDeskId(final Integer minDeskId) {
        this.minDeskId = minDeskId;
    }

    /**
     * Accesor method.
     *
     * @return the max desk id
     */
    public final Integer getMaxDeskId() {
        return maxDeskId;
    }

    /**
     * Mutator method.
     *
     * @param maxDeskId The max desk id
     */
    public final void setMaxDeskId(final Integer maxDeskId) {
        this.maxDeskId = maxDeskId;
    }

    @Override
    public final String toString() {
        // Auto generated
        return "EmployeeSearch{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", minDeskId=" + minDeskId +
                ", maxDeskId=" + maxDeskId +
                '}';
    }
}
