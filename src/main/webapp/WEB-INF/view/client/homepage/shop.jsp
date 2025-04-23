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
                        <h4>Mua sắm</h4>
                        <div class="breadcrumb__links">
                            <a href="/">Trang chủ</a>
                            <span>Sản phẩm</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shop Section Begin -->
    <section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="shop__sidebar">
                        <div class="shop__sidebar__search">
                            <form action="#">
                                <input type="text" placeholder="Search...">
                                <button type="submit"><span class="icon_search"></span></button>
                            </form>
                        </div>
                        <div class="shop__sidebar__accordion">
                            <div class="accordion" id="accordionExample">
                                <div class="card">
                                    <div class="card-heading">
                                        <span>Loại</span>
                                    </div>
                                    <div class="card-body">
                                        <div class="shop__sidebar__categories">
                                            <ul class="nice-scroll">
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">Đồng hồ thông minh</a>
                                                </li>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">Đồng hồ cơ</a>
                                                </li>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">Đồng hồ thể thao</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-heading">
                                        <span>Thương hiệu</span>
                                    </div>
                                    <div class="card-body">
                                        <div class="shop__sidebar__brand">
                                            <ul>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">Rolex</a>
                                                </li>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">Casio</a>
                                                </li>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">Apple</a>
                                                </li>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">Gucci</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-heading">
                                        <span>Giá</span>
                                    </div>
                                    <div class="card-body">
                                        <div class="shop__sidebar__price">
                                            <ul>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">$0.00 - $50.00</a>
                                                </li>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">$50.00 - $100.00</a>
                                                </li>
                                                <li>
                                                    <input type="checkbox">
                                                    <a href="#">$100.00 - $150.00</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- <div class="card">
                                    <div class="card-heading">
                                        <a data-toggle="collapse" data-target="#collapseSix">Tags</a>
                                    </div>
                                    <div id="collapseSix" class="collapse show" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <div class="shop__sidebar__tags">
                                                <a href="#">Product</a>
                                                <a href="#">Bags</a>
                                                <a href="#">Shoes</a>
                                                <a href="#">Fashio</a>
                                                <a href="#">Clothing</a>
                                                <a href="#">Hats</a>
                                                <a href="#">Accessories</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="shop__product__option">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__left">
                                    <p>Kết quả</p>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__right">
                                    <p>Sắp xếp:</p>
                                    <select>
                                        <option value="">Cao đến thấp</option>
                                        <option value="">Thấp đến cao</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <c:forEach var="product" items="${products}">
                            <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__item">
                                    <a href="/product/${product.id}">
                                        <div class="product__item__pic set-bg" data-setbg="/images/product/${product.image}">
                                            <span class="label">New</span>
                                        </div>
                                    </a>
                                    <div class="product__item__text">
                                        <h6>${product.name}</h6>
                                        <!-- <a href="#" class="add-cart">+ Add To Cart</a> -->
                                        <form action="/add-product-to-cart/${product.id}" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <button>+ Add To Cart</button>
                                        </form>
                                        <h5>
                                            <fmt:formatNumber type="number" value="${product.price}"/> đ
                                        </h5>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="product__pagination">
                                <a class="active" href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                                <span>...</span>
                                <a href="#">21</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Section End -->

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