<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />
<div>
                 <h4>Successful Transaction!</h4>
                </div>
<c:choose>
		<c:when test="${ (empty msg) }">
		</c:when>
		<c:otherwise>
			<h3 style="color: blue">${msg}</h3>
		</c:otherwise>
	</c:choose>

	<c:forEach var="error" items="${errors}">
		<h3 style="color: red">${error}</h3>
	</c:forEach>
                
                
                     
                     
                                
<jsp:include page="template-buttom.jsp" />