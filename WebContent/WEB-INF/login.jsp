<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Login Page</title>
    </head>
    
	<body>
	
		<h2>Login</h2>
		
		<c:forEach var="error" items="${errors}">
			<h3 style="color:red"> ${error} </h3>
		</c:forEach>
	
		<form action="login.do" method="POST">
		    <table>
		        <tr>
		            <td style="font-size: x-large">Email:</td>
		            <td>
		                <input type="text" name="email" value="${form.email}" />
		            </td>
		        </tr>
		        <tr>
		            <td style="font-size: x-large">Password:</td>
		            <td><input type="password" name="password" /></td>
		        </tr>
		        <tr>
		            <td colspan="2" align="center">
		                <input type="submit" name="action" value="Login" />
		            </td>
		        </tr>
			</table>
		</form>
	</body>
</html>