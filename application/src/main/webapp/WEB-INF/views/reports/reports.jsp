<script src="/resources/js/vendor/d3.v3.min.js" type="text/javascript"></script>
<script src="/resources/js/reports.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/reports.css">

<div class="tabbable tabs-left">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#jobCategories" data-toggle="tab">Job Categories</a></li>
        <li><a href="#totalEmployees" data-toggle="tab">Total Employees</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="jobCategories">
            <%-- Shown when no data was found --%>
            <div id="jobCategoriesEmpty" class="alert">Sorry, there was not enough information to generate a report. Please add some employees to the system.</div>
            <div id="jobCategoriesContainer" style="text-align:center">
                <div id="jobCategoriesLoadingImage"><img src="/resources/img/ajax-loader.gif" alt="Loading Data..."/>
                </div>
                <div id="jobCategoriesReport"></div>
            </div>
        </div>
        <div class="tab-pane" id="totalEmployees">
            <p>TODO if I have time.</p>
        </div>
    </div>
</div>