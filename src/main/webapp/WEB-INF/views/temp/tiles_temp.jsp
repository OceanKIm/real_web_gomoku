<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Real오목</title>
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<!-- css -->
	<link rel="stylesheet" href="/res/css/common.css">
	<link rel="stylesheet" href="/res/css/home.css">
	<link rel="stylesheet" href="/res/css/play/playHome.css">
	<!-- js -->
	<script defer src="/res/js/home.js?"></script>
	<script defer src="/res/js/ajax.min.js?"></script>
</head>
<body>
	<div id="container">
		<tiles:insertAttribute name="header" />
		<section>
			<tiles:insertAttribute name="content" />			
		</section>		
	</div>
	<div>
		<footer>
			<a href="">feedback |</a>
			<a href="">contect |</a>		
			developed by ocean
		</footer>
	</div>
</body>
</html>



