
<%@ page import="com.propertyconnection.Tenant" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tenant.label', default: 'Tenant')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<div id="show-tenant" class="content scaffold-show" role="main">
			<h3>Profile for: ${tenantInstance.loginId}</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tenant">
			
				<g:if test="${tenantInstance?.loginId}">
				<li class="fieldcontain">
					<span id="loginId-label" class="property-label"><g:message code="tenant.loginId.label" default="Login Id" /></span>
					
						<span class="property-value" aria-labelledby="loginId-label"><g:fieldValue bean="${tenantInstance}" field="loginId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="tenant.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${tenantInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="tenant.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${tenantInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="tenant.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${tenantInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="tenant.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${tenantInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="tenant.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${tenantInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.homes}">
				<li class="fieldcontain">
					<span id="homes-label" class="property-label"><g:message code="tenant.homes.label" default="Homes" /></span>
					
						<span class="property-value" aria-labelledby="homes-label"><g:fieldValue bean="${tenantInstance}" field="homes.propertyTitle" /> </span>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.landlord}">
				<li class="fieldcontain">
					<span id="landlord-label" class="property-label"><g:message code="tenant.landlord.label" default="Landlord" /></span>
					
						<span class="property-value" aria-labelledby="landlord-label"><g:fieldValue bean="${tenantInstance}" field="landlord.loginId" /></span>
					
				</li>
				</g:if>
			<!--
				<g:if test="${tenantInstance?.messages}">
				<li class="fieldcontain">
					<span id="messages-label" class="property-label"><g:message code="tenant.messages.label" default="Messages" /></span>
					
						<g:each in="${tenantInstance.messages}" var="m">
						<span class="property-value" aria-labelledby="messages-label"><g:link controller="message" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.serviceOrders}">
				<li class="fieldcontain">
					<span id="serviceOrders-label" class="property-label"><g:message code="tenant.serviceOrders.label" default="Service Orders" /></span>
					
						<g:each in="${tenantInstance.serviceOrders}" var="s">
						<span class="property-value" aria-labelledby="serviceOrders-label"><g:link controller="serviceOrder" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.payments}">
				<li class="fieldcontain">
					<span id="payments-label" class="property-label"><g:message code="tenant.payments.label" default="Payments" /></span>
					
						<g:each in="${tenantInstance.payments}" var="p">
						<span class="property-value" aria-labelledby="payments-label"><g:link controller="payment" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${tenantInstance?.photo}">
				<li class="fieldcontain">
					<span id="photo-label" class="property-label"><g:message code="tenant.photo.label" default="Photo" /></span>
					
				</li>
				</g:if>
			-->

			
			</ol>
			<g:form url="[resource:tenantInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tenantInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
