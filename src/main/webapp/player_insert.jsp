<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>選手登録</title>
</head>
<body>
	<header><h1>選手登録</h1></header>
	<main>
	<form action="PlayerInsertController" method="post">
		<p>　選手名：
			<input type="text" name="playerName">
		</p>
		<p>　背番号：
			<input type="number" name="uniformNumber">
		</p>
		<p>守備位置：
			<input type="text" name="position">
		</p>
		
		<input type="submit" value="登録">
		
	</form>
	<br>
		<a href="player_list.jsp">選手一覧へ戻る</a>
	</main>
</body>
</html>