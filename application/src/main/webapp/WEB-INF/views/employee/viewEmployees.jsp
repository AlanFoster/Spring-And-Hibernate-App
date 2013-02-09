<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<h2><tiles:getAsString name="page.title"/></h2>

<div class="well">

    <form:form modelAttribute="employeeSearchCriteria" method="post" cssClass="form-horizontal">
        <div class="control-group">
            <form:label path="firstName" cssClass="control-label">First Name</form:label>
            <div class="controls">
                <form:input path="firstName"/> <form:errors path="firstName" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label path="secondName" cssClass="control-label">Second Name</form:label>
            <div class="controls">
                <form:input path="secondName"/> <form:errors path="secondName" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label path="minDeskId" cssClass="control-label">Min Desk Id</form:label>
            <div class="controls">
                <form:input path="minDeskId"/> <form:errors path="minDeskId" cssClass="help-inline"/>
            </div>
        </div>
        <div class="control-group">
            <form:label path="maxDeskId" cssClass="control-label">Max Desk Id</form:label>
            <div class="controls">
                <form:input path="maxDeskId"/> <form:errors path="maxDeskId" cssClass="help-inline"/>
            </div>
        </div>

        <button type="submit" name="search" class="btn btn-primary">Search</button>
    </form:form>
</div>

<c:choose>
    <c:when test="${empty employees}">
        <div class="alert">Sorry. There are no employees which matched your requirement</div>
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
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.secondName}</td>
                    <td>${employee.job.jobTitle}</td>
                    <td>${employee.deskId}</td>
                    <td><a href="/employees/edit/${employee.id}" title="Edit this employee">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>