<html>
    <head>
        <title>Register as a tenant now!</title>
        <meta name ="layout" content="main" />
    </head>
    <body>
        <h3>Register as a landlord now</h3>
        <g:hasErrors>
            <div class="errors">
                <g:renderErrors bean="${landlord}" as="list" />
            </div>
        </g:hasErrors>
        <g:if test="${flash.message}">
            <div class="flash">${flash.message}</div>
        </g:if>
        <g:form action="register">
            <fieldset class="form">
                <div class="fieldcontain required">
                    <label for="loginId">LoginId</label>
                    <g:textField name="loginId" value="${tenant?.loginId}" />
                </div>
                <div class="fieldcontain required">
                    <label for="firstName">First Name</label>
                    <g:textField name="firstName" value="${tenant?.firstName}" />
                </div>
                <div class="fieldcontain required">
                    <label for="lastName">Last Name</label>
                    <g:textField name="lastName" value="${tenant?.lastName}" />
                </div>
                <div class="fieldcontain required">
                    <label for="email">Email</label>
                    <g:textField name="email" value="${tenant?.email}" />
                </div>
                <div class="fieldcontain required">
                <label for="password">Password</label>
                <g:passwordField name="password" value="${tenant?.password}" />
                </div>
                <div class="fieldcontain required">
                    <label for="passwordRepeat">Please type your password again:</label>
                    <g:passwordField name="passwordRepeat" value="${tenant?.passwordRepeat}" />
                </div>
            </fieldset>
            <fieldset>
                <g:submitButton name="register" value="Register" />
            </fieldset>
        </g:form>
    </body>
</html>