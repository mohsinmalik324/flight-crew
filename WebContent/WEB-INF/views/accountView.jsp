<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="_head.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="_header.jsp"></jsp:include>
		<h1>Account</h1>
		<h3>Hello <b>${loginedUser.userName}</b></h3>
		
		<div class="container">
  <h2>Account Info</h2>      
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Address</th>
        <th>City</th>
        <th>State</th>
        <th>ZipCode</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${person.firstName}</td>
        <td>${person.lastName}</td>
        <td>${person.address}</td>
        <td>${person.city}</td>
        <td>${person.state}</td>
        <td>${person.zipCode}</td>
      </tr>
    </tbody>
  </table>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Id</th>
        <th>AccountNo</th>
        <th>CreditCardNo</th>
        <th>Email</th>
        <th>CreationDate</th>
        <th>Rating</th>
        <th>Username</th>
        <th>Password</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${customer.id}</td>
        <td>${customer.accountNo}</td>
        <td>${customer.creditCardNo}</td>
        <td>${customer.email}</td>
        <td>${customer.creationDate}</td>
        <td>${customer.rating}</td>
        <td>${customer.username}</td>
        <td>${customer.password}</td>
      </tr>
    </tbody>
  </table>
</div>
		
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</body>
</html>