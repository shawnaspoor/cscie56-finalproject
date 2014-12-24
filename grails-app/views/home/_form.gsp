<%@ page import="com.propertyconnection.Home" %>



<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'propertyTitle', 'error')} required">
	<label for="propertyTitle">
		<g:message code="home.propertyTitle.label" default="Property Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="propertyTitle" maxlength="30" required="" value="${homeInstance?.propertyTitle}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'streetAddress', 'error')} required">
	<label for="streetAddress">
		<g:message code="home.streetAddress.label" default="Street Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="streetAddress" required="" value="${homeInstance?.streetAddress}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="home.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${homeInstance?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="home.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${homeInstance?.state}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'zipcode', 'error')} required">
	<label for="zipcode">
		<g:message code="home.zipcode.label" default="Zipcode" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="zipcode" required="" value="${homeInstance?.zipcode}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'bedrooms', 'error')} required">
	<label for="bedrooms">
		<g:message code="home.bedrooms.label" default="Bedrooms" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="bedrooms" type="number" value="${homeInstance.bedrooms}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'baths', 'error')} required">
	<label for="baths">
		<g:message code="home.baths.label" default="Baths" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="baths" type="number" value="${homeInstance.baths}" required=""/>

</div>

<!--div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="home.photo.label" default="Photo" />
    </label>

	 <input type="file" id="photo" name="photo" />

</div>-->

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'tenant', 'error')} ">
	<label for="tenant">
		<g:message code="home.tenant.label" default="Tenant" />
		
	</label>
	<g:select id="tenant" name="tenant.id" from="${com.propertyconnection.Tenant.list()}" optionKey="id" value="${homeInstance?.tenant?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<!--<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'serviceOrders', 'error')} ">
	<label for="serviceOrders">
		<g:message code="home.serviceOrders.label" default="Service Orders" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${homeInstance?.serviceOrders?}" var="s">
    <li><g:link controller="serviceOrder" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="serviceOrder" action="create" params="['home.id': homeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'serviceOrder.label', default: 'ServiceOrder')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'landlord', 'error')} required">
	<label for="landlord">
		<g:message code="home.landlord.label" default="Landlord" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="landlord" name="landlord.id" from="${com.propertyconnection.Landlord.list()}" optionKey="id" required="" value="${homeInstance?.landlord?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: homeInstance, field: 'payments', 'error')} ">
	<label for="payments">
		<g:message code="home.payments.label" default="Payments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${homeInstance?.payments?}" var="p">
    <li><g:link controller="payment" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="payment" action="create" params="['home.id': homeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'payment.label', default: 'Payment')])}</g:link>
</li>
</ul>


</div>-->

