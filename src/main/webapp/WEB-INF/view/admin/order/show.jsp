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
                                <li class="breadcrumb-item active">Order</li>
                            </ol>
                        </div>
                        <hr>
                        <div class="inner-head">
                            <h3 class="fw-bold fs-4 my-3">Order table</h3>
                        </div>
                        <div class="inner-table">
                            <div class="row">
                                <div class="col-12 mx-auto">                    
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Total price</th>
                                                <th>User</th>
                                                <th>Create time</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="order" items="${orders}">
                                                <tr>
                                                    <th>${order.id}</th>
                                                    <td>
                                                        <fmt:formatNumber type="number" value="${order.totalPrice}" /> đ
                                                    </td>
                                                    <td>${order.user.fullName}</td>
                                                    <td> <span class="createTime">${order.createTime}</span> </td>
                                                    <td>${order.status}</td>
                                                    <td>
                                                        <a href="/admin/order/${order.id}" class="btn btn-success">View</a>
                                                        <a href="/admin/order/update/${order.id}" class="btn btn-warning mx-2">Update</a>
                                                        <!-- Button trigger modal -->   
                                                        <button type="button" class="btn btn-danger" onclick="setDeleteId('${order.id}')" data-bs-toggle="modal" data-bs-target="#deleteWarningModal">
                                                            Delete
                                                        </button>

                                                        <!-- Modal -->
                                                        <div class="modal fade" id="deleteWarningModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Warning</h1>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    Are you sure to delete this order?
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                                                    <form action="/admin/order/delete" method = "POST">
                                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                                        <input type="hidden" name="id" id="deleteOrderId"/>
                                                                        <button type="submit button" class="btn btn-success">Yes</button>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            </div>
                        </div>
                        <c:if test="${totalPages > 0}">
                            <div class="inner-pagination">
                                <a class="${1 eq currentPage ? 'page-link disabled' : 'page-link'}" href="/admin/order?limit=${limit}&page=${currentPage - 1}">&laquo;</a>
    
                                <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                    <a class="${(loop.index + 1) eq currentPage ? 'page-link active' : 'page-link'}" href="/admin/order?limit=${limit}&page=${loop.index + 1}">${loop.index + 1}</a>
                                </c:forEach>
    
                                <a class="${totalPages eq currentPage ? 'page-link disabled' : 'page-link'}" href="/admin/order?limit=${limit}&page=${currentPage + 1}">&raquo;</a>
                                
                                <div class="option__page__limit">
                                    <p>Size:</p>
                                    <select name="limit" id="limit_select">
                                        <option value="5" ${limit == 5 ? 'selected' : ''}>5</option>
                                        <option value="10" ${limit == 10 ? 'selected' : ''}>10</option>
                                        <option value="15" ${limit == 15 ? 'selected' : ''}>15</option>
                                        <option value="20" ${limit == 20 ? 'selected' : ''}>20</option>
                                    </select>
                                    <input type="hidden" name="page" value="${currentPage}" />
                                </div>
                            </div>
                        </c:if>
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