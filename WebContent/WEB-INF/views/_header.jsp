<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/homeLink">Flight Crew</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${pageContext.request.contextPath}/homeLink">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/reverseAuction">Reverse Auction</a></li>
			<li><a href="${pageContext.request.contextPath}/account">My Account</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%  Object a = request.getSession().getAttribute("loginedUser");
				if(a != null) { %>
				<!-- <li style="color: #CCC">Logged in as ${loginedUser.userName}. </li>  -->
				<li> <a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        		
			<% } %>
			<% if(a == null) { %>
				<li><a href="${pageContext.request.contextPath}/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
			<% } %>
			
		</ul>
	</div>
</nav>