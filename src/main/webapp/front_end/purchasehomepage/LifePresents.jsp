<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
	ProductService posvc = new ProductService();	
	/* ProductVO poVo = new ProductVO(); */
	List<ProductVO> povoList = posvc.getAll();
	pageContext.setAttribute("povoList", povoList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ProductHomePage.css">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<style>
	.car-BTN{
		position:relative;
		top:25px;
		border:1px solid rgb(33, 37, 41);
		background-color:white;
		font-size:20px;
		width:150px;
		color:rgb(33, 37, 41);
	}
.car-BTN:hover{
		background-color:rgb(33, 37, 41);
		color:white;
	}
</style>
</head>
<body>
	<header>
		<nav>
			<img class="nav-logo" src="${pageContext.request.contextPath}/images/logo.png" alt="">
			<table class="weather-div">
				<tbody class="weather-tbody">
					<tr class="weather-tr">
					</tr>
					<tr class="weather-tr-1">
					</tr>
				</tbody>
			</table>
			<div class="icon"></div>
			<img class="nav-top-img" src="${pageContext.request.contextPath}/images/wheather.png" alt=""> <a
				href="" class="text">會員登入/註冊</a> <a href="" class="nav-top-a"> <img
				class="nav-top-img-1" src="${pageContext.request.contextPath}/images/shopping-cart.png" alt="">
			</a>
			<div class="nav-top-bot">
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="" class="">
							<div class="nav-left-div">
								<p>首頁</p>
							</div>
					</a></li>
				</ul>
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="" class="">
							<div class="nav-left-div">
								<p>最新消息</p>
							</div>
					</a></li>
				</ul>
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="" class="">
							<div class="nav-left-div">
								<p>房型介紹</p>
							</div>
					</a></li>
				</ul>
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="" class="">
							<div class="nav-left-div">
								<p>關於我們</p>
							</div>
					</a></li>
				</ul>
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="" class="">
							<div class="nav-left-div">
								<p>活動商城</p>
							</div>
					</a></li>
				</ul>
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="${pageContext.request.contextPath}/front_end/purchasehomepage/PurchaseHomePage.jsp" class="">
							<div class="nav-left-div">
								<p>伴手禮商城</p>
							</div>
					</a></li>
				</ul>
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="" class="">
							<div class="nav-left-div">
								<p>媒體報導</p>
							</div>
					</a></li>
				</ul>
				<ul class="nav-ul-bot">
					<li class="nav-li"><a href="" class="">
							<div class="nav-left-div">
								<p>會員中心</p>
							</div>
					</a></li>
				</ul>
			</div>
		</nav>
	</header>

	<!-- 跑馬頁 -->

	<div id="myCarousel" class="carousel slide">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="item active" id="img-id1">
				<img class="img1" src="${pageContext.request.contextPath}/images/跑馬圖片.png" alt="First slide">
				<div class="carousel-caption"></div>
			</div>
			<div class="item" id="img-id2">
				<img class="img2" src="${pageContext.request.contextPath}/images/跑馬圖片2.png" alt="Second slide">
				<div class="carousel-caption"></div>
			</div>
			<div class="item" id="img-id3">
				<img class="img3" src="${pageContext.request.contextPath}/images/跑馬圖片3.jpg" alt="Third slide">
				<div class="carousel-caption"></div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev" id="slide1"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next" id="slide2"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<aside class="aside">
		<nav class="aside_nav">
			<!-- <button type="button" class="btn_hamburger">按鈕</button> -->
			<ul class="aside_nav_list">
				<li><a href="${pageContext.request.contextPath}/front_end/purchasehomepage/PurchaseHomePage.jsp">全部商品</a></li>
				<li ><a href="${pageContext.request.contextPath}/front_end/purchasehomepage/Food.jsp">食品</a></li>
				<li><a href="${pageContext.request.contextPath}/front_end/purchasehomepage/Gift.jsp">伴手禮</a></li>
				<li><a href="${pageContext.request.contextPath}/front_end/purchasehomepage/LifePresents.jsp">生活精品</a></li>
				<li><a href="#">訂單查詢</a></li>
				<li><a href="#">購物車</a></li>
			</ul>
		</nav>
	</aside>
	<div class="main">
	
	
	
	
	
	
		<!-- 搜尋區 -->
		
		
		<div class="search-all">
			<form action="${pageContext.request.contextPath}/productTypeSearch" class="search-form">
			<input type="hidden" name="action" value="searchTyping">
			<lable for="search-form" class="fas fa-search"></lable>
			<input type="search" name="search-bar-string" placeholder="搜尋..." id="search-box"/>
			</form>
			
			<form action="${pageContext.request.contextPath}/productTypeSearch" class="sort-form">
			<select class="select-bar" name="show-product-sort" onchange="this.form.submit()">
				<option value="0">熱門商品</option>
				<option value="1">最新上架</option>
				<option value="2">促銷商品</option>
			</select>
			
			<select class="select-bar" name="show-product-price-sort" onchange="this.form.submit()">
				<option value="">價格排序</option>
				<option value="0">由高到低</option>
				<option value="1">由低到高</option>
			</select>
			<input type="hidden" name="action" value="sort-bar">
			</form>
		</div>


		<!-- 商品欄 -->
		<form action="${pageContext.request.contextPath}/Product_order_detailServlet">
		<ul class="card-list">
			<c:forEach var="poVO" items="${povoList}">
			<c:if test="${poVO.product_category_id == 3}">
			<li class="card">
			<c:if test="${poVO.product_name == '雲淡風輕沐浴乳'}">
			<a class="card-image" href="${pageContext.request.contextPath}/front_end/purchasehomepage/Showproductpage4.jsp"
				target="_blank" style="background-image: url(${pageContext.request.contextPath}/images/黑白曲奇餅.png);"> 
			</a>
			<a class="card-description" href="${pageContext.request.contextPath}/front_end/purchasehomepage/Showproductpage0.jsp">
					<h2>${poVO.product_name }</h2>
					<p>$${poVO.product_price }</p> <input class="car-BTN" type="submit" name="goCar" value="加入購物車">
			</a>
			</c:if>
			
			</c:if>
			</c:forEach>
		</ul>
		<input type="hidden" name="action" value="buy_now">
		</form>
	</div>
	
	<footer>
		<div>
			<p>Copyright 2000 , CloudWind HotSpring , All Rights Reserved.
				Designed by</p>
			<p>DREAMCENTER</p>
		</div>
	</footer>


	<div class="textinfo">
		<a href=""><img class="textinfo" src="${pageContext.request.contextPath}/images/chat.png" alt=""></a>
	</div>
	<script src="${pageContext.request.contextPath}/js/date.js"></script>
	<script src="${pageContext.request.contextPath}/js/weather.js"></script>
	<script src="${pageContext.request.contextPath}/js/icon.js"></script>
	<script src="${pageContext.request.contextPath}/js/hidden.js"></script>
	<script src="${pageContext.request.contextPath}/js/purchasepage.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
</body>
</html>