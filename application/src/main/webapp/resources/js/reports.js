/**
 * Add the getJobCategories functionality to the EmployeeApp
 */
(function (EmployeeApp) {
    EmployeeApp.getJobCategories = function (callback) {
        // Mimic being slow (Obviously you wouldn't do this in production!)
        setTimeout(function () {
            $.getJSON("/reports/jobs/titleCounts", {}, callback);
        }, 200);
    }
})(EmployeeApp || (EmployeeApp = {}));

/**
 * Adds the ability to get the employee hierachy
 */
(function (EmployeeApp) {
    EmployeeApp.getEmployeeHierarchy = function (callback) {
        // Mimic being slow (Obviously you wouldn't do this in production!)
        setTimeout(function () {
            // Return fake data, as this isn't properly implemented in the database
            var employeeHierarchy =
            {
                "firstName": "John",
                "secondName": "Smith",
                "job": {"jobId": 1, "jobTitle": "CEO"},
                "subordinates": [
                    {
                        "firstName": "Ray",
                        "secondName": "McPherson",
                        "job": {"jobId": 2, "jobTitle": "Executive"},
                        "subordinates": [
                            {
                                "firstName": "Darren",
                                "secondName": "Bevilacqua",
                                "job": {"jobId": 3, "jobTitle": "Principal Engineer"}
                            }
                        ]
                    },
                    {
                        "firstName": "Ted",
                        "secondName": "Whaley",
                        "job": {"jobId": 2, "jobTitle": "Executive"}
                    },
                    {
                        "firstName": "Helen",
                        "secondName": "Bevilacqua",
                        "job": {"jobId": 2, "jobTitle": "Executive"},
                        "subordinates": [
                            {
                                "firstName": "Allan",
                                "secondName": "Motyka",
                                "job": {"jobId": 3, "jobTitle": "Principal Engineer"},
                                "subordinates": [
                                    {
                                        "firstName": "Roy",
                                        "secondName": "McHatterson",
                                        "job": {"jobId": 4, "jobTitle": "Senior Engineer"}
                                    },
                                    {
                                        "firstName": "David",
                                        "secondName": "Zephyros",
                                        "job": {"jobId": 4, "jobTitle": "Engineer"}
                                    }
                                ]
                            }
                        ]
                    }
                ]
            };
            callback(employeeHierarchy);
        }, 200);
    }
})(EmployeeApp || (EmployeeApp = {}));

/**
 * Adds a basic method of creating a pieChart
 */
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
            })
            .attr("class", "arcLabel");

    };
})(EmployeeApp || (EmployeeApp = {}));


/**
 * Add a basic tree drawer
 */
(function (EmployeeApp) {

    EmployeeApp.createTree = function (selector, treeData, options) {
        var labelFunc = options.labelFunc,
            size = options.size,
            fontSize = options.fontSize,
            nodeRadius = options.fontSize,
            childrenKey = options.childrenKey;

        /**
         * A function to work out the widest length label in a tree
         * Recursively calls itself and chooses the label that is biggest from either itself or its children and returns it
         * @param parent The parent node
         * @param labelFunc The function for calculating the label
         * @return {*|number} The widest label length
         */
        var getWidestLabelSize = function (parent, labelFunc) {
            var maximumLabelLength = labelFunc(parent).length;
            var children = parent[childrenKey];

            if (children && children.length > 0) {
                // Get the maximum child size
                children.forEach(function (child) {
                    maximumLabelLength = Math.max(maximumLabelLength, getWidestLabelSize(child, labelFunc));
                })
            }

            return maximumLabelLength;
        };

        var maxLabelLength = getWidestLabelSize(treeData, labelFunc);

        var svg = d3.select(selector)
            .append("svg:svg").attr("width", size.width).attr("height", size.height)
            .append("svg:g")
            .attr("class", "container")
            .attr("transform", "translate(" + maxLabelLength + ",0)");

        var tree = d3.layout.tree()
            .size([size.height, size.width])
            .children(function (d) {
                return (!d[childrenKey] || d[childrenKey].length === 0) ? null : d[childrenKey];
            });

        var nodes = tree.nodes(treeData);
        var links = tree.links(nodes);

        var link = d3.svg.diagonal()
            .projection(function (d) {
                return [d.x, d.y];
            });

        svg.selectAll("path.link")
            .data(links)
            .enter()
            .append("svg:path")
            .attr("class", "link")
            .attr("d", link);

        var nodeGroup = svg.selectAll("g.node")
            .data(nodes)
            .enter()
            .append("svg:g")
            .attr("class", "node")
            .attr("transform", function (d) {
                return "translate(" + d.x + "," + d.y + ")";
            });


        nodeGroup.append("svg:circle")
            .attr("class", "node")
            .attr("r", nodeRadius);

        // Create labels
        nodeGroup.append("svg:text")
            .attr("dy", function (d) {
                var gap = 2 * nodeRadius;
                return -gap;
            })
            .attr("dx", function (d) {
                return -(labelFunc(d).length / 2) * fontSize;
            })
            .text(labelFunc)
    }
})(EmployeeApp || (EmployeeApp = {}));


/**
 * When the page loads we need to go grab the job category information
 * and create the appropiate graph for it
 */
$(function () {
    EmployeeApp.getJobCategories(function (data) {
        /**
         * A function to fade out the ajax loader and fade in the required element
         */
        function swapAjaxLoaderWith(selector) {
            // Fadeout the loader and fade in required element
            $("#jobCategoriesLoadingImage").fadeOut(100, function () {
                $(selector).fadeIn();
            })
        }

        // Check if the data is empty, and if it is, update the interface
        if (!data || data.length === 0) {
            swapAjaxLoaderWith("#jobCategoriesEmpty");
            return;
        }

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

    EmployeeApp.getEmployeeHierarchy(function (treeData) {
        // Create the options for the tree
        var options = {
            labelFunc: function (data) {
                return data.firstName + " " + data.secondName;
            },
            size: {width: 700, height: 700},
            fontSize: 6,
            nodeRadius: 10,
            childrenKey: "subordinates"
        }

        EmployeeApp.createTree("#employeeHierarchyTree", treeData, options);
    });

});