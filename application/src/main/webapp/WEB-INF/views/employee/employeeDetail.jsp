<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="/resources/js/employeeDetail.js"></script>

<%-- Modal for deleting an employee which will show when the delete button is clicked --%>
<div id="deleteEmployeeModel" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>Delete Employee</h3>
    </div>
    <div class="modal-body">
        <p>You are about to delete the following employee, this procedure is irreversible.</p>
        <p>Do you want to proceed?</p>
    </div>
    <div class="modal-footer">
        <button class="btn btn-danger" id="confirmDelete">Yes</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
    </div>
</div>


<%-- This one JSP page represents the 'employee detail' object detail page.
     This template should be reused as appropiate for the CRUD operations --%>
<h2><tiles:getAsString name="page.title"/></h2>

<c:choose>
    <c:when test="${empty employee}">
        <div class="alert">Sorry. That employee does not exist in the system.</div>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="span12">
                <form:form modelAttribute="employee" method="post" cssClass="form-horizontal">
                    <%-- If the employee has a Job Id, show it read only mode --%>
                    <c:if test="${!empty employee.id}">
                        <div class="control-group">
                            <form:label path="id" cssClass="control-label">Employee Id</form:label>
                            <div class="controls">
                                <form:input path="id" cssClass="uneditable-input"/>
                            </div>
                        </div>
                    </c:if>
                    <div class="control-group">
                        <form:label path="firstName" cssClass="control-label">First Name</form:label>
                        <div class="controls">
                            <form:input path="firstName" placeholder="First Name"/> <form:errors path="firstName"
                                                                                                 cssClass="help-inline"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label path="secondName" cssClass="control-label">Second Name</form:label>
                        <div class="controls">
                            <form:input path="secondName" placeHolder="Second Name"/> <form:errors path="secondName"
                                                                                                   cssClass="help-inline"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label path="job" cssClass="control-label">Job Title</form:label>
                        <div class="controls">
                            <form:select path="job">
                                <form:option value="-1" label="Select One"/>
                                <form:options items="${jobs}" itemLabel="jobTitle" itemValue="jobId"/>
                            </form:select>
                            <form:errors path="job" cssClass="help-inline"/>
                            <form:errors path="job.jobId" cssClass="help-inline"/>
                            <form:errors path="job.jobTitle" cssClass="help-inline"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label path="deskId" cssClass="control-label">Desk Id</form:label>
                        <div class="controls">
                            <form:input path="deskId" placeholder="Desk Id"/> <form:errors path="deskId"
                                                                                           cssClass="help-inline"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                                <%-- Show the 'Add Employee' button if this is a new employee, otherwise show the update/delete buttons --%>
                            <c:choose>
                                <c:when test="${empty employee.id}">
                                    <button type="submit" name="save" class="btn btn-primary">Add Employee</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" name="save" class="btn btn-primary">Save</button>
                                    <%-- Note :: This button activates some javascript which is bound to
                                        automatically through the id 'confirmDelete'
                                         - this is so users without javascript still make use of the system --%>
                                    <a href="/employees/delete/${employee.id}" class="btn btn-danger"
                                       id="confirmEmployeeDelete">Delete</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        </div>
    </c:otherwise>
</c:choose>