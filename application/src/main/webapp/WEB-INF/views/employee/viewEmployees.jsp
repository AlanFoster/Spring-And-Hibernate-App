<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<h1>Search Employees</h1>

<c:choose>
    <c:when test="${empty employees}">
        Sorry. There are no employees which matched your requirement.
    </c:when>
    <c:otherwise>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Desk ID</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.secondName}</td>
                    <td>${employee.deskId}</td>
                    <td><a href="/employees/edit/${employee.id}" title="Edit this employee">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>