<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="input" uri="http://www.springframework.org/tags/form" %>


<link rel="stylesheet" href="/resources/css/bootstrap/DT_bootstrap.css"/>
<script type="text/javascript" src="/resources/js/vendor/jquery.dataTables.js"></script>
<script type="text/javascript" src="/resources/js/vendor/DT_bootstrap.js"></script>

<script>
    $(function () {
        $('#searchResults').dataTable({
            "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_"
            },
            // Disable sorting for the edit column, as it doesn't make sense
            "aoColumnDefs" : [
                { "bSortable" : false, 'aTargets' : ["edit"]}
            ]
        });
    });
</script>

<div>
    <div class="tabbable">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#basicSearch" data-toggle="tab">Basic Search</a></li>
            <li><a href="#advancedSearch" data-toggle="tab">Advanced Search</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="basicSearch">
            </div>
            <div class="tab-pane" id="advancedSearch">

                <form:form id="advancedForm" modelAttribute="employeeSearchCriteria" method="post"
                           cssClass="form-horizontal">
                    <div class="control-group">
                        <form:label path="firstName" cssClass="control-label">First Name</form:label>
                        <div class="controls">
                            <form:input path="firstName"/> <form:errors path="firstName" cssClass="help-inline"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label path="secondName" cssClass="control-label">Second Name</form:label>
                        <div class="controls">
                            <form:input path="secondName"/> <span class="help-inline"><em>Remember you can use '%' as a
                            wild card!</em></span> <form:errors path="secondName" cssClass="help-inline"/>
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
        <table id="searchResults" class="table table-striped table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Job Title</th>
                <th>Desk ID</th>
                <th class="edit"></th>
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