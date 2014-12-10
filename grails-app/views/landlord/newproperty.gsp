<html>
    <head>
        <title>Create a new home</title>
        <meta name ="layout" content="main" />
    </head>
    <body>
        <h3>Create a new home</h3>
        <g:hasErrors>
            <div class="errors">
                <g:renderErrors bean="${landlord}" as="list" />
            </div>
        </g:hasErrors>
        <g:if test="${flash.message}">
            <div class="flash">${flash.message}</div>
        </g:if>
        <g:form action="createHome">
            <fieldset class="form">
                <div class="fieldcontain required">
                    <label for="propertyTitle">Property Title</label>
                    <g:textField name="propertyTitle" value="${home?.propertyTitle}" />
                </div>
                <div class="fieldcontain required">
                    <label for="streetAddress">Street Address</label>
                    <g:textField name="streetAddress" value="${home?.streetAddress}" />
                </div>
                <div class="fieldcontain required">
                    <label for="city">City</label>
                    <g:textField name="city" value="${home?.city}" />
                </div>
                <div class="fieldcontain required">
                    <label for="zipcode">Zipcode</label>
                    <g:textField name="zipcode" value="${home?.zipcode}" />
                </div>
                <div class="fieldcontain required">
                <label for="bedrooms">bedrooms</label>
                <g:textField name="bedrooms" value="${home?.bedrooms}" />
                </div>
                <div class="fieldcontain required">
                    <label for="baths">baths</label>
                    <g:textField name="baths" value="${home?.baths}" />
                </div>
            </fieldset>
            <fieldset>
                <g:submitButton name="createHome" value="CreateHome" />
            </fieldset>
        </g:form>
    </body>
</html>