<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<jsp:include page="style.css"></jsp:include>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		
		<div class="container">
			<p style="color: red; text-align: center;">${errorString}</p>
			<form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin">
				<h1 class="form-signin-heading text-muted">Log In</h1>
				<input type="text" name="Username" class="form-control" placeholder="Username" required="" autofocus="">
				<input type="password" name="Password" class="form-control" placeholder="Password" required="">
				<button class="btn btn-lg btn-primary btn-block" type="submit" value="Submit">
					Log In
				</button>
			</form>
			<div class="text-center">
				<a href="${pageContext.request.contextPath}/signup" tabindex="5" class="forgot-password">Create Account</a>
			</div>
		</div>    
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>