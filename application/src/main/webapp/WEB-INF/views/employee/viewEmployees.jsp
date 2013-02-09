<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="input" uri="http://www.springframework.org/tags/form" %>


<div>
    <div class="tabbable">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#jobCategories" data-toggle="tab">Basic Search</a></li>
            <li><a href="#totalEmployees" data-toggle="tab">Advanced Search</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="jobCategories">
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
                            <form:input path="secondName"/> <span class="help-inline"><em>Remember you can use '%' as a wild card!</em></span> <form:errors path="secondName" cssClass="help-inline"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">

                            <button type="submit" name="search" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="tab-pane" id="totalEmployees">

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

                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" name="search" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>


</div>

<c:choose>
    <c:when test="${empty employees}">
        <div class="alert">Sorry. There are no employees which matched your requirement</div>
    </c:when>
    <c:otherwise>
        <table class="table table-striped table-hover">
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