var sortableImagessCopy;
var sortableOptions = {
    revert: true,
    items: "li:not(.ui-state-disabled)",
    connectWith: ".sortableGroups",
    dropOnEmpty: true,
    placeHolder: "highlight",
    receive: function(event, ui) {
        var groupId = $(this).attr('data-id');
        var imageId = ui.item.attr('data-id');
        addImageToGroup(groupId, imageId);
    },
    stop: function(event, ui) {
        $('#sortableImages').replaceWith(sortableImagessCopy);
        initSortables();
    }
};

$(document).ready(function() {
    sortableImagessCopy = $('#sortableImages').clone(true);

    initSortables();

    $('#createGroup').click(createGroup);
    $('.deleteImage').click(deleteImage);
    $('.deleteGroup').click(deleteGroup);
    $('.removeImageFromGroup').click(removeImageFromGroup);
    $('.imageDownload').click(downloadImage);
    $('.groupImageCollapsible').click(initSortables);

    $('#uploadImages').dropzone({
        url: baseUrl + '/config/uploadImages',
        method: 'POST',
        acceptedFiles: ".png, .jpeg",
        success: function(file) {
            file.previewElement.innerHTML = "";
            location.reload();
        }
    });
});

function initSortables() {
    $('#sortableImages, .sortableGroups').sortable(sortableOptions).disableSelection();
    sortableImagessCopy = $('#sortableImages').clone(true);
}

function downloadImage(e) {
    var imageId = $(e.currentTarget).closest('.list-group-item').attr('data-id');
    $.ajax({
        url: 'downloadImage/' + imageId,
        method: 'GET'
    });
}

function deleteImage(e) {
    var imageRow = $(e.currentTarget).closest('.list-group-item');
    var imageId = imageRow.attr('data-id');
    $.ajax({
        url: 'config/deleteImage/' + imageId,
        method: 'GET',
        success: function() {
            imageRow.remove();
        }
    });
}

function createGroup() {
    $.ajax({
        url: 'config/createGroup',
        method: 'GET',
        success: function(result) {
            location.reload(); // TODO - add new element instead of reloading page
        }
    });
}

function deleteGroup(e) {
    var imageRow = $(e.currentTarget).closest('.groupContainer');
    var groupId = imageRow.attr('data-group-id');
    $.ajax({
        url: 'config/deleteGroup/' + groupId,
        method: 'GET',
        success: function() {
            imageRow.remove();
        }
    });
}

function addImageToGroup(groupId, imageId) {
    $.ajax({
        url: 'config/addImageToGroup/' + groupId + "/" + imageId,
        method: 'GET',
        success: function() {}
    });
}

function removeImageFromGroup(e) {
    var groupImageRow = $(e.currentTarget).closest('.list-group-item');
    var groupId = groupImageRow.attr('data-group-id');
    var imageId = groupImageRow.attr('data-image-id');
    $.ajax({
        url: 'config/removeImageFromGroup/' + groupId + "/" + imageId,
        method: 'GET',
        success: function() {
            groupImageRow.remove();
        }
    });
}