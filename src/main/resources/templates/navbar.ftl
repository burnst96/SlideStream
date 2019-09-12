<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="${rc.contextPath}">SlideStream</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="groupDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Group: </a>
                <div class="dropdown-menu" aria-labelledby="groupDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle btn btn-dark" id="delayDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Delay: </a>
                <div class="dropdown-menu" aria-labelledby="delayDropdown">
                    <#list [5,10,15,20,25,30,35,40,45,50,55,60] as number>
                        <a class="dropdown-item" href="#">${number}</a>
                    </#list>
                </div>
            </li>
        </ul>
        <a class="nav-link form-inline mt-2 mt-md-0" href="${rc.contextPath}/config"><i class="fas fa-cog text-white"></i></a>
    </div>
</nav>