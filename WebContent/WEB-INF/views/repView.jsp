<%@ page import="org.flightcrew.beans.*" %>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<jsp:include page="style.css"></jsp:include>
		<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		<h1>Account</h1>
		<h3>Hello <b>${loginedUser.userName}</b></h3>
		<p id="rep-err-msg">${modify_cust_error}</p>
		<p id="rep-suc-msg">${modify_cust_success}</p>
		<%		
			session.removeAttribute("modify_cust_error");
			session.removeAttribute("modify_cust_success");
		%>
		
		<div class="container">
		
		  <h2>Record a reservation</h2>      
		  <table class="table table-striped">
			<div class="row">
  				<div class="col-sm-4" style="text-align: right;">
					<label id="res-date">Reservation Date: </label><br>
					<label id="book-fee">Booking Fee: </label><br>
					<label id="tot-fare">Total Fare: </label><br>
					<label id="rep-id">Representative ID: </label><br>
					<label id="acc-no3">Account Number: </label><br>
					<label id="air-id">Airline ID: </label><br>
					<label id="flight-no">Flight No: </label><br>
					<label id="leg-no">Leg Number: </label><br>
					<label id="flight-date">Flight Date: </label><br>
  				</div>
  				<div class="col-sm-8">
					<form method="post" action="${pageContext.request.contextPath}/account">
					<input id="res-date-in" name="res-date-in" type="date"><br>
					<input id="book-fee-in" name="book-fee-in" type="text"><br>
					<input id="tot-fare-in" name="tot-fare-in" type="text"><br>
					<input id="rep-id-in" name="rep-id-in" type="text"><br>
					<input id="acc-no3-in" name="acc-no3-in" type="text"><br>
					<input id="air-id-in" name="air-id-in" type="text"><br>
					<input id="flight-no-in" name="flight-no-in" type="text"><br>
					<input id="leg-no-in" name="leg-no-in" type="text"><br>
					<input id="flight-date-in" name="flight-date-in" type="date"><br><br>
					<button type="submit" id="acc-mod-submit" name="res-submit" class="btn btn-default">Record Reservation</button><br><br><br>
					</form>
  				</div>
			</div>
		  </table>
		
		  <h2>Modify customer information</h2>      
		  <table class="table table-striped">
		  
			<%
			Customer cust = (Customer)session.getAttribute("customer");
			if(cust != null) {
				session.setAttribute("savedCustomer", cust);
			%>
			<div class="row">
  				<div class="col-sm-4" style="text-align: right;">
					<label for="acc-id">ID: </label><br>
					<label for="acc-no2">Account Number: </label><br>
					<label for="acc-cc">Credit Card No: </label><br>
					<label for="acc-email">Email: </label><br>
					<label for="acc-uname">Username: </label><br>
					<label for="acc-pword">Password: </label><br>
  				</div>
  				<div class="col-sm-8">
					<form method="post" action="${pageContext.request.contextPath}/account">
					<input id="acc-id-in" name="acc-id-in" type="text" placeholder=${customer.id}><br>
					<input id="acc-no2" name="acc-no2" type="text" placeholder=${customer.accountNo}><br>
					<input id="acc-cc-in" name="acc-cc-in" type="text" placeholder=${customer.creditCardNo}><br>
					<input id="acc-email-in" name="acc-email-in" type="text" placeholder=${customer.email}><br>
					<input id="acc-uname-in" name="acc-uname-in" type="text" placeholder=${customer.username}><br>
					<input id="acc-pword-in" name="acc-pword-in" type="password" placeholder=${customer.password}><br><br>
					<button type="submit" id="acc-mod-submit" class="btn btn-default">Submit Changes</button><br><br><br>
					</form>
  				</div>
			</div>
			
			<%
				request.getSession().removeAttribute("customer");
			}
			%>
			<form method="post" action="${pageContext.request.contextPath}/account">
				<label for="acc-no">Account Number: </label>
				<input id="acc-no-input" name="acc-no-input" type="text">
				<button type="submit" id="acc-no-submit" class="btn btn-default">Search</button>
			</form>
		  </table>
		
		  <h2>Mailing List</h2>      
		  <table class="table table-striped">
			<%
			if(request.getParameter("gen-ml-submit") != null) {
			%>
			${mailingList}<br>
			<%
				request.getSession().removeAttribute("mailingList");
	        }
			%>
			
			<form method="post" action="${pageContext.request.contextPath}/account">
				<button type="submit" name ="gen-ml-submit" id="gen-ml-submit" class="btn btn-default">Generate Mailing List</button>
			</form>
		  </table>
		
		  <h2>Flight Suggestions</h2>      
		  <table class="table table-striped">
		  	<%
			if(request.getParameter("gen-flsuggestions-submit") != null) {
			%>
			${flightSuggestions}<br>
			<%
				request.getSession().removeAttribute("flightSuggestions");
	        }
			%>
			<form method="post" action="${pageContext.request.contextPath}/account">
				<label for="acc-no7">Account Number: </label>
				<input id="acc-no-input7" name="acc-no-input7" type="text">
				<button type="submit" name ="gen-flsuggestions-submit" id="gen-flsuggestions-submit" class="btn btn-default">Generate Flight Suggestions</button>
			</form>
		  </table>
  
		</div>
		
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>