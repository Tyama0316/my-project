<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー</title>
</head>
<body>
	<main>
		<p class="message">${errorMessage}</p>
		<a href="${backAddress}">戻る</a>
	</main>
</body>
</html>