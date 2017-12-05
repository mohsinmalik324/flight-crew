<%@ page import="java.util.List" %>
<%@ page import="org.flightcrew.utils.DBUtils" %>
<%@ page import="org.flightcrew.utils.MyUtils" %>
<%@ page import="org.flightcrew.beans.Airport" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<style>
			.middle {
				width: 350px;
				margin: auto;
				text-align: center;
			}
			
			#origin, #dest, #dept-date, #ret-date, #num-people {
				margin-bottom: 10px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		
		<div class="middle">
			<%
			String err = request.getParameter("err");
			if(err != null) {
				String errMsg = null;
				if(err.equals("1")) {
					errMsg = "Origin and Destination can't be the same.";
				} else if(err.equals("2")) {
					errMsg = "Return Date must come after Departure Date.";
				}
				if(errMsg != null) {
					out.println("<p class='alert alert-danger' id='errMsg'>" + errMsg + "</p>");
				}
			}
			String origin = request.getParameter("origin");
			String dest = request.getParameter("dest");
			String deptDate = request.getParameter("dept-date");
			String retDate = request.getParameter("ret-date");
			String numPeopleString = request.getParameter("num-people");
			if(origin == null && dest == null && deptDate == null && retDate == null && numPeopleString == null) {%>
				<form method="get" action="${pageContext.request.contextPath}/">
					<label for="origin">Origin:</label>
					<select class="form-control" id="origin" name="origin">
						<%
						List<Airport> airports = DBUtils.getAirports(MyUtils.getStoredConnection(request));
						for(Airport airport : airports) {
							String display = "(" + airport.getId() + ") " + airport.getName() + " - " + airport.getCity() + ", " + airport.getCountry();
							out.println("<option>" + display + "</option>");
						}
						%>
					</select>
					<label for="dest">Destination:</label>
					<select class="form-control" id="dest" name="dest">
						<%
						for(Airport airport : airports) {
							String display = "(" + airport.getId() + ") " + airport.getName() + " - " + airport.getCity() + ", " + airport.getCountry();
							out.println("<option>" + display + "</option>");
						}
						%>
					</select>
					<label for="dept-date">Departure Date:</label>
					<input id="dept-date" name="dept-date" type="date"><br>
					<label for="ret-date">Returning Date:</label>
					<input id="ret-date" name="ret-date" type="date"><br>
					<label for="num-people">Number of People</label>
					<select class="form-control" id="num-people" name="num-people">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
					</select>
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			<%} else {
				if(origin.equals(dest)) {
					response.sendRedirect("home?err=1");
					return;
				}
				System.out.println(retDate);
			}
			%>
		</div>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>