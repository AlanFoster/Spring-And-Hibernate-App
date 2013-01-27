<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%--
<html>
<head>
    <title><tiles:getAsString name="page.title"/></title>
</head>
<body>

<div><tiles:getAsString name="website.title"/> <em><tiles:getAsString name="website.version"/></em></div>
<h1>Welcome!!!!</h1>
<div style="border: 1px solid red">
    <tiles:insertAttribute name="body" />
</div>
<div style="align:center"></div>
</body>
</html>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><?= title ?></title>
    <link rel='stylesheet' href='resources/css/style.css' />
    <link href="resources//css/bootstrap.min.css" rel="stylesheet" />
    <script src="resources/js/vendor/jquery-1.9.0.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid wrapper">
    <div class="row">
        <div class="span5">
            <h1><?= app.locals.websiteName ?></h1>
            <p class="muted"><?= app.locals.motto ?></p>
        </div>
    </div>

    <div class="navbar">
        <div class="navbar-inner">
            <ul class="nav">
                <li class="<?= title === 'Home' ? 'active' : '' ?>"><a href="/">a</a></li>
                <li class="<?= title === 'Products' ? 'active' : '' ?>"><a href="/viewAllProducts">b</a></li>
            </ul>
        </div>
    </div>

    <div class="row-fluid">
        <div class="span2 row">
            <div class="well ">
                User details
            </div>
        </div>
        <div class="row span10">
            <div class="tabbable">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">Section 1</a></li>
                    <li><a href="#tab2" data-toggle="tab">Section 2</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab1">
                        <p>I'm in Section 1.</p>
                    </div>
                    <div class="tab-pane" id="tab2">
                        <p>Howdy, I'm in Section 2.</p>
                    </div>
                </div>
            </div>
            <tiles:insertAttribute name="body" />

    </div>

    <div style="text-align: center"><tiles:insertAttribute name="copyright" /></div>
</div>
</body>
</html>