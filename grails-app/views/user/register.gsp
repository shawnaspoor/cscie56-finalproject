<html>
    <head>
        <title>Register now!</title>
        <meta name ="layout" content="main" />
    </head>
    <body>
        <h3>Register now</h3>
        <g:hasErrors>
            <div class="errors">
                <g:renderErrors bean="${user}" as="list" />
            </div>
        </g:hasErrors>
        <g:if test="${flash.message}">
            <div class="flash">${flash.message}</div>
        </g:if>
        <g:form action="register">
            <fieldset class="form">
                <g:select for="selection" name="selection" from="${['Landlord', 'Tenant']}"
                         value="${user?.selection}"/>
                <div class="fieldcontain required">
                    <label for="loginId">LoginId</label>
                    <g:textField name="loginId" value="${user?.loginId}" />
                </div>
                <div class="fieldcontain required">
                    <label for="firstName">First Name</label>
                    <g:textField name="firstName" value="${user?.firstName}" />
                </div>
                <div class="fieldcontain required">
                    <label for="lastName">Last Name</label>
                    <g:textField name="lastName" value="${user?.lastName}" />
                </div>
                <div class="fieldcontain required">
                    <label for="email">Email</label>
                    <g:textField name="email" value="${user?.email}" />
                </div>
                <div class="fieldcontain required">
                <label for="password">Password</label>
                <g:passwordField name="password" value="${user?.password}" />
                </div>
                <div class="fieldcontain required">
                    <label for="passwordRepeat">Please type your password again:</label>
                    <g:passwordField name="passwordRepeat" value="${user?.passwordRepeat}" />
                </div>
            </fieldset>
            <fieldset>
                <g:submitButton name="register" value="Register" />
            </fieldset>
        </g:form>
    </body>
</html>