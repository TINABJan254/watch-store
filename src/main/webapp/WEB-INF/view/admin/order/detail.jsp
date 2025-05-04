<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin manage</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link rel="stylesheet" href="/admin/css/bootstrap.min.css"  />
    <link rel="stylesheet" href="/admin/css/style.css">

    <!-- JavaScript để cập nhật giá trị id -->
    <script>
        function setDeleteId(orderId) {
            document.getElementById("deleteOrderId").value = orderId;
        }
    </script>
</head>

<body>
    <div class="wrapper">
        <jsp:include page = "../layout/sidebar.jsp"/>
        <div class="main">
            <jsp:include page="../layout/header.jsp"/>
            <main class="content px-3 py-4">
                <div class="container-fluid">
                    <div class="mb-3">
                        <div class="inner-breadcrum">
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"> <a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item"> <a href="/admin/order">Order</a></li>
                                <li class="breadcrumb-item active">View detail</li>
                            </ol>
                        </div>
                        <hr>
                        <div class="inner-head">
                            <h3 class="fw-bold fs-4 my-3">Order with id = ${order.id}</h3>
                        </div>
                        <div class="order-content">
                            <div class="row">
                                <div class="col-6">
                                    <table class="table">
                                        <tr>
                                            <th>Receiver</th>
                                            <td>${order.receiverName}</td>
                                        </tr>
                                        <tr>
                                            <th>Address</th>
                                            <td>${order.receiverAddress}</td>
                                        </tr>
                                        <tr>
                                            <th>Phone number</th>
                                            <td>${order.receiverPhone}</td>
                                        </tr>
                                        <tr>
                                            <th>Total price</th>
                                            <td><fmt:formatNumber type="number" value="${order.totalPrice}" /> đ</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <br>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Product</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="orderDetail" items="${order.orderDetails}">
                                        <tr>
                                            <th scope="row">
                                                <div class="d-flex align-items-center">
                                                    <img src="/images/product/${orderDetail.product.image}" class="img-fluid me-5 rounded-circle"
                                                        style="width: 80px; height: 80px;" alt="">
                                                </div>
                                            </th>
                                            <td>
                                                <p class="mb-0 mt-4">
                                                    <a href="/product/${orderDetail.product.id}" target="_blank">
                                                        ${orderDetail.product.name}
                                                    </a>
                                                </p>
                                            </td>
                                            <td>
                                                <p class="mb-0 mt-4">
                                                    <fmt:formatNumber type="number" value="${orderDetail.price}" /> đ
                                                </p>
                                            </td>
                                            <td>
                                                <div class="input-group quantity mt-4" style="width: 100px;">
                                                    ${orderDetail.quantity}
                                                </div>
                                            </td>
                                            <td>
                                                <p class="mb-0 mt-4">
                                                    <fmt:formatNumber type="number" value="${orderDetail.price * orderDetail.quantity}" /> đ
                                                </p>
                                            </td>
            
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </main>
            </div>
            </div>
        </div>
    </div>
    <script src="/admin/js/bootstrap.bundle.min.js" ></script>
    <script src="/admin/js/jquery-3.7.1.min.js" ></script>
    <script src="/admin/js/main.js"></script>      
</body>

</html>