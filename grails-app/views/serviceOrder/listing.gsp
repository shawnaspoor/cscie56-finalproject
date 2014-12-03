<%--
  Created by IntelliJ IDEA.
  User: Shawna
  Date: 12/1/2014
  Time: 8:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All service orders</title>
</head>

<body>
<h3>Service Orders</h3>
    <div id="allServiceOrders">
        <g:each in="${tenant.serviceOrders}" var ="serviceOrder">
            <div class="serviceOrderItem">
                <div class ="serviceOrderDetails">
                   Location: ${serviceOrder.location}
                    < br/>
                   Description: ${serviceOrder.description}
                   <hr>
                </div>
            </div>
        </g:each>
    </div>

</body>
</html>