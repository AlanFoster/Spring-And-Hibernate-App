<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><tiles:getAsString name="website.title"/> <tiles:getAsString name="website.version"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/ui-lightness/jquery-ui-1.10.0.custom.min.css"/>
    <link rel="stylesheet" href="/resources/css/main.css"/>
    <script src="/resources/js/vendor/jquery-1.9.0.min.js"></script>
    <script src="/resources/js/vendor/bootstrap.min.js"></script>
    <script src="/resources/js/vendor/jquery-ui-1.10.0.custom.min.js"></script>
    <script src="/resources/js/main.js"></script>
</head>
<body>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="/">EMS</a>
            <ul class="nav">
                <li><a href="/employees/add"><i class="icon-user"></i> Add Employee</a></li>
                <li><a href="/employees/search"><i class="icon-search"></i> Search</a></li>
                <li><a href="/reports"><i class="icon-picture"></i> Reports</a></li>
            </ul>

            <div class="pull-right">
                <ul class="nav pull-right">
                    <li><a href="/#help" title="Need Help?"><i class="icon-question-sign"></i> Help</a></li>
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