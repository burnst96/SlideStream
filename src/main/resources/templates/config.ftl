<#--<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />-->
<#--<#import "/layout/layout.ftl" as layout/>-->
<#--<#import "/spring.ftl" as spring>-->
<#include "/layout.ftl" />
<script src="${rc.contextPath}/js/config.js"></script>
<script>
    var baseUrl = '${rc.contextPath}';
    var groupingsJson = JSON.parse('<#if groupingsJson??>${groupingsJson}<#else>{}</#if>');
    var configurationJson = JSON.parse('<#if configurationJson??>${configurationJson}<#else>{}</#if>');
</script>
<body>
    <#include "/navbar.ftl" />
    <div id="configContainer" class="d-flex justify-content-center" style="position:absolute; top: 100px; width:100%">
        <#--<div id="dropzone">-->
            <#--<form method="POST" action="${rc.contextPath}/uploadImages" class="dropzone needsclick dz-clickable" id="uploadImages" enctype="multipart/form-data">-->
                <#--<div class="dz-message needsclick">-->
                    <#--Drop files here or click to upload.<br>-->
                <#--</div>-->
            <#--</form>-->
        <#--</div>-->
        <#--<div>-->
            <#--<p><#if groupingsJson??>${groupingsJson}</#if></p>-->
            <#--<p><#if configurationJson??>${configurationJson}</#if></p>-->
        <#--</div>-->
        <div id="imagesCard" class="card col-sm ml-3 mr-3">
            <h4 class="card-header">
                Images
            </h4>
            <div class="card-body">
                <ul id="sortableImages" class="list-group">
                    <#if images??>
                        <#list images as image>
                            <li class="ui-state-default list-group-item list-group-item-action" data-id="${image.pk}" data-index="${image?index}">
                                <i class="fas fa-bars"></i>
                                <a href="#" class="pr-2 pl-2" style="word-break: break-word;text-align: center">
                                    ${image.name}
                                </a>
                                <a class="text-right flex-fill">
                                    <i class="fas fa-arrow-alt-circle-down imageDownload clickable-icon"></i>
                                    <i class="fas fa-trash-alt deleteImage clickable-icon"></i>
                                </a>
                            </li>
                        </#list>
                    </#if>
                </ul>
                <li id="uploadImages" class="ui-state-disabled list-group-item list-group-item-action dz-message needsclick">
                    <a href="#"><i class="fas fa-plus"></i>Add Image</a>
                </li>
            </div>
        </div>
        <div id="groupsCard" class="card col-sm mr-3 ml-1">
            <h4 class="card-header">
                Groups
            </h4>
            <div class="card-body">
                <div id="accordion" class="accordion">
                    <div id="groupAccordionContainer" class="card mb-0">
                        <#if groups??>
                            <#list groups as group>
                                <div class="groupContainer" data-group-id="${group.pk}">
                                    <div class="card-header collapsed" data-toggle="collapse" href="#collapseGroup${group.pk}">
                                        <a class="card-title">
                                            ${group.name}
                                        </a>
                                        <a class="text-right flex-fill">
                                            <i class="fas fa-trash-alt deleteGroup clickable-icon"></i>
                                        </a>
                                    </div>
                                    <div id="collapseGroup${group.pk}" class="card-body collapse" data-parent="#accordion">
                                        <ul class="sortableGroups list-group" data-id="${group.pk}">
                                            <#if group.images??>
                                                <#list group.images as image>
                                                    <li class="groupImageCollapsible ui-state-default list-group-item list-group-item-action" data-index="${image?index}" data-image-id="${image.pk}" data-group-id="${group.pk}">
                                                        <a href="#"><i class="fas fa-bars"></i>${image.name}</a>
                                                        <a class="text-right flex-fill">
                                                            <i class="fas fa-trash-alt removeImageFromGroup clickable-icon"></i>
                                                        </a>
                                                    </li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>
                <div class="card mb-0">
                    <div id="createGroup" class="card-header">
                        <a class="card-title">
                            <i class="fas fa-plus"></i> Add Group
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>