<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% 	
	ProductVO poVO = (ProductVO)(request.getAttribute("poVo")); /* 取得該筆產品 */
	
	Integer amounts = (Integer)request.getAttribute("amounts");  /* 取得數量 */
	
	/* 該筆訂單  */
	Product_order_detailVO podVO = (Product_order_detailVO)request.getAttribute("podVO");
	pageContext.setAttribute("podVO", podVO);
	pageContext.setAttribute("poVO", poVO);
	pageContext.setAttribute("amounts", amounts);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
	<script src="./jquery.js"></script>
	<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment.css"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<style>
#card-number-input:hover{
	border: 3px solid #5B5B5B;
}
#card-holder-input:hover{
	border: 3px solid #5B5B5B;
}


#product-aside>img {
  /* display: inline-block; */
  height: 50px;
  width: 70px;
  filter: brightness(0.8);
  border: 0.5px solid #757575;
}

#product-aside>img:hover {
  border: 0px;
  filter: brightness(1.2);

}
#photo{
  /* border: 2px solid red; */
  filter: brightness(0.8);
  position: relative;
  left: 60px;
  top: 100px;
  width: 450px;
  height: 300px;
  display: inline-block;

}
#photo:hover{
	border: 0px;
  	filter: brightness(1);
}
.information{
	
	position:relative;
	top:120px;
	width:450px;
	height:100px;
	left:60px;
	font-size:22px;
}



</style>
</head>
<body>

<header>
    <nav>
      <a><img class="nav-logo" src="${pageContext.request.contextPath}/images/logo.png" alt=""></a>
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
  
  <main class="detail_list">
    <%-- <span class="confirm-words">商品訂單</span>
    <div class="display-area">
      <span class="span">會員編號</span><span class="display-data">${podVO.member_id}</span><br>
      <span class="span">商品名稱</span><span class="display-data"><%=poVO.getProduct_name()%></span><br>
      <span class="span">商品數量</span><span class="display-data">${amounts}</span><br>
      <span class="span">商品單價</span><span class="display-data"><%=poVO.getProduct_price()%></span><br>
      <span class="span">總價</span><span class="display-data">${amounts * poVO.product_price}</span><br>
    </div> --%>
    
   <!-- 顯示該商品圖片 -->
   <div id="Allphoto">
    <img id="photo" src="${pageContext.request.contextPath}/images/黑白曲奇餅.png">
    </div>
    <div class="information">
    <h2>商品介紹</h2>
      <p style="color:orange;">${poVO.product_describtion }</p>
      
     </div>
    <div class="info">
			<div class="accordion" id="accordionExample">
				<div class="accordion-item">
					<h2 class="accordion-header" id="headingOne">
						<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
							<span>①您的商品詳情</span>
						</button>
					</h2>
					<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
						<div class="accordion-body">
							<div class="info-1">
								會員編號:<input type="text"  class="p" name="phoneNumber" disabled value="這裡要先從session取得會員資料"><br>
								商品名稱:<input type="text"  placeholder="" class="n" name="firstName" disabled value="<%=poVO.getProduct_name()%>"><br>
								商品單價:<input type="text"  class="t" name="lastName" disabled value="<%=poVO.getProduct_price()%>"><br>
								商品數量:<input type="text"  class="m" name="e-mail" disabled value="${amounts}"><br>
								<span style="margin-left:200px">總價</span><span style="color:red;"> $${amounts * poVO.product_price}</span>
							</div>
						</div>
					</div>
				</div> 
				<div class="accordion-item">
					<h2 class="accordion-header" id="headingTwo">
						<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							<span>付款方式</span>
						</button>
					</h2>
					<div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
						<div class="accordion-body">
						
							<div class="container">
								<h1 class="cho">請選擇付款方式:</h1>
								
								<!-- 針對現金流 -->
								<div class="cah">
									<%--  <form action="${pageContext.request.contextPath}/Product_order_detailServlet" class="form-1"> --%>  
									<label for="cash" id="cashlabel">現金付款</label>
									<input type="radio"   id="cash"  name="same">
									<label for="cr" id="crlabel">信用卡付款</label>
									<input type="radio"  id="cr" name="same">
									<%-- <form action="${pageContext.request.contextPath}/Product_order_detailServlet"> --%> 
									<input type="submit" value="送出訂單" class="submit-top" >
									<input type="hidden" name="action" value="選擇現金付款">
									<input type="hidden" name="product" value="${poVO}">
									<input type="hidden" name="amounts" value="${amounts}">
									 <!-- </form> -->
								</div>
								<div class="card-container">
								
								
									<div id="front">
										<div class="image">
											<img src="${pageContext.request.contextPath}/images/chip.png" alt="">
											<img src="${pageContext.request.contextPath}/images/visa.png" alt="">
										</div>
										<div id="card-number-box">################</div>
										<div class="flexbox">
											<div class="box">
												<span>姓名</span>
												<div id="card-holder-name">card holder</div>
											</div>
											<div class="box">
												<span>到期日</span>
												<div class="expiration">
													<span class="exp-year">yy</span>
													<span class="exp-month">mm</span>
												</div>
											</div>
										</div>
									</div>

									<div id="back">
										<div class="stripe"></div>
										<div class="box">
											<span>cvv</span>
											<div id="cvv-box"></div>
											<img src="${pageContext.request.contextPath}/images/visa.png" alt="">
										</div>
									</div>

								</div>
									
									<form action="${pageContext.request.contextPath}/Product_order_detailServlet">
									<div class="inputBox">
										<span>信用卡卡號</span>
										<input type="text" maxlength="16" id="card-number-input">
									</div>
									<div class="inputBox">
										<span>姓名</span>
										<input type="text" id="card-holder-input" name="input-name" value="">
									</div>
									<div class="flexbox">
										<div class="inputBox">
											<span>到期年</span>
											<select name="" id="" class="year-input">
												<option value="year" selected disabled>請選擇</option>
												<option value="2021">2021</option>
												<option value="2022">2022</option>
												<option value="2023">2023</option>
												<option value="2024">2024</option>
												<option value="2025">2025</option>
												<option value="2026">2026</option>
												<option value="2027">2027</option>
												<option value="2028">2028</option>
												<option value="2029">2029</option>
												<option value="2030">2030</option>
											</select>
										</div>
										<div class="inputBox">
											<span>到期月</span>
											<select name="" id="" class="month-input">
												<option value="month" selected disabled>請選擇</option>
												<option value="01">01</option>
												<option value="02">02</option>
												<option value="03">03</option>
												<option value="04">04</option>
												<option value="05">05</option>
												<option value="06">06</option>
												<option value="07">07</option>
												<option value="08">08</option>
												<option value="09">09</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
											</select>
										</div>
										<div class="inputBox">
											<span>cvv</span>
											<input type="text" maxlength="4" id="cvv-input">
										</div>
									</div>
									
									<input type="hidden" value="${poVO.product_id}" name="productId">
									<input type="hidden" value="${amounts}" name="amounts">
									<input type="hidden" name="action" value="來自信用卡付款">
									<input type="submit" value="付款" class="submit-btn">
									</form>
							</div>
							
						</div>
							
					</div>
				</div>
				
      <!-- <form class="client-input-area">
      sadsdad<input>
      sadd<input>
      das<input>
      dasda<input>
     asdsa <input>
    </form> -->
  </main>
  	
  <div class="textinfo">
		<a href=""><img class="textinfo" src="${pageContext.request.contextPath}/images/chat.png" alt=""></a>
	</div>
  <footer>
    <div>
      <p>Copyright 2000 , CloudWind HotSpring , All Rights Reserved. Designed by </p>
      <p>DREAMCENTER</p>
    </div>
  </footer>

  



  
  <script>
    document.querySelector('#card-number-input').oninput = () => {
      document.querySelector('#card-number-box').innerText = document.querySelector('#card-number-input').value;
    }

    document.querySelector('#card-holder-input').oninput = () => {
      document.querySelector('#card-holder-name').innerText = document.querySelector('#card-holder-input').value;
    }

    document.querySelector('.month-input').oninput = () => {
      document.querySelector('.exp-month').innerText = document.querySelector('.month-input').value;
    }

    document.querySelector('.year-input').oninput = () => {
      document.querySelector('.exp-year').innerText = document.querySelector('.year-input').value;
    }

    document.querySelector('#cvv-input').onclick = () => {
      document.querySelector('#front').style.transform = 'perspective(1000px) rotateY(180deg)';
      document.querySelector('#back').style.transform = 'perspective(1000px) rotateY(0deg)';
    }

    document.querySelector('#cvv-input').onblur = () => {
      document.querySelector('#back').style.transform = 'perspective(1000px) rotateY(180deg)';
      document.querySelector('#front').style.transform = 'perspective(1000px) rotateY(0deg)';
    }

    document.querySelector('#cvv-input').oninput = () => {
      document.querySelector('#cvv-box').innerText = document.querySelector('#cvv-input').value;
    }
    document.querySelector('#cr').onclick = () => {
        document.querySelector('#front').style.visibility = "visible";
        document.querySelector('#back').style.visibility = "visible";
        document.querySelector('form').style.visibility = "visible";
        document.querySelector('.info').style.height = '961px';
        document.querySelector('.submit-top').style.visibility = 'hidden';
        document.querySelector('footer').style.bottom = '-1200px';

    }
     document.querySelector('#cash').onclick = () => {
        document.querySelector('#front').style.visibility = "hidden";
        document.querySelector('#back').style.visibility = "hidden";
        document.querySelector('form').style.visibility = "hidden";
        document.querySelector('.info').style.height = '400px';
        document.querySelector('.submit-top').style.visibility = 'visible';
    } 

  </script>
<script src="${pageContext.request.contextPath}/js/date.js"></script>
  <script src="${pageContext.request.contextPath}/js/weather.js"></script>
  <script src="${pageContext.request.contextPath}/js/hidden.js"></script>
  <script src="${pageContext.request.contextPath}/js/icon.js"></script>
 <%--  <script src="${pageContext.request.contextPath}/js/Oneproduct.js"></script> --%>
 <%-- <script src="${pageContext.request.contextPath}/js/page.js"></script> --%>
</body>
</html>