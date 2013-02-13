$(function () {
    $('#searchResults').dataTable({
        "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_"
        },
        // Disable sorting for the edit column, as it doesn't make sense
        "aoColumnDefs" : [
            { "bSortable" : false, 'aTargets' : ["edit"]}
        ]
    });
});