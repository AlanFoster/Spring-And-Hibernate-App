<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>


<c:choose>
    <c:when test="${empty employee}">
        Sorry. That employee does not exist in the system.
    </c:when>
    <c:otherwise>
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
                        <form:options items="${jobTitles}"/>
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
    </c:otherwise>
</c:choose>