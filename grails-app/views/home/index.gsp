
<%@ page import="com.propertyconnection.Home" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'home.label', default: 'Home')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-home" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-home" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="propertyTitle" title="${message(code: 'home.propertyTitle.label', default: 'Property Title')}" />
					
						<g:sortableColumn property="streetAddress" title="${message(code: 'home.streetAddress.label', default: 'Street Address')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'home.city.label', default: 'City')}" />
					
						<g:sortableColumn property="state" title="${message(code: 'home.state.label', default: 'State')}" />
					
						<g:sortableColumn property="zipcode" title="${message(code: 'home.zipcode.label', default: 'Zipcode')}" />
					
						<g:sortableColumn property="bedrooms" title="${message(code: 'home.bedrooms.label', default: 'Bedrooms')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${homeInstanceList}" status="i" var="homeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${homeInstance.id}">${fieldValue(bean: homeInstance, field: "propertyTitle")}</g:link></td>
					
						<td>${fieldValue(bean: homeInstance, field: "streetAddress")}</td>
					
						<td>${fieldValue(bean: homeInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: homeInstance, field: "state")}</td>
					
						<td>${fieldValue(bean: homeInstance, field: "zipcode")}</td>
					
						<td>${fieldValue(bean: homeInstance, field: "bedrooms")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${homeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
