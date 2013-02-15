$(function () {
    // Create a new dataTable from the DOM Table
    var searchDataTable = $('#searchResults').dataTable({
        "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_"
        },
        // Disable sorting for the edit column, as it doesn't make sense
        "aoColumnDefs": [
            { "bSortable": false, 'aTargets': ["edit"]}
        ]
    });

    var employeeKeys;
    $.fn.dataTableExt.afnFiltering.push(
        function (oSettings, aData, iDataIndex) {
            if (!employeeKeys) {
                return true;
            }

            var employeeId = parseInt(aData[0], 10);
            var employeeFound = employeeKeys.indexOf(employeeId) != -1;
            return employeeFound;
        }
    );

    /**
     * Return the first element in an array which matches the predicate function
     * @param func Match the signature (val?, i?, arr?) => bool
     * @return {*}
     */
    Array.prototype.first = function (func) {
        for (var i = 0, length = this.length; i < length; i++) {
            if (func.call(undefined, this[i], i, this)) {
                return this[i];
            }
        }
        return null;
    };

    /**
     * Create a search employee object
     * @return {{firstName: *, secondName: *}}
     */
    var createEmployeeSearchObject = function () {
        var exactMatch = $("#exactMatchSwitch").bootstrapSwitch('status');
        var fieldArray = $("#advancedForm").serializeArray();

        var getField = function(fieldName) {
            var field = fieldArray.first(function (elem) {
                return elem.name == fieldName;
            });
            return field;
        }

        var getFieldWithExactMatch = function (fieldName, exactMatch) {
            var field = getField(fieldName);
            if(!field) {
                return "";
            }

            // Add encapsulating wildcards if not exact match
            return exactMatch ? field.value : "%" + field.value + "%";
        };

        var getFieldBetween = function(fieldName) {
            var field = getField(fieldName);
            var value = field.value;
            // Capture either "\d*" or "\d+-\d+" ignoring whitespace
            var match = /(?:^\s*(\d*)$)|(?:^\s*(\d+)\s*-\s*(\d+)\s*$)/.test(value);
            // If we don't have a match, return
            if(!match) {
                return;
            }
            // Set up a new betweenRange object, and optionally set the max
            // value if the input was in the format "min-max"
            var betweenRange = !RegExp.$2 ? {"min": RegExp.$1} : {"min": RegExp.$2, "max": RegExp.$3};
            return betweenRange;
        }

        var request = {
            "firstName": getFieldWithExactMatch("firstName", exactMatch),
            "secondName": getFieldWithExactMatch("secondName", exactMatch)
        };

        // Can be refactored into a helper if required...
            // Grab the employee id min and max values
            var employeeIdBetween = getFieldBetween("employeeId");
            if(employeeIdBetween) {
                request.minEmployeeId = employeeIdBetween.min;
                request.maxEmployeeId = employeeIdBetween.max;
            }

            // Grab the desk id min and max values
            var deskIdBetween = getFieldBetween("deskId");
            if(deskIdBetween) {
                request.minDeskId = deskIdBetween.min;
                request.maxDeskId = deskIdBetween.max;
            }

        return request;
    };

    var filterTable = function() {
        var searchEmployee = createEmployeeSearchObject();

        console.log(searchEmployee);

        $.postJSON("/employees/search", searchEmployee, function (data) {
            // Get the list of employee keys
            employeeKeys = data.map(function (employee) {
                return employee.id;
            });

            // Clear the filter
            searchDataTable.fnFilter("");

            $("#searchResults").show();
        });
    }

    // Filter the table when the advanced form is edited
    $("#advancedForm").keyup(filterTable);
    $("#exactMatchSwitch").on("switch-change", filterTable);

    // If the user refreshes the fields will need to be filtered
    filterTable();
});

