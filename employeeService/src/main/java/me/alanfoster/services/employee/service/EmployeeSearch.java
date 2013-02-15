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
     * The minimum employee id.
     */
    @Range(min = 1)
    private Integer minEmployeeId;
    /**
     * The maximum employee id.
     */
    @Range(min = 1)
    private Integer maxEmployeeId;
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
     * @return the min employee id
     */
    public final Integer getMinEmployeeId() {
        return minEmployeeId;
    }

    /**
     * Mutator method.
     *
     * @param minEmployeeId the min employee id
     */
    public final void setMinEmployeeId(final Integer minEmployeeId) {
        this.minEmployeeId = minEmployeeId;
    }

    /**
     * Accessor method.
     *
     * @return the max employee id
     */
    public final Integer getMaxEmployeeId() {
        return maxEmployeeId;
    }

    /**
     * Mutator method.
     *
     * @param maxEmployeeId the max employee id
     */
    public final void setMaxEmployeeId(final Integer maxEmployeeId) {
        this.maxEmployeeId = maxEmployeeId;
    }


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
                "minEmployeeId=" + minEmployeeId +
                ", maxEmployeeId=" + maxEmployeeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", minDeskId=" + minDeskId +
                ", maxDeskId=" + maxDeskId +
                '}';
    }
}
