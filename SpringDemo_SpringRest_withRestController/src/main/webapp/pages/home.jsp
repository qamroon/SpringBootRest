<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

function highligh(obj){
	
	if(obj){
		obj.setSelectionRange(0,obj.value.length);
		obj.select();
		
	}
	
}


</script>

</head>
<body>

<form action = "addCustomer">
CustomerId:<input type="text" name="custId" id="custId"  onfocus="highlight(this)"> <br>
CustomerName: <input type="text" name="custName" ><br>
Customer Address: <input type="text" name="custAddress"><br>
<input type="submit" >


</form>


<form action = "getCustomer">
CustomerId:<input type="text" name="custId"> <br>

<input type="submit" >


</form>

</body>
</html>