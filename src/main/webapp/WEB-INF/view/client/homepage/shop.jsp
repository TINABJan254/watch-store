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
                            <div class="accordion" id="productFilter">
                                <div class="card">
                                    <div class="card-heading">
                                        <span>Loại</span>
                                    </div>
                                    <div class="card-body">
                                        <div class="shop__sidebar__categories">
                                            <ul class="nice-scroll">
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="Smart" id="type-1">
                                                    <label for="type-1">Đồng hồ thông minh</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="Analog" id="type-2">
                                                    <label for="type-2">Đồng hồ kim</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="Digital" id="type-3">
                                                    <label for="type-3">Đồng hồ điện tử</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="Diver" id="type-4">
                                                    <label for="type-4">Đồng hồ bơi</label>
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
                                                    <input class="filter-checkbox" type="checkbox" value="ROLEX" id="factory-1">
                                                    <label for="factory-1">Rolex</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="APPLE" id="factory-2">
                                                    <label for="factory-2">Apple</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="SEIKO" id="factory-3">
                                                    <label for="factory-3">Seiko</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="ORIENT" id="factory-4">
                                                    <label for="factory-4">Orient</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="CITIZEN" id="factory-5">
                                                    <label for="factory-5">Citizen</label>
                                                </li>
                                                
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="CASIO" id="factory-6">
                                                    <label for="factory-6">Casio</label>
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
                                                    <input class="filter-checkbox" type="checkbox" value="lt-1m" id="price-1">
                                                    <!-- <a href="#">Dưới 1 triệu</a> -->
                                                    <label for="price-1">Dưới 1 triệu</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="1m-3m" id="price-2">
                                                    <label for="price-2">1 triệu - 3 triệu</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="3m-6m" id="price-3">
                                                    <label for="price-3">3 triệu - 6 triệu</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="6m-9m" id="price-4">
                                                    <label for="price-4">6 triệu - 9 triệu</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="9m-15m" id="price-5">
                                                    <label for="price-5">9 triệu - 15 triệu</label>
                                                </li>
                                                <li>
                                                    <input class="filter-checkbox" type="checkbox" value="ge-15m" id="price-6">
                                                    <label for="price-6">Trên 15 triệu</label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div id="btnFilter" class="continue__btn">
                                            <a style="text-transform: none;" href="">Lọc</a>
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
                                    <p>Kết quả <c:if test="${totalPages == 0}">: Không tìm thấy sản phẩm phù hợp</c:if> </p>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__right">
                                    <p>Sắp xếp theo giá:</p>
                                    <select id="sort_select">
                                        <option value="asc" id="sort-1">Tăng dần</option>
                                        <option value="desc"id="sort-2">Giảm dần</option>
                                        <option value="none"id="sort-3" selected>Không</option>
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

                    <c:if test="${totalPages > 0}">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="product__pagination">
                                    <a class="${1 eq currentPage ? 'disabled' : ''}" href="/shop?limit=${limit}&page=${currentPage - 1}${queryString}">&laquo;</a>
                                    <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                        <a class="${(loop.index + 1) eq currentPage ? 'active' : ''}" href="/shop?limit=${limit}&page=${loop.index + 1}${queryString}">${loop.index +
                                            1}</a>
                                    </c:forEach>
                                    <a class="${totalPages eq currentPage ? 'disabled' : ''}" href="/shop?limit=${limit}&page=${currentPage + 1}${queryString}">&raquo;</a>
                                    <div class="shop__product__option__right">
                                        <!-- <p>Size:</p>
                                        <select>
                                            <option value="">5</option>
                                            <option value="">10</option>
                                            <option value="">20</option>
                                        </select> -->
                                        <!-- <form method="get" action="/shop">
                                            <p>Size:</p>
                                            <select name="limit" onchange="this.form.page.value = 1; this.form.submit()">
                                                <option value="3" ${limit == 3 ? 'selected' : ''}>3</option>
                                                <option value="6" ${limit == 6 ? 'selected' : ''}>6</option>
                                                <option value="9" ${limit == 9 ? 'selected' : ''}>9</option>
                                                <option value="12" ${limit == 12 ? 'selected' : ''}>12</option>
                                                <option value="20" ${limit == 20 ? 'selected' : ''}>20</option>
                                            </select>
                                            <input type="hidden" name="page" value="${currentPage}" />
                                        </form> -->
                                        <p>Size:</p>
                                        <select name="limit" id="limit_select">
                                            <option value="3" ${limit == 3 ? 'selected' : ''}>3</option>
                                            <option value="6" ${limit == 6 ? 'selected' : ''}>6</option>
                                            <option value="9" ${limit == 9 ? 'selected' : ''}>9</option>
                                            <option value="12" ${limit == 12 ? 'selected' : ''}>12</option>
                                            <option value="20" ${limit == 20 ? 'selected' : ''}>20</option>
                                        </select>
                                        <input type="hidden" name="page" value="${currentPage}" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
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