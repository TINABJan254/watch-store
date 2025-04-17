<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__option">
        <div class="offcanvas__links">
            <a href="#">Đăng nhập</a>
        </div>
        <div class="offcanvas__top__hover">
            <span>ADMIN <i class="arrow_carrot-down"></i></span>
            <ul>
                <li>Tài khoản</li>
                <li>Lịch sử mua hàng</li>
                <li>Đăng xuất</li>
            </ul>
        </div>
    </div>
    <div class="offcanvas__nav__option">
        <a href="#" class="search-switch"><img src="img/icon/search.png" alt=""></a>
        <a href="#"><img src="img/icon/cart.png" alt=""> <span>0</span></a>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__text">
        <p>Miễn phí vận chuyển, 30 ngày trả hàng hoàn tiền.</p>
    </div>
</div>
<!-- Offcanvas Menu End -->

<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7">
                    <div class="header__top__left">
                        <p>Miễn phí vận chuyển, 30 ngày trả hàng hoàn tiền.</p>
                    </div>
                </div>
                <div class="col-lg-6 col-md-5">
                    <div class="header__top__right">
                        <c:if test="${empty pageContext.request.userPrincipal}">
                            <div class="header__top__links">
                                <a href="/login">Đăng nhập</a>
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty pageContext.request.userPrincipal}">
                            <div class="header__top__hover">
                                <span>Admin </span>
                                <ul>
                                    <li style="white-space: nowrap; text-align: center;">
                                        <a style="color: #000000; font-size: 15px;" href="#">Tài khoản</a>
                                    </li>
                                    <li style="white-space: nowrap; text-align: center;">
                                        <a style="color: #000000; font-size: 15px;" href="/order-history">Lịch sử mua hàng</a>
                                    </li>
                                    <li style="white-space: nowrap; text-align: center;">
                                        <a style="color: #000000; font-size: 15px;" href="#"> 
                                            <form method="post" action="/logout">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                <button type="submit">
                                                    Đăng xuất
                                                </button>
                                            </form>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="col-lg-3 col-md-3" style="display: flex; justify-content: center; align-items: center; height: 100%;">
                    <a href="/">
                        <h2 style="font-weight: bold; color:chocolate">TWatch</h2>
                    </a>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li><a href="/">Trang chủ</a></li>
                        <li><a href="/shop">Sản phẩm</a></li>
                        <li><a href="#">Pages</a>
                            <ul class="dropdown">
                                <li><a href="./shop-details.html">Shop Details</a></li>
                                <li><a href="./shopping-cart.html">Shopping Cart</a></li>
                                <li><a href="./checkout.html">Check Out</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-3">
                <div class="header__nav__option">
                    <!-- <a href="#" class="search-switch"><img src="/client/icon/search.png" alt=""></a> -->
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                        <a href="/cart"><img src="/client/icon/cart.png" alt=""> <span>2</span></a>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
