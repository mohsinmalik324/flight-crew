<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<style>
		
		</style>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		<div class="middle">
			<h1>Signup</h1>
			<form method="post" action="signup">
				<div class="form-group">
					<input type="email" class="form-control" id="email" placeholder="Username">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="pwd" placeholder="Password">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="cpwd" placeholder="Confirm Password">
				</div>
				<!--<div class="checkbox">
					<label><input type="checkbox"> Remember me</label>
				</div>-->
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>