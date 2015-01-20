<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-employee-top.jsp" />

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
      
      <div class="form-group">
        <h3>Reset password for: <font color="blue">${customer} </font> </h3>
        <br>
        <label>New Password</label>
        <input class="form-control" type="password" placeholder="Case sensitive field">
        </div>
      <div class="form-group">
        <label>Confirm Password</label>
        <input class="form-control" type="password" placeholder="Case sensitive field">
      </div>
      <button type="submit" name="action2" value="reset" class="btn btn-default">Set New Password</button>
      <p><br>
      </p>
      
<jsp:include page="template-buttom.jsp" />