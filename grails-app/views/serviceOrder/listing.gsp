<%@ page   contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All service orders</title>
    <meta name="layout" content="main">
</head>

<body>
<h3>Service Orders </h3>
<g:each in="${serviceOrder}" var ="serviceOrder">
    <div class ="serviceOrders">

        Home:  ${user.serviceOrder.homes.propertyTitle}
        <br/>
        Location: ${user.serviceOrder.location}
        <br/>
        Description: ${user.serviceOrder.description}
        <br/>
        Completed?  ${user.serviceOrder.completed}
        <br/></div>
    <div class="serviceOrderDate">
        Created: <pc:dateFrom date="${serviceOrder.dateCreated}"/>
    </div>

    </div>
</g:each>

<br/>
<br/>
    <g:if test ="${flash.message}">
        <div class="flash">
            ${flash.message}
        </div>
    </g:if>
    <div id="newServiceOrder">
        <h3>Create a new service order</h3>
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