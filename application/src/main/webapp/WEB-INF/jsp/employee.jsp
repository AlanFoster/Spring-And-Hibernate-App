<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Add Employee</h1>

<form:form modelAttribute="employee" method="post" action="addEmployee.html" cssClass="form-horizontal">
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
        <form:label path="jobTitle" cssClass="control-label">Job Title</form:label>
        <div class="controls">
            <form:select path="jobTitle">
                <form:option value="" label="Select One"/>
                <form:options items="${jobTitles}" />
            </form:select>
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


<h1>Existing Employees</h1>

<c:choose>
    <c:when test="${empty employees}">
        Sorry. There are currently no employees in the system
    </c:when>
    <c:otherwise>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Job Title</th>
                <th>Desk ID</th>
            </tr>
            </thead>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.secondName}</td>
                    <td>${employee.jobTitle}</td>
                    <td>${employee.deskId}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>