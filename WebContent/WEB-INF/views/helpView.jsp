<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
		<style>
			.middle {
				width: 600px;
				margin: auto;
				text-align: center;
			}
			
			.middle ol {
				text-align: left;
			}
		</style>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		<div class="middle">
			<div class="jumbotron">
				<p>To make a reservation, follow these steps:</p>
				<ol>
					<li>Make sure you are logged in. If you are not logged in, <a href="login" target="_blank">click here</a>.</li>
					<li><a href="home" target="_blank">Click here.</a></li>
					<li>Select an Origin Airport.</li>
					<li>Select a Destination Airport.</li>
					<li>Select a Departure Date.</li>
					<li>If this is a round-trip flight, select a Returning Date. Otherwise leave this field blank.</li>
					<li>Select the number of people associated with the reservation.</li>
					<li>Click the Search button.</li>
					<li>Browse the available flights and book a flight by clicking Book This Flight under the appropriate flight.</li>
					<li>If selecting a return flight, perform the previous step again.</li>
					<li>Enter information for all the people associated with this reservation. Tick the box at the top if you want to include yourself. If this box is ticked, you do not have to type any information for Person 1 EXCEPT for meal and class. When finished click the submit button.</li>
					<li>Your flight reservation has now been recorded. Happy travels!</li>
				</ol>
			</div>
			<div class="jumbotron">
				<p>To participate in a reverse auction, follow these steps:</p>
				<ol>
					<li>Make sure you are logged in. If you are not logged in, <a href="login" target="_blank">click here</a>.</li>
					<li><a href="reverseAuction" target="_blank">Click here.</a></li>
					<li>Select an Origin Airport.</li>
					<li>Select a Destination Airport.</li>
					<li>Select a Departure Date.</li>
					<li>If this is a round-trip flight, select a Returning Date. Otherwise leave this field blank.</li>
					<li>Select the number of people associated with the reservation.</li>
					<li>Input your own bid.</li>
					<li>Click the Search button.</li>
					<li>Browse the available flights and book a flight by clicking Book This Flight under the appropriate flight. Note that only flights which meet your bid will have a clickable button.</li>
					<li>If selecting a return flight, perform the previous step again.</li>
					<li>Enter information for all the people associated with this reservation. Tick the box at the top if you want to include yourself. If this box is ticked, you do not have to type any information for Person 1 EXCEPT for meal and class. When finished click the submit button.</li>
					<li>Your flight reservation has now been recorded. Happy travels!</li>
				</ol>
			</div>
		</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>