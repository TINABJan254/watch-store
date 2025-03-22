<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Jquery preview image -->
    <script>
        $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgURL);
                $("#avatarPreview").css({ "display": "block" });
            });
        });
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
                                <li class="breadcrumb-item"> <a href="/admin/product">Product</a></li>
                                <li class="breadcrumb-item active">View detail</li>
                            </ol>
                        </div>
                        <hr>
                        <div class="inner-head">
                            <h3 class="fw-bold fs-4 my-3">Product with id = ${product.id}</h3>
                        </div>
                        <div class="product-content">
                            <div class="inner-product-image">
                                <img src="/images/product/${product.image}" alt="">
                            </div>
                            <div class="inner-product-info">
                                <table class="table table-striped">
                                    <tr>
                                        <th>ID</th>
                                        <td>${product.id}</td>
                                    </tr>
                                    <tr>
                                        <th>Name</th>
                                        <td>${product.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Factory</th>
                                        <td>${product.factory}</td>
                                    </tr>
                                    <tr>
                                        <th>Price</th>
                                        <td>${product.price}</td>
                                    </tr>
                                    <tr>
                                        <th>Type</th>
                                        <td>${product.type}</td>
                                    </tr>
                                    <tr>
                                        <th>Quantity</th>
                                        <td>${product.quantity}</td>
                                    </tr>
                                    <tr>
                                        <th>Short description</th>
                                        <td>${product.shortDesc}</td>
                                    </tr>
                                    <tr>
                                        <th>Detail description</th>
                                        <td>${product.detailDesc}</td>
                                    </tr>
                                    
                                </table>
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