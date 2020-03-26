let connectedGroup;
let stompClient;

$(document).ready(function () {
    connectImageWebSocket(-1);

    $('nav').hide();

    $("html").mousemove(function () {
        $("nav").slideDown();

        resetHideNavbarTimeout();
        hideNavbar();
    });

    function hideNavbar() {
        myVar = setTimeout(function(){
            $("nav").slideUp();
        }, 1000);
    }

    function resetHideNavbarTimeout() {
        if(typeof myVar != 'undefined'){
            clearTimeout(myVar);
        }
    }
});

function updateImage(imageBytes) {
    $('#imageHolder1').attr('src', 'data:image/png;base64,' + imageBytes);
}

function updateGroupDelay(delayInSeconds) {
    $.ajax({
        url: 'config/updateDelay/' + connectedGroup.pk + "/" + delayInSeconds,
        method: 'GET',
        success: function (message) {
            console.log(message);
        }
    });
}

function updateTransitionType() {
    $.ajax({
        url: 'config/deleteImage/' + imageId,
        method: 'GET',
        success: function () {
            imageRow.remove();
        }
    });
}

function connectImageWebSocket(groupPk) {
    if (stompClient) {
        stompClient.disconnect();
    }

    if (groups.length > 0) {
        stompClient = Stomp.over(new SockJS('socket'));
        stompClient.connect({}, function () {
            if (groupPk > -1) {
                groups.forEach(g => {
                    if (g.pk === groupPk) {
                        connectedGroup = g
                    }
                });
            } else {
                groupPk = groups[0].pk;
            }

            stompClient.subscribe('/images/' + (groupPk), function (message) {
                updateImage(JSON.parse(message.body).value);
            });
        });
    } else {
        console.log("No image groups found...");
    }
}