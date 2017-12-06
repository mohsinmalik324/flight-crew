<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Collections" %>
<%@ page import="org.flightcrew.utils.DBUtils" %>
<%@ page import="org.flightcrew.utils.MyUtils" %>
<%@ page import="org.flightcrew.beans.*" %>
<%@ page import="java.sql.Connection" %>
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
			
			.flight {
				background-color: #e6e6e6;
				border-radius: 5px;
				border-style: solid;
				padding: 10px;
				margin-bottom: 5px;
			}
			
			#airline {
				font-size: 20px;
				font-weight: bold;
			}
			
			#flightNo {
				font-weight: bold;
				font-size: 18px;
				margin-top: -10px;
			}
			
			.leg-number {
				text-decoration: underline;
				font-weight: bold;
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
				} else if(err.equals("3")) {
					errMsg = "Departure Date must be specified.";
				}
				if(errMsg != null) {
					out.println("<p class='alert alert-danger' id='errMsg'>" + errMsg + "</p>");
				}
			}
			String origin = request.getParameter("origin");
			String dest = request.getParameter("dest");
			String deptDateString = request.getParameter("dept-date");
			String retDateString = request.getParameter("ret-date");
			String numPeopleString = request.getParameter("num-people");
			// Check if none of the fields are available.
			if(origin == null && dest == null && deptDateString == null && retDateString == null && numPeopleString == null) {%>
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
				
				// Get departure date calendar object.
				Calendar deptDate = null;
				if(deptDateString != null && !deptDateString.equals("")) {
					String[] deptDateStringSplit = deptDateString.split("-");
					deptDate = Calendar.getInstance();
					deptDate.set(Calendar.YEAR, Integer.valueOf(deptDateStringSplit[0]));
					deptDate.set(Calendar.MONTH, Integer.valueOf(deptDateStringSplit[1]));
					deptDate.set(Calendar.DAY_OF_MONTH, Integer.valueOf(deptDateStringSplit[2]));
				}
				
				// Get return date calendar object.
				Calendar retDate = null;
				if(retDateString != null && !retDateString.equals("")) {
					String[] retDateStringSplit = retDateString.split("-");
					retDate = Calendar.getInstance();
					retDate.set(Calendar.YEAR, Integer.valueOf(retDateStringSplit[0]));
					retDate.set(Calendar.MONTH, Integer.valueOf(retDateStringSplit[1]));
					retDate.set(Calendar.DAY_OF_MONTH, Integer.valueOf(retDateStringSplit[2]));
				}
				
				if(deptDate == null) {
					response.sendRedirect("home?err=3");
					return;
				}
				if(deptDate != null && retDate != null && deptDate.after(retDate)) {
					response.sendRedirect("home?err=2");
					return;
				}
				
				// Error checking done, generate flight list below.
				
				String originID = origin.substring(0, 5).substring(1).substring(0, 3);
				String destID = dest.substring(0, 5).substring(1).substring(0, 3);
				
				//out.println(originID + " " + destID + " " + deptDateString);
				
				Map<Flight, List<Leg>> flightsAndLegs = DBUtils.getFlightsAndLegs(MyUtils.getStoredConnection(request), originID, destID, deptDateString);
				
				Connection conn = MyUtils.getStoredConnection(request);
				for(Flight flight : flightsAndLegs.keySet()) {
					List<Leg> legs = flightsAndLegs.get(flight);
					Collections.sort(legs);
					String airlineName = DBUtils.getAirline(conn, flight.getAirlineID()).getName();
					int flightNo = flight.getFlightNumber();
					
					out.println("<div class='flight'>");
					out.println("<p id='airline'>" + airlineName + "</p>");
					out.println("<p id='flightNo'>Flight " + flightNo + "</p>");
					int legNum = 1;
					for(Leg leg : legs) {
						Airport from = DBUtils.getAirport(conn, leg.getDepAirportID());
						Airport to = DBUtils.getAirport(conn, leg.getArrAirportID());
						
						out.println("<div class='leg'>");
						out.println("<p class='leg-number'>Leg " + legNum++ + "</p>");
						out.print("<p>From: " + from.getName() + "<br>To: " + to.getName() + "<br>");
						String depTime = leg.getDepTime().replace("-", "/");
						depTime = depTime.substring(0, depTime.length() - 2);
						String arrTime = leg.getArrTime().replace("-", "/");
						arrTime = arrTime.substring(0, arrTime.length() - 2);
						out.println("Departure Time: " + depTime + "<br>Arrival Time: " + arrTime + "</p>");
						out.println("</div>");
					}
					out.println("</div>");
				}
			}
			%>
		</div>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>