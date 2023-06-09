<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
	<title>Parent Page</title>
</head>
<body>
<h2>this is parent Page!</h2>
<p>total: ${total}</p>
<c:forEach var="entity" items="${parentList }">
	ID: <c:out value="${entity.id }" /> &nbsp;
	Receiver: <c:out value="${entity.receiver }" />&nbsp;
	Sender: <c:out value="${entity.sender }" />&nbsp;
	TotalAmount: <c:out value="${entity.totalAmount }" />&nbsp;
	TotalPaidAmount: <c:out value="${entity.totalPaidAmount }" />&nbsp;
	<hr>
</c:forEach>
</body>
</html>
