<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="${rc.contextPath}">SlideStream</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li id="groupDropdown" class="nav-item dropdown" data-toggle="dropdown" data-target="#groupDropdownMenu"
                aria-haspopup="true">
                <a class="nav-link dropdown-toggle btn btn-dark">Group: </a>
                <div id="groupDropdownMenu" class="dropdown-menu" aria-labelledby="groupDropdown">
                    <#list groups as group>
                        <a id="selectGroup-${group.pk}" class="dropdown-item" href="#"
                           onclick="connectImageWebSocket(${group.pk})">${group.name}</a>
                    </#list>
                </div>
            </li>
            <li id="delayDropdown" class="nav-item dropdown" data-toggle="dropdown" data-target="#delayDropdownMenu"
                aria-haspopup="true">
                <a class="nav-link btn btn-dark dropdown-toggle">Delay: </a>
                <div id="delayDropdownMenu" class="dropdown-menu" aria-labelledby="delayDropdown">
                    <#list [5,10,15,20,25,30,35,40,45,50,55,60,120,300,600] as number>
                        <a id="selectDelay-${number}" class="dropdown-item" href="#"
                           onclick="updateGroupDelay()"><#if number <= 60>${number} Seconds<#else>${number/60} Minutes</#if></a>
                    </#list>
                </div>
            </li>
        </ul>
        <a class="nav-link form-inline mt-2 mt-md-0" href="${rc.contextPath}/config"><i
                    class="fas fa-cog text-white"></i></a>
    </div>
</nav>