<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Add Employee</h1>

<form:form modelAttribute="employee" method="post" action="addEmployee.html">
    <div><form:label path="firstName">First Name</form:label><form:input path="firstName" /></div>
    <div><form:label path="secondName">Second Name</form:label><form:input path="secondName" /></div>
    <div><form:label path="jobTitle">Job Title</form:label><form:input path="jobTitle" /></div>
    <div><form:label path="deskId">Desk Id</form:label><form:input path="deskId" /></div>
    <div><input type="submit" value="Add Employee" /></div>
</form:form>

<h1>Existing Employees</h1>

<c:choose>
    <c:when test="${empty employees}">
        Sorry. There are currently no employees in the system
    </c:when>
    <c:otherwise>
        <c:forEach items="${employees}" var="employee">
            <div>Name : ${employee.firstName}</div>
            <div>Job Title : ${employee.jobTitle}</div>
        </c:forEach>
    </c:otherwise>
</c:choose>