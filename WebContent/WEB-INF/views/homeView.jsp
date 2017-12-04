<%@ page import="java.util.List" %>
<%@ page import="org.flightcrew.utils.DBUtils" %>
<%@ page import="org.flightcrew.utils.MyUtils" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<style>
			.middle {
				width: 250px;
				margin: auto;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		
		<div class="middle">
			<form>
				<label for="sel1">Origin:</label>
				<select class="form-control" id="origin">
					<%
					List<String> airports = DBUtils.getAirports(MyUtils.getStoredConnection(request));
					for(String airport : airports) {
						out.println("<option>" + airport + " ()</option>");
					}
					%>
				</select>
				<label for="sel1">Destination:</label>
				<select class="form-control" id="dest">
					<%
					for(String airport : airports) {
						out.println("<option>" + airport + "</option>");
					}
					%>
				</select>
				<button type="submit" class="btn btn-default">Search</button>
			</form>
		</div>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>