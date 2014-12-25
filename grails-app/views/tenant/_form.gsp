<%@ page import="com.propertyconnection.Tenant" %>



<div class="fieldcontain ${hasErrors(bean: tenantInstance, field: 'loginId', 'error')} required">
	<label for="loginId">
		<g:message code="tenant.loginId.label" default="Login Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="loginId" maxlength="20" required="" value="${tenantInstance?.loginId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tenantInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="tenant.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
    <g:passwordField name="password" required="" value="${tenantInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tenantInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="tenant.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${tenantInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tenantInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="tenant.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${tenantInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tenantInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="tenant.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${tenantInstance?.email}"/>

</div>

 <br />


</div>

<!--<div class="fieldcontain ${hasErrors(bean: tenantInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="tenant.photo.label" default="Photo" />
		
	</label>
	<input type="file" id="photo" name="photo" />

</div>-->


