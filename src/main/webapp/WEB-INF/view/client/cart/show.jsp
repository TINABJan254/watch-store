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
                        <h4>Giỏ hàng</h4>
                        <div class="breadcrumb__links">
                            <a href="/">Trang chủ</a>
                            <span>Giỏ hàng</span>
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
                <div class="col-lg-8">
                    <div class="shopping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Số lượng</th>
                                    <th>Tổng</th>
                                    <th>xóa</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${ empty cartDetails}">
                                    <tr>
                                        <td colspan="6">
                                            Chưa có sản phẩm nào được thêm vào giỏ hàng
                                        </td>
                                    </tr>
                                </c:if>
                                <c:forEach var="cartDetail" items="${cartDetails}" varStatus="status">
                                    <tr>
                                        <td class="product__cart__item">
                                            <div class="product__cart__item__pic">
                                                <a href="/product/${cartDetail.product.id}" target="_blank">
                                                    <img src="/images/product/${cartDetail.product.image}" alt="">
                                                </a>
                                            </div>
                                            <div class="product__cart__item__text">
                                                <h6>${cartDetail.product.name}</h6>
                                                <h5> 
                                                    <fmt:formatNumber type="number" value="${cartDetail.price}"/> đ
                                                </h5>
                                            </div>
                                        </td>
                                        <td class="quantity__item">
                                            <div class="quantity">
                                                <div class="pro-qty-2">
                                                    <input type="text" value="${cartDetail.quantity}" 
                                                        data-cart-detail-id="${cartDetail.id}"
                                                        data-cart-detail-price="${cartDetail.price}"
                                                        data-cart-detail-index="${status.index}">
                                                </div>
                                            </div>
                                        </td>
                                        <td class="cart__price" data-cart-detail-id="${cartDetail.id}">
                                            <fmt:formatNumber type="number" value="${cartDetail.price * cartDetail.quantity}"/> đ
                                        </td>
                                        <td class="cart__close">
                                            <form method="post" action="/delete-cart-product/${cartDetail.id}">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                <button>
                                                    <i class="fa fa-close"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
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
                    </div>
                </div>
                <c:if test="${not empty cartDetails}">
                    <div class="col-lg-4">
                        <div class="cart__total">
                            <h6>Tổng giỏ hàng</h6>
                            <ul>
                                <li>
                                    Tạm tính <span data-cart-total-price="${totalPrice}"> <fmt:formatNumber type="number" value="${totalPrice}" /> đ </span>
                                </li>
                                <li>Phí vận chuyển <span>0 đ</span></li>
                                <li>
                                    Tổng cộng <span data-cart-total-price="${totalPrice}"><fmt:formatNumber type="number" value="${totalPrice}" /> đ</span>
                                </li>
                            </ul>
                            <form:form method="post" action="/confirm-checkout"  modelAttribute="cart">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <!-- <a href="/checkout" class="primary-btn">Tiến hành thanh toán</a> -->
                                <div style="display: none;">
                                    <c:forEach var="cartDetail" items="${cart.cartDetails}" varStatus="status">
                                        <div class="mb-3">
                                            <div class="form-group">
                                                <label>Id:</label>
                                                <form:input class="form-control" type="text" value="${cartDetail.id}" path="cartDetails[${status.index}].id" />
                                            </div>
                                            <div class="form-group">
                                                <label>Quantity:</label>
                                                <form:input class="form-control" type="text" value="${cartDetail.quantity}" path="cartDetails[${status.index}].quantity" />
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <button class="primary-btn">Tiến hành thanh toán</button>
                            </form:form>
                        </div>
                    </div>
                </c:if>
                
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->

    <!-- Footer Section Begin -->
    <jsp:include page = "../layout/footer.jsp"/>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

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