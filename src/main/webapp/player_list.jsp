<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>選手一覧</title>
</head>
<body>
	<header><h1>選手一覧</h1></header>
	<main>
	<form action="player_insert.jsp" method="get">
    	<input type="submit" value="選手追加">
	</form>
	<table border="1">
		<tr>
            <th>ID</th>
            <th>選手名</th>
            <th>背番号</th>
            <th>守備位置</th>
            <th>詳細</th>
            <th>更新</th>
            <th>削除</th>
        </tr>
		<tr>
		    <td>1</td>
		    <td>高山</td>
		    <td>1</td>
		    <td>投手</td>
		    <td><a href="#">詳細</a></td>
		    <td><a href="#">更新</a></td>
		    <td><a href="#">削除</a></td>
		</tr>
	</table>
	</main>
</body>
</html>