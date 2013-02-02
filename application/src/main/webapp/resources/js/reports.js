/**
 * EmployeeApp should be the namespace for all javascript to
 * register itself with in this basic Employee Application
 */
var EmployeeApp;

(function (EmployeeApp) {
    EmployeeApp.getJobCategories = function (callback) {
        // Mimic being slow (Obviously you wouldn't do this in production!)
        setTimeout(function () {
            $.getJSON("/reports/jobs/titleCounts", {}, callback);
        }, 200);
    }

})(EmployeeApp || (EmployeeApp = {}));

(function (EmployeeApp) {
    EmployeeApp.createPieChart = function (selector, options, data) {
        var width = options.width,
            height = options.height,
            radius = options.radius,
            colors = options.color,
            dataName = options.dataName,
            labelName = options.labelName;

        var arc = d3.svg.arc()
            .outerRadius(radius - 10)
            .innerRadius(0)

        // Create the pie chart from the raw data value
        var pie = d3.layout.pie()
            .value(function (data) {
                return data[dataName];
            });

        // Select the node with the selector given and append the svg element to draw the pie chart in
        var svg = d3.select(selector).append("svg:svg")
            .attr("width", width)
            .attr("height", height)
            .append("svg:g")
            .attr("transform", "translate(" + radius + "," + radius + ")")

        // Create the pie chart with the correct data passed in
        // And add a class 'arc' for each arc for styling with .arc { ... }
        var arcs = svg.selectAll(".arc")
            .data(pie(data))
            .enter().append("svg:g")
            .attr("class", "arc");

        // Append a simple tooltip to each arc
        arcs.attr("title", function (d) {
                return d.data[labelName] + " : " + d.data[dataName];
            });

        // Fill in each arc with the correct color
        arcs.append("svg:path")
            .attr("fill", function (data, index) {
                return colors(index);
            })
            .attr("d", arc);

        // Append a label to the center of all arcs
        arcs.append("svg:text")
            .attr("transform", function (d) {
                return "translate(" + arc.centroid(d) + ")";
            })
            .attr("text-anchor", "middle")
            .text(function (d) {
                return d.data[labelName];
            });

    };
})(EmployeeApp || (EmployeeApp = {}));

/**
 * When the page loads we need to go grab the job category information
 * and create the appropiate graph for it
 */
$(function () {
    EmployeeApp.getJobCategories(function (data) {
        // All of the options for creating the Pie Chart
        var jobPieChartOptions = {
            width: 450,
            height: 450,
            color: d3.scale.category20c(),
            // The keys for the data object that the information to show will be in
            dataName: "count",
            labelName: "jobTitle"
        };
        // Define the radius
        jobPieChartOptions.radius = Math.min(jobPieChartOptions.width, jobPieChartOptions.height) / 2;

        // Call for the pie chart to be made
        EmployeeApp.createPieChart("#jobCategoriesReport", jobPieChartOptions, data);

        // Fadeout the loader and fade in the chart
        $("#jobCategoriesLoadingImage").fadeOut(100, function () {
            $("#jobCategoriesReport").fadeIn();
        })
    });
});



$(function () {
    $(document).tooltip({items: "[title]"});
});