<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productpics.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
	ProductService posvc = new ProductService();
	ProductVO poVO = null;
	List<ProductVO> povoList = posvc.getAll();
	
	for(ProductVO povo : povoList){
		if(povo.getProduct_name().equals("經典茶葉禮盒")){
			poVO = povo;
			
		}
	}
	Product_pics_Service ppsvc = new Product_pics_Service();
	List<Product_pics_VO> ppVOList = ppsvc.getAllPicsbyId(poVO.getProduct_id());
	pageContext.setAttribute("ppVOList", ppVOList);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Oneprodct.css">
  <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
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
      <img class="nav-top-img" src="${pageContext.request.contextPath}/images/wheather.png" alt="">
      <a href="" class="text">會員登入/註冊</a>
      <a href="" class="nav-top-a">
        <img class="nav-top-img-1" src="${pageContext.request.contextPath}/images/shopping-cart.png" alt="">
      </a>
      <div class="nav-top-bot">
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="" class="">
              <div class="nav-left-div">
                <p>首頁</p>
              </div>
            </a>
          </li>
        </ul>
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="" class="">
              <div class="nav-left-div">
                <p>最新消息</p>
              </div>
            </a>
          </li>
        </ul>
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="" class="">
              <div class="nav-left-div">
                <p>房型介紹</p>
              </div>
            </a>
          </li>
        </ul>
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="" class="">
              <div class="nav-left-div">
                <p>關於我們</p>
              </div>
            </a>
          </li>
        </ul>
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="" class="">
              <div class="nav-left-div">
                <p>活動商城</p>
              </div>
            </a>
          </li>
        </ul>
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="${pageContext.request.contextPath}/front_end/purchasehomepage/PurchaseHomePage.jsp" class="">
              <div class="nav-left-div">
                <p>伴手禮商城</p>
              </div>
            </a>
          </li>
        </ul>
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="" class="">
              <div class="nav-left-div">
                <p>媒體報導</p>
              </div>
            </a>
          </li>
        </ul>
        <ul class="nav-ul-bot">
          <li class="nav-li">
            <a href="" class="">
              <div class="nav-left-div">
                <p>會員中心</p>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  
  <main>
    <div id="product-aside">
      <c:forEach var="eachPhoto" items="${ppVOList}">
      <img id="product-img1" src="<%=request.getContextPath()%>/prodpics_reader?product_photo_id=${eachPhoto.product_photo_id}" onclick='changes("${pageContext.request.contextPath}/prodpics_reader?product_photo_id=${eachPhoto.product_photo_id}")'>
      </c:forEach>
    </div>



    <img id="photo" src="${pageContext.request.contextPath}/images/經典茶葉禮盒.png">

  </main>
  <div class="product-title-all">
    <h1 class="product-name"><%=poVO.getProduct_name()%></h1>
    <div class="product-content">
      <h2>商品詳情</h2>
      <p><%=poVO.getProduct_describtion().substring(0, poVO.getProduct_describtion().indexOf(";"))%></p>
     	<%=poVO.getProduct_describtion().substring(poVO.getProduct_describtion().substring(0, poVO.getProduct_describtion().indexOf(";")).length()+1, poVO.getProduct_describtion().length())%>
    </div>
    <h1 class="hr1"></h1>
    <form method="post" action="${pageContext.request.contextPath}/Product_order_detailServlet">
      <span id="number-text">數量:</span>
      <div class="select-area">
        <span class="down" onclick='decreaseCount(event, this)'><img class="arrow-pic" src="${pageContext.request.contextPath}/images/arrow-down-sign-to-navigate.png"></span>
        <input  id="input-amount" type="number" name="input-amounts" value="1">
        <span class="up" onclick='increaseCount(event, this)'><img class="arrow-pic" src="${pageContext.request.contextPath}/images/navigate-up-arrow.png"></span>
      </div>
      <div class="purchase-area">
        <input type="hidden" name="action" value="buy_now"> 
        <input type="hidden" name="productId" value="<%=poVO.getProduct_id()%>">
        <input class="purchase-btn" name="payNow" type="submit" value="前往付款">
        <input class="addcar-btn" name="goCar" type="submit"   value="加入購物車">
        <!-- <input type="hidden" name="action" value="order-list">
        <input type="hidden" name="action" value="car-list"> -->
      </div>
    </form>
  </div>


  <footer>
    <div>
      <p>Copyright 2000 , CloudWind HotSpring , All Rights Reserved. Designed by </p>
      <p>DREAMCENTER</p>
    </div>
  </footer>

  	<div class="textinfo">
		<a href=""><img class="textinfo" src="${pageContext.request.contextPath}/images/chat.png" alt=""></a>
	</div>

  <script src="${pageContext.request.contextPath}/js/date.js"></script>
  <script src="${pageContext.request.contextPath}/js/weather.js"></script>
  <script src="${pageContext.request.contextPath}/js/hidden.js"></script>
  <script src="${pageContext.request.contextPath}/js/icon.js"></script>
  <script src="${pageContext.request.contextPath}/js/Oneproduct.js"></script>
</body>
</html>