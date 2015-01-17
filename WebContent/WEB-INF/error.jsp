<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--<jsp:include page="template-top.jsp" />-->

<h2>Error Page</h2>

<c:forEach var="error" items="${errors}">
	<h3 style="color: red">${error}</h3>
</c:forEach>

<p>
	<c:choose>
		<c:when test="${ (empty user) }">
				Click <a href="login.do">here</a> to login.
			</c:when>
	</c:choose>
</p>

<!--<jsp:include page="template-bottom.jsp" />-->
