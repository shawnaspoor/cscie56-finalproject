<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Home ${home.id}</title>
        <meta name ="layout" content="main" />
    </head>

    <body>
    <div in="${details}" var ="details">
        <div class ="homeDetails">
            Property details: ${home.propertyTitle}
            <br/>
            Address: ${home.streetAddress}
            <br/>
            City: ${home.city}
            <br/>
            State:
            <br/>
            Zipcode: ${home.zipcode}
            <br/>
            Bedrooms: ${home.bedrooms}
            <br/>
            baths: ${home.baths}
            <br /><br />
            <fieldset>
                <a  class="buttons" href="/propertyconnection/home/edit/${home.id}" class="edit">Edit</a>

               ;" />
            </fieldset> <g:actionSubmit action="delete" name="delete" value="Delete" class="delete" onclick="return confirm('Are you sure?')
            <hr>
            <br/>
        </div>
    </div>
    </body>
</html>