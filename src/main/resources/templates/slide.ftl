<#include "/layout.ftl" />
<script src="${rc.contextPath}/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="${rc.contextPath}/webjars/stomp-websocket/2.3.1/stomp.min.js"></script>
<script src="${rc.contextPath}/js/slide.js"></script>
<script>
    var groups = JSON.parse('<#if groupsJson??>${groupsJson}<#else>{}</#if>');
</script>
<body>
<#include "/navbar.ftl" />
<div id="slideContainer">
    <div id="imageContainer">
        <#--            <div class="spinner-border text-center" role="status">-->
        <#--                <span class="sr-only">Loading...</span>-->
        <#--            </div>-->
        <img id="imageHolder1" class="imageHolder">
        <img id="imageHolder2" class="imageHolder" hidden>
    </div>
</div>
</body>