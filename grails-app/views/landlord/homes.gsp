<html>
    <head>
            <title>Your Properties</title>
        <meta name ="layout" content="main" />
    </head>

<body>
<div class="container">
<h3>Properties</h3>
<div id="allHomes">
    <g:each in="${landlord.homes}" var ="homes">
            <div class ="homeDetails">
                Property details: ${homes.propertyTitle}
                <br/>
                Address: ${homes.streetAddress}
                <br /><br />
                <fieldset>
                    <a  class="buttons" href="/propertyconnection/home/show/${homes.id}" class="edit">Details</a>
                </fieldset>
                <hr>
                <br/>
            </div>
        </div>
    </g:each>
</div>

<h3>Create a new home</h3>
<g:hasErrors>
    <div class="errors">
        <g:renderErrors bean="${landlord}" as="list" />
    </div>
</g:hasErrors>
<g:if test="${flash.message}">
    <div class="flash">${flash.message}</div>
</g:if>
<g:form action="createHome"  id="${params.id}">
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
            <label for="state">State</label>
            <g:select for="state" name="state" from="${['Alabama',
                                                        'Alaska',
                                                        'Arizona',
                                                        'Arkansas',
                                                        'California',
                                                        'Colorado',
                                                        'Connecticut',
                                                        'Delaware',
                                                        'Florida',
                                                        'Georgia',
                                                        'Hawaii',
                                                        'Idaho',
                                                        'Illinois',
                                                        'Indiana',
                                                        'Iowa',
                                                        'Kansas',
                                                        'Kentucky',
                                                        'Louisiana',
                                                        'Maine',
                                                        'Maryland',
                                                        'Massachusetts',
                                                        'Michigan',
                                                        'Minnesota',
                                                        'Mississippi',
                                                        'Missouri',
                                                        'Montana',
                                                        'Nebraska',
                                                        'Nevada',
                                                        'New Hampshire',
                                                        'New Jersey',
                                                        'New Mexico',
                                                        'New York',
                                                        'North Carolina',
                                                        'North Dakota',
                                                        'Ohio',
                                                        'Oklahoma',
                                                        'Oregon',
                                                        'Pennsylvania',
                                                        'Rhode Island',
                                                        'South Carolina',
                                                        'South Dakota',
                                                        'Tennessee',
                                                        'Texas',
                                                        'Utah',
                                                        'Vermont',
                                                        'Virginia',
                                                        'Washington',
                                                        'West Virginia',
                                                        'Wisconsin',
                                                        'Wyoming',]}"
                      value="${home?.state}"/>
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
</div>

</body>
</html>