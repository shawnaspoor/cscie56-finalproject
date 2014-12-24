<%@ page import="com.propertyconnection.ServiceOrder" %>



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
<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'completed', 'error')} required">
    <label for="completed">
        <g:message code="serviceOrder.completed.label" default="Completed" />
<g:checkBox name="completed" id="completed" checked="${serviceOrderInstance.completed}"/>
</label>
</div>
<br />o
<!--
<div class="fieldcontain ${hasErrors(bean: serviceOrderInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="serviceOrder.photo.label" default="Photo" />
		
	</label>
	<input type="file" id="photo" name="photo" />


</div>-->

