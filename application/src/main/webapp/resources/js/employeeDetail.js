/**
 * Binds the event for when the employee delete button is clicked it
 * will show the bootstrap modal and ask for a delete confirmation
 */
$(function () {
    // Store the delete url for when the modal confirm button is clicked
    var deleteUrl = "#";

    // Redirect to the delete url when the confirm button is clicked
    $("#confirmDelete").click(function(event) {
        window.location = deleteUrl;
    });

    // Grab the delete button from the employee form, and whe nit's clicked
    $("#confirmEmployeeDelete").click(function(event) {
        // Keep track of the deleteUrl so we can redirect when the confirm button is clicked
        deleteUrl = this.getAttribute("href");
        // Show the modal
        $("#deleteEmployeeModel").modal("show");
        // Return false so the window doesn't redirect to the delete without confirmation
        return false;
    })
});