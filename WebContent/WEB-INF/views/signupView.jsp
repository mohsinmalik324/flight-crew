<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<style>
			.form-signin {
				max-width: 280px;
				padding: 15px;
				margin: 0 auto;
				margin-top: 0px;
			}
			
			.form-signin .form-signin-heading,
			.form-signin {
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
			
			.form-signin input[name="fname"] {
				margin-bottom: -1px;
				border-bottom-left-radius: 0;
				border-bottom-right-radius: 0;
				border-top-style: solid;
				border-right-style: solid;
				border-bottom-style: none;
				border-left-style: solid;
				border-color: #000;
			}
			
			.form-signin input[middle] {
				margin-bottom: 0px;
				border-radius: 0px;
				border-top-style: none;
				border-right-style: solid;
				border-bottom-style: none;
				border-left-style: solid;
				border-color: rgb(0, 0, 0);
				border-top: 1px solid rgba(0, 0, 0, 0.08);
			}
			
			.form-signin input[name="cpassword"] {
				margin-bottom: 10px;
				border-top-left-radius: 0;
				border-top-right-radius: 0;
				border-top-style: none;
				border-right-style: solid;
				border-bottom-style: solid;
				border-left-style: solid;
				border-color: rgb(0, 0, 0);
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
			
			#errMsg {
				text-align: center;
				margin-bottom: -20px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		<%
		String err = request.getParameter("err");
		if(err != null) {
			String errMsg = null;
			if(err.equals("1")) {
				errMsg = "Zipcode must be a positive integer.";
			} else if(err.equals("2")) {
				errMsg = "Passwords do not match.";
			} else if(err.equals("3")) {
				errMsg = "An unexpected error occured. Try again.";
			} else if(err.equals("4")) {
				errMsg = "That user already exists.";
			}
			if(errMsg != null) {
				out.println("<p class='alert alert-danger' id='errMsg'>" + errMsg + "</p>");
			}
		}
		%>
		<div class="container">
			<form method="POST" action="${pageContext.request.contextPath}/signup" class="form-signin">
				<h1 class="form-signin-heading text-muted">Signup</h1>
				
				<input name="fname" type="text" class="form-control" placeholder="First Name" required autofocus>
				<input name="lname" type="text" class="form-control" placeholder="Last Name" required middle>
				<input name="address" type="text" class="form-control" placeholder="Address" required middle>
				<input name="city" type="text" class="form-control" placeholder="City" required middle>
				<input name="state" type="text" class="form-control" placeholder="State" required middle>
				<input name="zipcode" type="text" class="form-control" placeholder="Zip Code" required middle>
				<input name="email" type="text" class="form-control" placeholder="Email" required middle>
				<input name="username" type="text" class="form-control" placeholder="Username" required middle>
				<input name="password" type="password" class="form-control" placeholder="Password" required middle>
				<input name="cpassword" type="password" class="form-control" placeholder="Confirm Password" required>
				
				<button class="btn btn-lg btn-primary btn-block" type="submit" value="Submit">
					Sign Up
				</button>
			</form>
			<div class="text-center">
				<a href="${pageContext.request.contextPath}/signup" tabindex="5" class="forgot-password">Login</a>
			</div>
		</div>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>