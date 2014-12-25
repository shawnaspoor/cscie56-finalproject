<!DOCTYPE html>
	<head>
		<title>Property Connection<g:layoutTitle default="Welcome"/></title>
        <g:external dir="css" file="bootstrap.css"/>

        <asset:stylesheet src="application.css"/>
        <asset:stylesheet src="bootstrap.css"/>
		<g:layoutHead/>

        <style>
        .container {
            max-width: 1000px;
        }
        footer {
            padding: 50px;
            text-align: center;
        }
        </style>
	</head>

	<body class="container">


    <div class="container">
        <div>
            <g:link uri="/">
                <g:img id="logo" uri="${assetPath(src:'propertyconnection.png')}" alt="Property Connection Logo" style="max-height: 120px; margin-bottom: 5px;"/>
            </g:link>
            <aside style="float: right; padding-top: 70px; padding-right: 15px;">
                <sec:ifLoggedIn>
                    <g:form name="logoutForm" controller="logout" action="index">
                        <g:submitButton name="signOut" value="Sign Out" />
                    </g:form>
                </sec:ifLoggedIn>
            </aside>

        </div>
        <div class="container">
            <!-- Static navbar -->


            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                    </div>
                    <div id="navbar" class="navbar-collapse collapse" >
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="/propertyconnection">Home</a></li>
                            <li><a href="/propertyconnection/landlord/show/${params.id}">Landlord Profile</a></li>
                            <li><a href="/propertyconnection/tenant/show/${params.id}">Tenant Profile</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Property <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="/propertyconnection/landlord/homes/${params.id}">Homes</a></li>
                                    <li><a href="/propertyconnection/serviceOrder/listing/${params.id}">Service Order</a></li>

                                </ul>
                            </li>
                        </ul>
                        <sec:ifNotLoggedIn>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><a href="login/auth?format=">Login</a></li>

                            <li><a href="/propertyconnection/user/register">Register</a></li>

                        </ul>
                        </sec:ifNotLoggedIn>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
            <div class="container">
            <g:layoutBody/>
            </div>

        <div id="footer">
            <footer>Property Connection - Bringing landlords and tenants together since 2014.</footer>
         </div>
</div>

<asset:javascript src="application.js"/>
<asset:javascript src="bootstrap.js"/>
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

</body>

</html>
