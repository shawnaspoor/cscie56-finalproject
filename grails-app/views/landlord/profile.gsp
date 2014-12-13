<html>
<head>
    <title>${profile.loginId}'s Profile</title>
    <meta name="layout" content="main">
</head>

<body>
    <div class="profilePhoto">
        <g:if test="${profile.photo}">
            <img src=""${createLink(controller: 'image', action:'renderImage', id: profile.loginId)} />
        </g:if>
        <p>Profile for <strong>${profile.firstName}</strong></p>
        <p>Put everything else here...</p>

        <div id="allServiceOrders">
            <g:render template="/serviceOrder/serviceOrderList" collection="${}" var="serviceOrder" />
        </div>
    </div>

</body>
</html>