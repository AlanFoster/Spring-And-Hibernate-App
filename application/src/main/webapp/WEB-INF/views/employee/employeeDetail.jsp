<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="input" uri="http://www.springframework.org/tags/form" %>


<%-- Output a success message if we have just saved the form --%>
<c:if test="${!empty formResult}">
    <c:choose>
        <c:when test="${formResult == 'success'}">
            <div class="alert alert-success">
                This employee has been updated successfully.
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-error">
                Unable to complete this action succesfully.
            </div>
        </c:otherwise>
    </c:choose>
</c:if>

<c:choose>
    <c:when test="${empty employee}">
        Sorry. That employee does not exist in the system.
    </c:when>
    <c:otherwise>
        <form:form modelAttribute="employee" method="post" action="/edit/${employee.id}" cssClass="form-horizontal">
            <div class="control-group">
                <form:label path="id" cssClass="control-label">Employee Id</form:label>
                <div class="controls">
                    <form:input path="id" cssClass="uneditable-input"/>
                </div>
            </div>
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
                    <button type="submit" name="save" class="btn btn-primary">Save</button>
                    <%-- Note :: This button activates some javascript which is bound to
                        automatically through the id 'confirmDelete'
                         - this is so users without javascript still make use of the system --%>
                    <a href="/delete/${employee.id}" class="btn btn-danger" id="confirmDelete">Delete</a>
                </div>
            </div>
        </form:form>
    </c:otherwise>
</c:choose>