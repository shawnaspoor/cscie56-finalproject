
<%@ page import="com.propertyconnection.Tenant" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tenant.label', default: 'Tenant')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tenant" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tenant" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="loginId" title="${message(code: 'tenant.loginId.label', default: 'Login Id')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'tenant.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'tenant.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'tenant.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'tenant.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'tenant.email.label', default: 'Email')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tenantInstanceList}" status="i" var="tenantInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tenantInstance.id}">${fieldValue(bean: tenantInstance, field: "loginId")}</g:link></td>
					
						<td>${fieldValue(bean: tenantInstance, field: "password")}</td>
					
						<td><g:formatDate date="${tenantInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: tenantInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: tenantInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: tenantInstance, field: "email")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tenantInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
