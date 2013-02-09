<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><tiles:getAsString name="website.title"/> <tiles:getAsString name="website.version"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/ui-lightness/jquery-ui-1.10.0.custom.min.css"/>
    <link rel="stylesheet" href="/resources/css/main.css"/>
    <script type="text/javascript" src="/resources/js/vendor/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="/resources/js/vendor/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/vendor/jquery-ui-1.10.0.custom.min.js"></script>
    <script type="text/javascript" src="/resources/js/vendor/json.min.js"></script>
    <script src="/resources/js/main.js"></script>
</head>
<body>

<%-- Extract the variable for which menu item should be highlighted
     This is used later in a ternary operator on the menu items to decide the class --%>
<c:set var="highlightedPage"><tiles:getAsString name="highlightedPage"/></c:set>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="/"><tiles:getAsString name="website.brand" /></a>
            <ul class="nav">
                <li class="${highlightedPage == 'addEmployee' ? 'active' : ''}"><a href="/employees/add"><i class="icon-user"></i> Add Employee</a></li>
                <li class="${highlightedPage == 'searchEmployee' ? 'active' : ''}"><a href="/employees/search"><i class="icon-search"></i> Search</a></li>
                <li class="${highlightedPage == 'reports' ? 'active' : ''}"><a href="/reports"><i class="icon-picture"></i> Reports</a></li>
            </ul>

            <div class="pull-right">
                <ul class="nav pull-right">
                    <li class="${highlightedPage == 'help' ? 'active' : ''}"><a href="/help" title="Need Help?"><i class="icon-question-sign"></i> Help</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div id="mainContainer" class="container">
    <tiles:insertAttribute name="body"/>
</div>
<div style="text-align: center"><tiles:insertAttribute name="copyright"/></div>
</body>
</html>