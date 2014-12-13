<!DOCTYPE html>
	<head>
		<title>Property Connection<g:layoutTitle default="Welcome"/></title>
        <g:external dir="css" file="bootstrap.css"/>
        <g:external dir="css" file="mail.css"/>
        <asset:stylesheet src="application.css"/>

		<g:layoutHead/>
	</head>

	<body>
    <div >


        <div >
            <g:link uri="/">
                <g:img id="logo" uri="${assetPath(src:'propertyconnection.png')}" alt="Property Connection Logo" style="max-height: 120px; margin-bottom: 5px;"/>
            </g:link>
        </div>
        <nav:primary/>
        <nav:secondary/>
        <div>
        <div id="tenant-nav">
            <nav:menu scope="tenant"/>
        </div>
        <div id="landlord-nav">
            <nav:menu scope="landlord"/>
        </div>
        <g:layoutBody/>
        </div>
        <div id="footer">
            <div id="footer-nav">
                <nav:menu scope="footer"/>
            </div>
            <footer>Property Connection - Bringing landlords and tenants together since 2014.</footer>
        </div>

    </div>
    <asset:javascript src="application.js"/>
	</body>
</html>
