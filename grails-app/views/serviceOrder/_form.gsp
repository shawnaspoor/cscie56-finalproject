<%@ page import="com.propertyconnection.ServiceOrder" %>



<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'homes', 'error')} required">
	<label for="homes">
		<g:message code="serviceOrder.homes.label" default="Homes" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="homes" name="homes.id" from="${com.propertyconnection.Home.list()}" optionKey="id" required="" value="${serviceOrderInstance?.homes?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'landlord', 'error')} required">
	<label for="landlord">
		<g:message code="serviceOrder.landlord.label" default="Landlord" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="landlord" name="landlord.id" from="${com.propertyconnection.Landlord.list()}" optionKey="id" required="" value="${serviceOrderInstance?.landlord?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'tenants', 'error')} required">
	<label for="tenants">
		<g:message code="serviceOrder.tenants.label" default="Tenants" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tenants" name="tenants.id" from="${com.propertyconnection.Tenant.list()}" optionKey="id" required="" value="${serviceOrderInstance?.tenants?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="serviceOrder.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${serviceOrderInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'location', 'error')} required">
	<label for="location">
		<g:message code="serviceOrder.location.label" default="Location" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="location" required="" value="${serviceOrderInstance?.location}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="serviceOrder.photo.label" default="Photo" />
		
	</label>
	<input type="file" id="photo" name="photo" />

</div>

