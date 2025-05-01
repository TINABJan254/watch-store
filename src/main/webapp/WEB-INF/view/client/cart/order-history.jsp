<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Watch store</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/client/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/client/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/client/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/client/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="/client/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/client/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/client/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/client/css/style.css" type="text/css">
</head>

<body>

    <!-- Header Section Begin -->
    <jsp:include page = "../layout/header.jsp"/>
    <!-- Header Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>Lịch sử mua hàng</h4>
                        <div class="breadcrumb__links">
                            <a href="/">Trang chủ</a>
                            <span>Lịch sử</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-9 offset-1">
                    <div class="shopping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <th>Trạng thái</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${ empty orders}">
                                    <tr>
                                        <td colspan="6">
                                            Bạn chưa mua đơn hàng nào
                                        </td>
                                    </tr>
                                </c:if>
                                <c:forEach var="order" items="${orders}">
                                    <tr>
                                        <td colspan="2">Order ID = ${order.id}</td>
                                        <td colspan="1">
                                            <h5><fmt:formatNumber type="number" value="${order.totalPrice}" /> đ</h5>
                                        </td>
                                        <td>
                                            <h5 style="font-weight: bold;">
                                                <c:if test="${order.status == 'PENDING'}">Đang chờ xử lý</c:if>
                                                <c:if test="${order.status == 'SHIPPING'}">Đang giao</c:if>
                                                <c:if test="${order.status == 'CANCEL'}">Đã hủy</c:if>
                                                <c:if test="${order.status == 'COMPLETE'}">Hoàn thành</c:if>
                                            </h5>
                                        </td>
                                    </tr>
                                    <c:forEach var="orderDetail" items="${order.orderDetails}">
                                        <tr>
                                            <td class="product__cart__item">
                                                <div class="product__cart__item__pic">
                                                    <img src="/images/product/${orderDetail.product.image}" alt="">
                                                </div>
                                                <div class="product__cart__item__text">
                                                    <h6>${orderDetail.product.name}</h6>
                                                    <h5><fmt:formatNumber type="number" value="${orderDetail.price}" /> đ</h5>
                                                </div>
                                            </td>
                                            <td class="quantity__item">
                                                <div class="quantity">
                                                    <div class="pro-qty-3">
                                                        <input type="text" value="x${orderDetail.quantity}">
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="cart__price"><fmt:formatNumber type="number" value="${orderDetail.price * orderDetail.quantity}" /> đ</td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn">
                                <a href="/shop">Tiếp tục mua sắm</a>
                            </div>
                        </div>
                        <!-- <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn update__btn">
                                <a href="#"><i class="fa fa-spinner"></i> Update cart</a>
                            </div>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->

    <!-- Footer Section Begin -->
    <jsp:include page = "../layout/footer.jsp"/>
    <!-- Footer Section End -->

    <!-- Js Plugins -->
    <script src="/client/js/jquery-3.3.1.min.js"></script>
    <script src="/client/js/bootstrap.min.js"></script>
    <script src="/client/js/jquery.nice-select.min.js"></script>
    <script src="/client/js/jquery.nicescroll.min.js"></script>
    <script src="/client/js/jquery.magnific-popup.min.js"></script>
    <script src="/client/js/jquery.countdown.min.js"></script>
    <script src="/client/js/jquery.slicknav.js"></script>
    <script src="/client/js/mixitup.min.js"></script>
    <script src="/client/js/owl.carousel.min.js"></script>
    <script src="/client/js/main.js"></script>
</body>

</html>