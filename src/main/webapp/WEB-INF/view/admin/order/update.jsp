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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
                                <li class="breadcrumb-item active">Update</li>
                            </ol>
                        </div>
                        <hr>
                        <div class="inner-head">
                            <h3 class="fw-bold fs-4 my-3">Order with id = ${order.id}</h3>
                        </div>
                        <div class="inner-form">
                            <div class="row">
                                <div class="col-12 col-md-6 mx-auto">
                                    <form:form method="post" action="/admin/order/update" class="row" modelAttribute="order">
                                        <div class="mb-3" style="display: none;">
                                            <label class="form-label">Id:</label>
                                            <form:input type="text" class="form-control" path="id" />
                                        </div>
                                    
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">User:</label>
                                            <input type="text" value="${order.user.fullName}" class="form-control" disabled="true" path="user.fullName" />
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Receiver:</label>
                                            <input type="text" value="${order.receiverName}" class="form-control" disabled="true" path="receiverName" />
                                        </div>
                                        
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Address:</label>
                                            <input type="text" value="${order.receiverAddress}" class="form-control" disabled="true" path="receiverAddress" />
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Phone:</label>
                                            <input type="text" value="${order.receiverPhone}" class="form-control" disabled="true" path="receiverAddress" />
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Price:</label>
                                            <span class="form-control"><fmt:formatNumber type="number" value="${order.totalPrice}" /> đ</span>
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Status:</label>
                                            <form:select class="form-select" path="status">
                                                <form:option value="PENDING">PENDING</form:option>
                                                <form:option value="SHIPPING">SHIPPING</form:option>
                                                <form:option value="COMPLETE">COMPLETE</form:option>
                                                <form:option value="CANCEL">CANCEL</form:option>
                                            </form:select>
                                        </div>
                                        <div class="col-12 mb-5">
                                            <button type="submit" class="btn btn-warning">Update</button>
                                        </div>
                                        <div>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>

</html>