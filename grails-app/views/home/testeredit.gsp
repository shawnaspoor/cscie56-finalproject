<html>
<head>
    <title>Title </title>
    <meta name ="layout" content="main" />
</head>

<body>
<div class="container">

<h3>Edit ${params.id}</h3>
<g:hasErrors>
    <div class="errors">
        <g:renderErrors bean="${home}" as="list" />
    </div>
</g:hasErrors>
<g:if test="${flash.message}">
    <div class="flash">${flash.message}</div>
</g:if>
<g:form action="editHome"  id="${params.id}">
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
        <g:submitButton name="editHome" value="EditHome" />
    </fieldset>
</g:form>
</div>

</body>
</html>



<!DOCTYPE html>
<head>
    <title>Property Connection Edit Home</title>

    <meta name="layout" content="main">

</head>


<div class="container">
    <div id="edit-home" class="content scaffold-edit" role="main">
        <h3>Edit Home ${params.id}</h3>


        <form action="update" method="post" enctype="multipart/form-data" ><input type="hidden" name="_method" value="PUT" id="_method" />
            <input type="hidden" name="version" value="0" id="version" />
            <fieldset class="form">
                <div class="fieldcontain  required">
                    <label for="propertyTitle">
                        Property Title
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="propertyTitle" maxlength="30" required="" value="${params.propertyTitle} " id="propertyTitle" />

                </div>

                <div class="fieldcontain  required">
                    <label for="streetAddress">
                        Street Address
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="streetAddress" required="" value="${params.streetAddress} " id="streetAddress" />

                </div>

                <div class="fieldcontain  required">
                    <label for="city">
                        City
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="city" required="" value=" " id="city" />

                </div>

                <div class="fieldcontain  required">
                    <label for="zipcode">
                        Zipcode
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="zipcode" required="" value=" " id="zipcode" />

                </div>

                <div class="fieldcontain  required">
                    <label for="bedrooms">
                        Bedrooms
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="number" name="bedrooms" value="" required="" id="bedrooms" />

                </div>

                <div class="fieldcontain  required">
                    <label for="baths">
                        Baths
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="number" name="baths" value="" required="" id="baths" />

                </div>

                <!-- <div class="fieldcontain  ">
                        <label for="photo">
                            Photo

                        </label>
                        <input type="file" id="photo" name="photo" />

                    </div>-->

                <div class="fieldcontain  ">
                    <label for="tenant">
                        Current Tenant: ${params.tenant}

                    </label>
                    <formset>
                        <legend>Find your tenant</legend>
                        <g:form action="tenantResults">
                            <label for="email">Email Address</label>
                            <g:textField name="email" />
                            <g:submitButton name="search" value="Search" />
                        </g:form>
                    </formset>


                </div>

            </fieldset>
            <fieldset class="buttons">
                <input type="submit" name="update" value="Update" class="save" />
            </fieldset>
        </form>
    </div>

</div>
<div id="footer">
    <footer>Property Connection - Bringing landlords and tenants together since 2014.</footer>
</div>
</div>

<script src="/propertyconnection/assets/jquery/jquery-1.11.1.js?compile=false" type="text/javascript" ></script>
<script src="/propertyconnection/assets/jquery.js?compile=false" type="text/javascript" ></script>
<script src="/propertyconnection/assets/bootstrap.js?compile=false" type="text/javascript" ></script>
<script src="/propertyconnection/assets/bootstrap.min.js?compile=false" type="text/javascript" ></script>
<script src="/propertyconnection/assets/npm.js?compile=false" type="text/javascript" ></script>
<script src="/propertyconnection/assets/application.js?compile=false" type="text/javascript" ></script>

<script src="/propertyconnection/assets/bootstrap.js?compile=false" type="text/javascript" ></script>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

</body>

</html>
