<#include "/layout.ftl" />
<script src="${rc.contextPath}/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="${rc.contextPath}/webjars/stomp-websocket/2.3.1/stomp.min.js"></script>
<script src="${rc.contextPath}/js/slide.js"></script>
<body>
    <#include "/navbar.ftl" />
    <div id="slideContainer">
        <div id="imageContainer">
            <#if images??>
                <#list images as image>
                    <img id="imageHolder1" class="imageHolder" src="data:image/png;base64,${image.getValueAsBase64String()}" alt="image: ${image.name}">
                    <img id="imageHolder2" class="imageHolder" hidden alt="image: ${image.name}">
                </#list>
            <#else>
                <i class="fa fa-spinner"></i><h2>Syncing...</h2>
            </#if>
        </div>
    </div>
</body>