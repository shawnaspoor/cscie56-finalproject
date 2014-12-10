<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All service orders</title>
    <meta name="layout" content="main">
</head>

<body>
<h3>Service Orders</h3>
    <div id="allServiceOrders">
        <g:each in="${tenant.serviceOrders}" var ="serviceOrder">
            <div class="serviceOrderItem">
                <div class ="serviceOrderDetails">
                    ID:  ${serviceOrder.id}
                    <br/>
                   Location: ${serviceOrder.location}
                    <br/>
                   Description: ${serviceOrder.description}
                   <hr>
                    <br/>
                    <br/>
                </div>
            </div>
        </g:each>
    </div>
    <g:if test ="${flash.message}">
        <div class="flash">
            ${flash.message}
        </div>
    </g:if>
    <div id="newServiceOrder">
        <p>Please give as much information about the issue as possible</p>
        <g:form action ="createServiceOrder" id="${params.id}">
            <div class="serviceOrderDescription">
                Description:
                <g:textArea id="serviceOrderDescription" name="description" rows="5" cols="100" />
            </div>
            <div class="serviceOrderLocation">
                Location:
                <g:textField name="location" id="serviceOrderLocation" />
            </div>

            <g:submitButton name="serviceOrder" value="ServiceOrder" />
        </g:form>
    </div>


</body>
</html>