
<%@ page import="com.propertyconnection.ServiceOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'serviceOrder.label', default: 'ServiceOrder')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-serviceOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-serviceOrder" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="serviceOrder.homes.label" default="Homes" /></th>
					
						<th><g:message code="serviceOrder.landlord.label" default="Landlord" /></th>
					
						<th><g:message code="serviceOrder.tenants.label" default="Tenants" /></th>
					
						<g:sortableColumn property="description" title="${message(code: 'serviceOrder.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'serviceOrder.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'serviceOrder.location.label', default: 'Location')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${serviceOrderInstanceList}" status="i" var="serviceOrderInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${serviceOrderInstance.id}">${fieldValue(bean: serviceOrderInstance, field: "homes")}</g:link></td>
					
						<td>${fieldValue(bean: serviceOrderInstance, field: "landlord")}</td>
					
						<td>${fieldValue(bean: serviceOrderInstance, field: "tenants")}</td>
					
						<td>${fieldValue(bean: serviceOrderInstance, field: "description")}</td>
					
						<td><g:formatDate date="${serviceOrderInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: serviceOrderInstance, field: "location")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${serviceOrderInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
