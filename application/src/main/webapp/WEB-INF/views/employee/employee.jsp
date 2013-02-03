<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Add Employee</h1>

<form:form modelAttribute="employee" method="post" action="/employees/add" cssClass="form-horizontal">
    <div class="control-group">
        <form:label path="firstName" cssClass="control-label">First Name</form:label>
        <div class="controls">
            <form:input path="firstName"/> <form:errors path="firstName" cssClass="help-inline" />
        </div>
    </div>
    <div class="control-group">
        <form:label path="secondName" cssClass="control-label">Second Name</form:label>
        <div class="controls">
            <form:input path="secondName"/> <form:errors path="secondName" cssClass="help-inline" />
        </div>
    </div>
    <div class="control-group">
        <form:label path="job" cssClass="control-label">Job Title</form:label>
        <div class="controls">
            <form:select path="job">
                <form:option value="-1" label="Select One"/>
                <form:options items="${jobs}" itemLabel="jobTitle" itemValue="jobId" />
            </form:select>
            <form:errors path="job" cssClass="help-inline" />
            <form:errors path="job.jobId" cssClass="help-inline" />
            <form:errors path="job.jobTitle" cssClass="help-inline" />
        </div>
    </div>
    <div class="control-group">
        <form:label path="deskId" cssClass="control-label">Desk Id</form:label>
        <div class="controls">
            <form:input path="deskId"/> <form:errors path="deskId" cssClass="help-inline" />
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <button type="submit" class="btn">Add Employee</button>
        </div>
    </div>
</form:form>