$(document).ready(function() {
    connectImageWebSocket();

    $('nav').hide();

    $("html").mousemove(function() {
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

function connectImageWebSocket() {
    var socket = new SockJS('socket/images/170');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function() {
        stompClient.subscribe('/console/log', function(message){

            var jsonMsg = JSON.parse(message.body);
            console.log(jsonMsg);

            // if(false) {
                stompClient.disconnect();
                return;
            // }

            // addJsonMessageToConsole(jsonMsg);
        });
    });
}