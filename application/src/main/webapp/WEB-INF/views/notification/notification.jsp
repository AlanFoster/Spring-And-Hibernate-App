<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--Decide whether or not the notification was a success or error and output the correct message style --%>

<c:choose>
    <c:when test="${formResult == 'success'}">
        <div class="alert alert-success">
            This action has been completed successfully.
            ${formResultDetail}
        </div>
    </c:when>
    <c:when test="${formResult == 'error'}">
        <div class="alert alert-error">
            Unable to complete this action unsuccessfully.
            ${formResultDetail}
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert">
            Unable to complete this action unsuccessfully.
            ${formResultDetail}
        </div>
    </c:otherwise>
</c:choose>