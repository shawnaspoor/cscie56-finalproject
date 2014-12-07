<%--
  Created by IntelliJ IDEA.
  User: Shawna
  Date: 12/1/2014
  Time: 8:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Your Properties</title>
</head>

<body>
<h3>Properties</h3>
<div id="allHomes">
    <g:each in="${landlord.homes}" var ="homes">
            <div class ="homeDetails">
                Property details: ${homes.propertyTitle}
                < br/>
                street ${homes.streetAddress}
                <hr>
            </div>
        </div>
    </g:each>
</div>


</body>
</html>