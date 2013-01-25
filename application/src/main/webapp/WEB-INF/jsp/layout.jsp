<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
<div style="align:center"><tiles:insertAttribute name="copyright" /></div>
</body>
</html>