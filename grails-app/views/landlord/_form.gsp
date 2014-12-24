<%@ page import="com.propertyconnection.Landlord" %>



<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'loginId', 'error')} required">
	<label for="loginId">
		<g:message code="landlord.loginId.label" default="Login Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="loginId" maxlength="20" required="" value="${landlordInstance?.loginId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="landlord.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${landlordInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="landlord.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${landlordInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="landlord.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${landlordInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="landlord.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${landlordInstance?.email}"/>

</div>
<!--

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'homes', 'error')} ">
	<label for="homes">
		<g:message code="landlord.homes.label" default="Homes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${landlordInstance?.homes?}" var="h">
    <li><g:link controller="home" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="home" action="create" params="['landlord.id': landlordInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'home.label', default: 'Home')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'tenants', 'error')} ">
	<label for="tenants">
		<g:message code="landlord.tenants.label" default="Tenants" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${landlordInstance?.tenants?}" var="t">
    <li><g:link controller="tenant" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tenant" action="create" params="['landlord.id': landlordInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tenant.label', default: 'Tenant')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'messages', 'error')} ">
	<label for="messages">
		<g:message code="landlord.messages.label" default="Messages" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${landlordInstance?.messages?}" var="m">
    <li><g:link controller="message" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="message" action="create" params="['landlord.id': landlordInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'message.label', default: 'Message')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'serviceOrders', 'error')} ">
	<label for="serviceOrders">
		<g:message code="landlord.serviceOrders.label" default="Service Orders" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${landlordInstance?.serviceOrders?}" var="s">
    <li><g:link controller="serviceOrder" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="serviceOrder" action="create" params="['landlord.id': landlordInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'serviceOrder.label', default: 'ServiceOrder')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'payments', 'error')} ">
	<label for="payments">
		<g:message code="landlord.payments.label" default="Payments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${landlordInstance?.payments?}" var="p">
    <li><g:link controller="payment" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="payment" action="create" params="['landlord.id': landlordInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'payment.label', default: 'Payment')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: landlordInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="landlord.photo.label" default="Photo" />
		
	</label>
	<input type="file" id="photo" name="photo" />

</div>-->



