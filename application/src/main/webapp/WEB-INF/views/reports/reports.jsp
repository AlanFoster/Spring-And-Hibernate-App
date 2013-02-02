<script src="/resources/js/vendor/d3.v3.min.js" type="text/javascript"></script>
<script src="/resources/js/reports.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/reports.css">

<div class="tabbable">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#jobCategories" data-toggle="tab">Job Categories</a></li>
        <li><a href="#totalEmployees" data-toggle="tab">Total Employees</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="jobCategories">
            <div id="jobCategoriesContainer" style="text-align:center">
                <div id="jobCategoriesLoadingImage"><img src="/resources/img/ajax-loader.gif" alt="Loading Data..."/>
                </div>
                <div id="jobCategoriesReport"></div>
            </div>
        </div>
        <div class="tab-pane" id="totalEmployees">
            <p>Total Employees</p>
        </div>
    </div>
</div>