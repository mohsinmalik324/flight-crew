<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Collections" %>
<%@ page import="org.flightcrew.utils.DBUtils" %>
<%@ page import="org.flightcrew.utils.MyUtils" %>
<%@ page import="org.flightcrew.beans.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<jsp:include page="style.css"></jsp:include>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		
		<div class="middle">
			<%
		  
			if(request.getSession().getAttribute("isReverseAuction") != null) {
				String infoMsg = "Enter your flight details and name your price.";
				out.println("<p class='alert alert-info' id='errMsg'>" + "<b>Reverse Auction</b><br>" + infoMsg + "</p>");
    			request.getSession().setAttribute("isReverseAuction2", 1);
			} 
			String err = request.getParameter("err");
			if(err != null) {
				String errMsg = null;
				if(err.equals("1")) {
					errMsg = "Origin and Destination can't be the same.";
				} else if(err.equals("2")) {
					errMsg = "Return Date must come after Departure Date.";
				} else if(err.equals("3")) {
					errMsg = "Departure Date must be specified.";
				} else if(err.equals("4")) {
					errMsg = "Requested price must be in valid American currency format. (ie. $x,xxx.xx, $xxxx.xx, $xxxx).";
				}
				if(errMsg != null) {
					out.println("<p class='alert alert-danger' id='errMsg'>" + errMsg + "</p>");
				}
			}
			String rtString = request.getParameter("rt");
			boolean rt = rtString == null ? false : rtString.equals("1");
			String lpString = request.getParameter("lp");
			boolean lp = lpString == null ? false : lpString.equals("1");
			if(lp) {
				String numPeopleString = request.getParameter("num-people");
				int numPeople = 0;
				try {
					numPeople = Integer.valueOf(numPeopleString);
				} catch(NumberFormatException e) {
					response.sendRedirect("home");
					return;
				}
				if(request.getSession().getAttribute("isReverseAuction3") != null) {
					String infoMsg = "Now enter passengers information.";
					out.println("<p class='alert alert-info' id='errMsg'>" + "<b>Reverse Auction</b><br>" + infoMsg + "</p>");
				} 
				out.println("<form method='post' action='home'>");
				for(int i = 1; i <= numPeople; i++) {
					out.println("<div class='person'>");
					out.println("<p class='person-text'>Person " + i + "</p>");
					if(i == 1) {
						out.println("<div class='checkbox checkbox-self'><input type='checkbox' name='include-self' checked> Tick if you want yourself as Person 1 (no need to fill info for Person 1 if ticked)</div>");
					}
					out.println("<div class='form-group'><input required type='text' name='fname-" + i + "' placeholder='First Name'></div>");
					out.println("<div class='form-group'><input required type='text' name='lname-" + i + "' placeholder='Last Name'></div>");
					out.println("<div class='form-group'><input required type='text' name='address-" + i + "' placeholder='Address'></div>");
					out.println("<div class='form-group'><input required type='text' name='city-" + i + "' placeholder='City'></div>");
					out.println("<div class='form-group'><input required type='text' name='state-" + i + "' placeholder='State'></div>");
					out.println("<div class='form-group'><input required type='text' name='zip-" + i + "' placeholder='Zip Code'></div>");
					out.println("<label>Meal:</label><br><select class='form-group' name='meal-" + i + "'><option>Chips</option><option>Fish and Chips</option><option>Sushi</option></select>");
					
					ArrayList<Double> fares = (ArrayList<Double>)request.getSession().getAttribute("fares");
					if(fares != null) {
						StringBuilder options = new StringBuilder();
						Double custNyop = fares.get(3);
						if(fares.get(2) != null && custNyop >= fares.get(2))
							options.append("<option>First</option>");
						if(fares.get(1) != null && custNyop >= fares.get(1))
							options.append("<option>Business</option>");
						if(fares.get(0) != null && custNyop >= fares.get(0))
							options.append("<option>Economy</option>");
						out.println("<br><label>Class:</label><br><select class='form-group' name='class-" + i + "'>" + options.toString() + "</select>");
					} else {
						System.out.println("no fares");
						out.println("<br><label>Class:</label><br><select class='form-group' name='class-" + i + "'><option>First</option><option>Business</option><option>Economy</option></select>");
					}
					
					out.println("</div>");
				}
				
				out.println("<br><button id='person-submit' type='submit' class='btn btn-default'>Submit</button>");
				out.println("</form>");
				if(rt) {
						
					session.setAttribute("rtFlightNo", "");
				} else {
					String flightNoString = request.getParameter("flightNo");
					String airlineID = request.getParameter("airlineID");
					String legs = request.getParameter("legs");
					session.setAttribute("flightNo", flightNoString);
					session.setAttribute("airlineID", airlineID);
					session.setAttribute("legs", legs);
				}
				return;
			}
			String origin = request.getParameter("origin");
			String dest = request.getParameter("dest");
			String deptDateString = request.getParameter("dept-date");
			String retDateString = request.getParameter("ret-date");
			String numPeopleString = request.getParameter("num-people");
			String nyopString = request.getParameter("nyop");
			// Check if none of the fields are available.
			if(origin == null && dest == null && deptDateString == null && retDateString == null && numPeopleString == null) {%>
				<form method="post" action="${pageContext.request.contextPath}/">
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
					<label for="num-people">Number of People:</label>
					<select class="form-control" id="num-people" name="num-people">
						<%
						for(int i = 1; i <= 6; i++) {
							out.println("<option>" + i + "</option>");
						}
						%>
					</select>
					
					<%if(request.getSession().getAttribute("isReverseAuction") != null) {
						out.println("<label for='nyop'><br>Name Your Own Price:  $</label>");
						out.println("<input id='nyop' name='nyop' type='date' placeholder='Total price'><br><br>");
       					request.getSession().removeAttribute("isReverseAuction");
					}%>
					
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			<%} else {
				System.out.println(origin);
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
				if(nyopString != null) {
					final String USD_REGEX = "^\\$?\\-?([1-9]{1}[0-9]{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\-?\\$?([1-9]{1}\\d{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\(\\$?([1-9]{1}\\d{0,2}(\\,\\d{3})*(\\.\\d{0,2})?|[1-9]{1}\\d{0,}(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))\\)$";
					Pattern p = Pattern.compile(USD_REGEX);
					Matcher m = p.matcher(nyopString);
					if(!m.matches()) {
						response.sendRedirect("reverseAuction?err=4");
					}
				}
				
				// Error checking done, generate flight list below.
				
				Object user = request.getSession().getAttribute("loginedUser");
				
				// Check if user is logged in.
				if(user == null) {
					out.println("<p class='alert alert-warning'>You are not logged in, so you will not be able to book any reservations. You will still be able to look at flights.</p>");
				}
				
				String originID = origin.substring(0, 5).substring(1).substring(0, 3);
				String destID = dest.substring(0, 5).substring(1).substring(0, 3);
				
				//out.println(originID + " " + destID + " " + deptDateString);
				
				Map<Flight, List<Leg>> flightsAndLegs = DBUtils.getFlightsAndLegs(MyUtils.getStoredConnection(request), originID, destID, deptDateString);
				if(flightsAndLegs.isEmpty()) {
					out.println("<p class='alert alert-info'>No departing flights were found on the specified departure date.</p>");
					out.println("<a href='home'><button type='submit' class='btn btn-primary'>Go Back</button></a>");
					return;
				}
				
				Map<Flight, List<Leg>> retFlightsAndLegs = null;
				if(retDate != null) {
					retFlightsAndLegs = DBUtils.getFlightsAndLegs(MyUtils.getStoredConnection(request), destID, originID, retDateString);
					if(retFlightsAndLegs.isEmpty()) {
						out.println("<p class='alert alert-info'>No returning flights were found on the specified return date.</p>");
						out.println("<a href='home'><button type='submit' class='btn btn-primary'>Go Back</button></a>");
						return;
					}
				}
				
				if(request.getSession().getAttribute("isReverseAuction2") != null && user != null) {
					String infoMsg = "Now select a flight that has accepted your price.";
					out.println("<p class='alert alert-info' id='errMsg'>" + "<b>Reverse Auction</b><br>" + infoMsg + "</p>");
					
    				request.getSession().setAttribute("isReverseAuction3", 1);
				} 
				
				if(rt) {
					out.println("<p class='alert alert-info'>Select a return flight.</p>");
				}
				
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
					if(user != null) {
						String link = null;
						int fleg = legs.get(0).getLegNumber();
						int lleg = legs.get(legs.size() - 1).getLegNumber();
						if(retDate != null) {
							link = "home?rt=1&origin=" + dest + "&dest=" + origin + "&dept-date=" + retDateString + "&ret-date=&num-people=" + numPeopleString;
							if(nyopString != null) {
								link += "&nyop" + nyopString;
							}
							link += "&flightNo=" + flightNo + "&airlineID=" + flight.getAirlineID() + "&legs=" + fleg + "-" + lleg;
						} else {
							// Submitting return flight. Else submitting one-way.
							if(rt) {
								
							} else {
								link = "home?num-people=" + numPeopleString + "&rt=0&lp=1&flightNo=" + flightNo + "&airlineID=" + flight.getAirlineID() + "&legs=" + fleg + "-" + lleg;
							}
						}
						
						if(request.getSession().getAttribute("isReverseAuction2") != null) {
							Double nyop = Double.valueOf(nyopString);
							Double[] fares = DBUtils.getFareForFlight(conn, flight);
							if(nyop <= DBUtils.getSmallestFare(fares)) {
								out.println("<a href='" + link + "'><button class='btn btn-secondary' disabled>Book This Flight</button></a>");
							} else {
								System.out.println("fares arr being stored");
								ArrayList<Double> faresArr = new ArrayList<>(Arrays.asList(fares));
								faresArr.add(nyop);
								session.setAttribute("fares", faresArr);
								out.println("<a href='" + link + "'><button type='submit' class='btn btn-primary'>Book This Flight</button></a>");
							}
						} else {
							out.println("<a href='" + link + "'><button type='submit' class='btn btn-primary'>Book This Flight</button></a>");
						}
					}
					out.println("</div>");
				}
			}
			%>
		</div>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>