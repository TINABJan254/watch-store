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

    <!-- Shop Details Section Begin -->
    <section class="shop-details">
        <div class="product__details__pic">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="product__details__breadcrumb">
                            <a href="/">Trang chủ</a>
                            <a href="/shop">Sản phẩm</a>
                            <span>Chi tiết sản phẩm</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="product__quickview">
            <div class="container">
                <div class="row">
                    <div class="product__details__image">
                        <div class="border rounded">
                            <img src="/images/product/${product.image}" class="img-fluid rounded" alt="Image">
                        </div>
                    </div>
                    <div class="product__details__text">
                        <h3>${product.name}</h3>
                        <h3>
                            <fmt:formatNumber type="number" value="${product.price}"/> đ
                        </h3>
                        <ul class="">
                            <li>
                                <span>Loại</span> : ${product.type}
                            </li>
                            <li>
                                <span>Kho</span> : ${product.quantity}
                            </li>
                        </ul>
                        <p>${product.shortDesc}</p>
                        <div class="product__details__cart__option">
                            <!-- <div class="quantity">
                                <div class="pro-qty"><span class="fa fa-angle-up dec qtybtn"></span>
                                    <input type="text" value="1">
                                    <span class="fa fa-angle-down inc qtybtn"></span>
                                </div>
                            </div> -->
                            <button form="add-to-cart-form" type="submit" class="primary-btn">
                                add to cart
                            </button>
                            <form id="add-to-cart-form" action="/add-product-to-cart/${product.id}" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="product__details__content">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="product__details__tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tabs-5"
                                    role="tab">Chi tiết</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-5" role="tabpanel">
                                    <div class="product__details__tab__content">
                                        <!-- <p class="note">Đồng hồ</p> -->
                                        <div class="product__details__tab__content__item">
                                            <h5>Mô tả chi tiết sản phẩm</h5>
                                            <p>${product.detailDesc}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Details Section End -->

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