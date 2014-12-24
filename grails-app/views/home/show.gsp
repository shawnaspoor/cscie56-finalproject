
<%@ page import="com.propertyconnection.Home" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'home.label', default: 'Home')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

		<div id="show-home" class="content scaffold-show" role="main">
			<h3>Details for ${homeInstance.propertyTitle}</h3>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list home">
			
				<g:if test="${homeInstance?.propertyTitle}">
				<li class="fieldcontain">
					<span id="propertyTitle-label" class="property-label"><g:message code="home.propertyTitle.label" default="Property Title" /></span>
					
						<span class="property-value" aria-labelledby="propertyTitle-label"><g:fieldValue bean="${homeInstance}" field="propertyTitle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.streetAddress}">
				<li class="fieldcontain">
					<span id="streetAddress-label" class="property-label"><g:message code="home.streetAddress.label" default="Street Address" /></span>
					
						<span class="property-value" aria-labelledby="streetAddress-label"><g:fieldValue bean="${homeInstance}" field="streetAddress"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="home.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${homeInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="home.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${homeInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.zipcode}">
				<li class="fieldcontain">
					<span id="zipcode-label" class="property-label"><g:message code="home.zipcode.label" default="Zipcode" /></span>
					
						<span class="property-value" aria-labelledby="zipcode-label"><g:fieldValue bean="${homeInstance}" field="zipcode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.bedrooms}">
				<li class="fieldcontain">
					<span id="bedrooms-label" class="property-label"><g:message code="home.bedrooms.label" default="Bedrooms" /></span>
					
						<span class="property-value" aria-labelledby="bedrooms-label"><g:fieldValue bean="${homeInstance}" field="bedrooms"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.baths}">
				<li class="fieldcontain">
					<span id="baths-label" class="property-label"><g:message code="home.baths.label" default="Baths" /></span>
					
						<span class="property-value" aria-labelledby="baths-label"><g:fieldValue bean="${homeInstance}" field="baths"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.photo}">
				<li class="fieldcontain">
					<span id="photo-label" class="property-label"><g:message code="home.photo.label" default="Photo" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.tenant}">
				<li class="fieldcontain">
					<span id="tenant-label" class="property-label"><g:message code="home.tenant.label" default="Tenant" /></span>
					
						<span class="property-value" aria-labelledby="tenant-label"><g:fieldValue bean="${homeInstance}" field="tenant.firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${homeInstance?.serviceOrders}">
				<li class="fieldcontain">
					<span id="serviceOrders-label" class="property-label"><g:message code="home.serviceOrders.label" default="Service Orders" /></span>
					    <p>Put in a link to the service order listing page</p>
						<!--<g:each in="${homeInstance.serviceOrders}" var="s">
						<span class="property-value" aria-labelledby="serviceOrders-label"><g:link controller="serviceOrder" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>-->
					
				</li>
				</g:if>
			
				<!--<g:if test="${homeInstance?.landlord}">
				<li class="fieldcontain">
					<span id="landlord-label" class="property-label"><g:message code="home.landlord.label" default="Landlord" /></span>
					
						<span class="property-value" aria-labelledby="landlord-label"><g:link controller="landlord" action="show" id="${homeInstance?.landlord?.id}">${homeInstance?.landlord?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>-->
			
				<g:if test="${homeInstance?.payments}">
				<li class="fieldcontain">
					<span id="payments-label" class="property-label"><g:message code="home.payments.label" default="Payments" /></span>
                    <p>Put in a link to the payment listing page</p>
						<!--<g:each in="${homeInstance.payments}" var="p">
						<span class="property-value" aria-labelledby="payments-label"><g:link controller="payment" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>-->
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:homeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${homeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
