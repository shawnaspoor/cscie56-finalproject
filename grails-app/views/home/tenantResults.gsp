<h3>Tenants with that email address:</h3>
<p>Searched ${totalTenants} records for users with ${term}. Found ${tenant.size()} users.
</p>
<ul>
    <g:each var="tenant" in="${tenant}">
        <li>${tenant.email}</li>
    </g:each>
    <g:link action="addToHome">Add this tenant to the home.</g:link>
</ul>



<formset>
    <legend><h4>Find your tenant</h4></legend>
    <g:form action="tenantResults">
        <label for="email">Email Address</label>
        <g:textField name="email" />
        <g:submitButton name="search" value="Search" />
    </g:form>
</formset>
