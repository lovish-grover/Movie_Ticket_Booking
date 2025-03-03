<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="movie_inserted" method="get">
<b>movie_id</b> : <input type="number" name="nmMid"/><br>
TITLE : <input type="text" name="txtTit"/><br>
DESCRIPTION : <input type="text" name="txtDes"/><br>
YEAR : <input type="text" name="txtYer"/><br>
LANG_ID          : <input type="number" name="nmLid"/><br>
ORIGNAL_LANGUAGE : <input type="text" name="txtOlang"/><br>
RATING : <input type="number" name="nmRat"/><br>
START_DATE: <input type="date" name="dateSd"/><br>
END_DATE : <input type="date" name="dateEd"/><br>
SLOT_9TO12 : <input type="text" name="txtS9t12"/><br>
SLOT_12TO15 : <input type="text" name="txtS12t15"/><br> 
SLOT_15TO18 : <input type="text" name="txtS15t18"/><br> 
SLOT_18TO21 : <input type="text" name="txtS18t21"/><br>
GOLD_PRICE : <input type="number" name="nmGP"/><br>
SILVER_PRICE : <input type="number" name="nmSP"/><br>
<input type="submit" value="insert"/>                                                             
</form>
</body>
</html>