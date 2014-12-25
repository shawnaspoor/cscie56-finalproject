
<%@ page import="com.propertyconnection.Landlord" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'landlord.label', default: 'Landlord')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<div id="show-landlord" class="content scaffold-show" role="main">
			<h3>Profile for ${landlordInstance.loginId}</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list landlord">
			
				<g:if test="${landlordInstance?.loginId}">
				<li class="fieldcontain">
					<span id="loginId-label" class="property-label"><g:message code="landlord.loginId.label" default="Login Id" /></span>
					
						<span class="property-value" aria-labelledby="loginId-label"><g:fieldValue bean="${landlordInstance}" field="loginId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${landlordInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="landlord.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"> <g:hiddenField name="password" bean="${landlordInstance}" field="password"/>*****</span>
					
				</li>
				</g:if>
			
				<g:if test="${landlordInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="landlord.dateCreated.label" default="Member Since" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${landlordInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${landlordInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="landlord.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${landlordInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${landlordInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="landlord.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${landlordInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${landlordInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="landlord.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${landlordInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${landlordInstance?.homes}">
				<li class="fieldcontain">
					<span id="homes-label" class="property-label"><g:message code="landlord.homes.label" default="Homes" /></span>
					
						<g:each in="${landlordInstance.homes}" var="h">
						<span class="property-value" aria-labelledby="homes-label"><g:link controller="home" action="show" id="${h.id}">${h?.propertyTitle.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			

			
				<g:if test="${landlordInstance?.photo}">
				<li class="fieldcontain">
					<span id="photo-label" class="property-label"><g:message code="landlord.photo.label" default="Photo" /></span>
					
				</li>
				</g:if>
			

			</ol>
			<g:form url="[resource:landlordInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${landlordInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
