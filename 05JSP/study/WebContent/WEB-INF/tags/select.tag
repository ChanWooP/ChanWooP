<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag dynamic-attributes="optionMap"%>
<%@ attribute name="name" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<select name="${name}">
	<c:forEach var="o" items="${optionMap}">
		<option value="${o.key}">${o.value}</option>
	</c:forEach>
</select>