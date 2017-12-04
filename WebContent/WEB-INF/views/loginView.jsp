<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>

		<style>
			.form-signin {
			    max-width: 280px;
			    padding: 15px;
			    margin: 0 auto;
			      margin-top:50px;
			  }
			  .form-signin .form-signin-heading, .form-signin {
			    margin-bottom: 10px;
			  }
			  .form-signin .form-control {
			    position: relative;
			    font-size: 16px;
			    height: auto;
			    padding: 10px;
			    -webkit-box-sizing: border-box;
			    -moz-box-sizing: border-box;
			    box-sizing: border-box;
			  }
			  .form-signin .form-control:focus {
			    z-index: 2;
			  }
			  .form-signin input[type="text"] {
			    margin-bottom: -1px;
			    border-bottom-left-radius: 0;
			    border-bottom-right-radius: 0;
			    border-top-style: solid;
			    border-right-style: solid;
			    border-bottom-style: none;
			    border-left-style: solid;
			    border-color: #000;
			  }
			  .form-signin input[type="password"] {
			    margin-bottom: 10px;
			    border-top-left-radius: 0;
			    border-top-right-radius: 0;
			    border-top-style: none;
			    border-right-style: solid;
			    border-bottom-style: solid;
			    border-left-style: solid;
			    border-color: rgb(0,0,0);
			    border-top:1px solid rgba(0,0,0,0.08);
			  }
			  .form-signin-heading {
			    color: #000;
			    text-align: center;
			  }
			  .forgot-password {
				text-decoration: underline;
				color: #888;
			  }
			  .forgot-password:hover,
			  .forgot-password:focus {
			 	text-decoration: underline;
			  	color: #666;
			  }
		</style>
		
		
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