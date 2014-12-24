

<!DOCTYPE html>
<head>
    <title>Edit Home</title>

    <meta name="layout" content="main">

</head>
<div class="container">
    <div id="updateHome"  role="main">

        <g:hasErrors>
            <div class="errors">
                <g:renderErrors bean="${home}" as="list" />
            </div>
        </g:hasErrors>
        <g:if test="${flash.message}">
            <div class="flash">${flash.message}</div>
        </g:if>
        <h3>Edit Home: ${home.propertyTitle}</h3>

        <form action="updateHome"  id="${params.id}">
            <fieldset class="form">
               <div class="fieldcontain  required">
                    <label for="propertyTitle">
                        Property Title
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="propertyTitle" maxlength="30" required="" value="${home.propertyTitle} " id="propertyTitle" />

                </div>

                <div class="fieldcontain  required">
                    <label for="streetAddress">
                        Street Address
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="streetAddress" required="" value="${home.streetAddress} " id="streetAddress" />

                </div>

                <div class="fieldcontain  required">
                    <label for="city">
                        City
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="city" required="" value=" ${home.city}" id="city" />

                </div>

                <div class="fieldcontain  required">
                    <label for="zipcode">
                        Zipcode
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="zipcode" required="" value="${home.zipcode} " id="zipcode" />

                </div>

                <div class="fieldcontain  required">
                    <label for="bedrooms">
                        Bedrooms
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="number" name="bedrooms" value="${home.bedrooms}" required="" id="bedrooms" />

                </div>

                <div class="fieldcontain  required">
                    <label for="baths">
                        Baths
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="number" name="baths" value="${home.baths}" required="" id="baths" />

                </div>
                <div class="fieldcontain  required">
                    <label for="id">
                        Id
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="number" name="id" value="${params.id}" required="" id="id" />

                </div>

                <!-- <div class="fieldcontain  ">
                        <label for="photo">
                            Photo

                        </label>
                        <input type="file" id="photo" name="photo" />

                    </div>-->

                <div class="fieldcontain  ">
                    <label>Current Tenant:</label>
                <g:if test="${home.tenant == null}">No tenants currently. </g:if>
                <g:else>${home.tenant.firstName} ${home.tenant.lastName}</g:else>
            </div>
            <p>home id: ${home.id} or params.id = ${params.id}</p>
            </fieldset>
            <br />
            <fieldset class="buttons">
                <g:actionSubmit action="updateHome" name="Update Home" value="Update Home" class="save" />
            </fieldset>
        </form>
    </div>

</div>

</div>


</body>

</html>
