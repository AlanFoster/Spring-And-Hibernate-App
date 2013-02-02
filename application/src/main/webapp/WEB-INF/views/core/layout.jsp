<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><tiles:getAsString name="website.title"/> <tiles:getAsString name="website.version"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css"/>
    <script src="/resources/js/vendor/jquery-1.9.0.min.js"></script>
    <script src="/resources/js/vendor/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid wrapper">

    <div class="row-fluid">
        <div class="span2 row">
            <div class="well">
                <div><a href="/employees/add">Add Employee</a></div>
                <div><a href="/employees/search">Search Employee</a></div>
                <div><a href="/reports">Reports</a></div>
            </div>
        </div>
        <div class="row span10">
            <tiles:insertAttribute name="body"/>
        </div>

        <div style="text-align: center"><tiles:insertAttribute name="copyright"/></div>
    </div>

</div>
</body>
</html>