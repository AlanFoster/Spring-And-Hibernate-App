<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Add Employee</h1>

<form:form modelAttribute="employee" method="post" action="/employees/add" cssClass="form-horizontal">
    <div class="control-group">
        <form:label path="firstName" cssClass="control-label">First Name</form:label>
        <div class="controls">
            <form:input path="firstName"/>
        </div>
    </div>
    <div class="control-group">
        <form:label path="secondName" cssClass="control-label">Second Name</form:label>
        <div class="controls">
            <form:input path="secondName"/>
        </div>
    </div>
    <div class="control-group">
        <form:label path="job" cssClass="control-label">Job Title</form:label>
        <div class="controls">
            <form:select path="job" items="${jobs}" itemValue="jobId" itemLabel="jobTitle"/>
        </div>
    </div>
    <div class="control-group">
        <form:label path="deskId" cssClass="control-label">Desk Id</form:label>
        <div class="controls">
            <form:input path="deskId"/>
        </div>

    </div>
    <div class="control-group">
        <div class="controls">
            <button type="submit" class="btn">Add Employee</button>
        </div>
    </div>
</form:form>