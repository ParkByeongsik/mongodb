<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,spring.data.mongodb.score.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/mongodb/score/search">
		<h1>검색하기</h1>
		검색할 필드선택: <select name="field">
			<option value="name">성명</option>
			<option value="id">아이디</option>
			<option value="addr">주소</option>
			<option value="dept">부서</option>
			<option value="java">java</option>
			<option value="spring">spring</option>
			<option value="servlet">servlet</option>
			<option value="bonus">bonus</option>

		</select>
		조건: <select name="criteria">
			<option value="is">=</option>
			<option value="gt">></option>
			<option value="gte">&gt;=</option>
			<option value="lt">&lt;</option>
			<option value="lte">&lt;=</option>
			

		</select>
		<h3>검색값:</h3>
		<input type="text" name="value" />
		<input type="submit" value="검색"/>
	</form>
</body>
</html>










