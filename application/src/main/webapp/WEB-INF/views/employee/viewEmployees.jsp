<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<%--
    This could be moved to the tiles.xml definitions
    But I found development to be faster if it's here as there wasn't a need for redeploys
--%>
<link rel="stylesheet" href="/resources/css/bootstrap/DT_bootstrap.css"/>
<script type="text/javascript" src="/resources/js/vendor/jquery.dataTables.js"></script>
<script type="text/javascript" src="/resources/js/vendor/DT_bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/vendor/DT_bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/search.js"></script>

<div>
    <div class="tabbable">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#basicSearch" data-toggle="tab">Basic Search</a></li>
            <li><a href="#advancedSearch" data-toggle="tab">Advanced Search</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane" id="basicSearch">
            </div>
            <div class="tab-pane active" id="advancedSearch">
                <form id="advancedForm" method="post" class="form-horizontal">
                    <div class="control-group">
                        <span class="control-label"
                              title="You can also specify ranges using 'min-max', IE '1-5' will bring back all records between 1 and 5"><i
                                class="icon-question-sign"></i> Employee Id</span>
                        <div class="controls">
                            <input name="employeeId" placeHolder="Employee Id" />
                        </div>
                    </div>
                    <div class="control-group">
                        <span class="control-label">First Name</span>
                        <div class="controls">
                            <input type="text" name="firstName" placeHolder="First Name" /> <span class="help-inline"><em>Remember you can use '%' as a wild card!</em></span>
                        </div>
                    </div>
                    <div class="control-group">
                        <span class="control-label">Second Name</span>
                        <div class="controls">
                            <input name="secondName" placeHolder="Second Name" />
                        </div>
                    </div>
                    <div class="control-group">
                        <span class="control-label"
                              title="You can also specify ranges using 'min-max', IE '1-5' will bring back all records between 1 and 5"><i
                                class="icon-question-sign"></i> Desk Id</span>
                        <div class="controls">
                            <input name="deskId" placeHolder="Desk Id" />
                        </div>
                    </div>
                    <div class="control-group">
                        <span class="control-label"
                                    title="When 'OFF' this will add an implicit wildcard before and after search terms"><i
                                class="icon-question-sign"></i> Exact Match</span>
                        <div class="controls">
                            <div id="exactMatchSwitch" class="switch">
                                <input type="checkbox">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${empty employees}">
        <div class="alert">Sorry. There are no employees which matched your requirement</div>
    </c:when>
    <c:otherwise>
        <table id="searchResults" class="table table-striped table-hover hidden">
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