
<%@ page import="com.propertyconnection.ServiceOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'serviceOrder.label', default: 'ServiceOrder')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-serviceOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-serviceOrder" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list serviceOrder">
			
				<g:if test="${serviceOrderInstance?.homes}">
				<li class="fieldcontain">
					<span id="homes-label" class="property-label"><g:message code="serviceOrder.homes.label" default="Homes" /></span>
					
						<span class="property-value" aria-labelledby="homes-label"><g:link controller="home" action="show" id="${serviceOrderInstance?.homes?.id}">${serviceOrderInstance?.homes?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOrderInstance?.landlord}">
				<li class="fieldcontain">
					<span id="landlord-label" class="property-label"><g:message code="serviceOrder.landlord.label" default="Landlord" /></span>
					
						<span class="property-value" aria-labelledby="landlord-label"><g:link controller="landlord" action="show" id="${serviceOrderInstance?.landlord?.id}">${serviceOrderInstance?.landlord?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOrderInstance?.tenants}">
				<li class="fieldcontain">
					<span id="tenants-label" class="property-label"><g:message code="serviceOrder.tenants.label" default="Tenants" /></span>
					
						<span class="property-value" aria-labelledby="tenants-label"><g:link controller="tenant" action="show" id="${serviceOrderInstance?.tenants?.id}">${serviceOrderInstance?.tenants?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOrderInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="serviceOrder.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${serviceOrderInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOrderInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="serviceOrder.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${serviceOrderInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOrderInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="serviceOrder.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${serviceOrderInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOrderInstance?.photo}">
				<li class="fieldcontain">
					<span id="photo-label" class="property-label"><g:message code="serviceOrder.photo.label" default="Photo" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:serviceOrderInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${serviceOrderInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
