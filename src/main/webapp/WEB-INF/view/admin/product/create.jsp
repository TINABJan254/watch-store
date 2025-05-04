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
                                <li class="breadcrumb-item active">Create product</li>
                            </ol>
                        </div>
                        <hr>
                        <div class="inner-form">
                            <div class="row">
                                <div class="col-12 col-md-6 mx-auto">
                                    <div class="col-12 col-md-6 mx-auto mb-3">
                                        <h3>Create a product</h3>
                                    </div>
                                    <form:form enctype="multipart/form-data" action="/admin/product/create" modelAttribute="newProduct" class="row">
                                        <div class="mb-3 col-12 col-md-12">
                                            <label class="form-label">Name:</label>
                                            <c:set var = "errorName">
                                                <form:errors path = "name" cssClass="invalid-feedback" />
                                            </c:set>

                                            <form:input type = "text" class="form-control ${not empty errorName? 'is-invalid':''}" path = "name"/>
                                            ${errorName}
                                        </div>
            
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Quantity:</label>
                                            <c:set var="errorQuantity">
                                                <form:errors path="quantity" cssClass="invalid-feedback" />
                                            </c:set>
                                            
                                            <form:input type="number" class="form-control ${not empty errorQuantity ? 'is-invalid':''}" path="quantity" />
                                            ${errorQuantity}
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Price:</label>
                                            <c:set var="errorPrice">
                                                <form:errors path="price" cssClass="invalid-feedback" />
                                            </c:set>
                                            
                                            <form:input type="number" class="form-control ${not empty errorPrice? 'is-invalid':''}" path="price" />
                                            ${errorPrice}
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Type:</label>
                                            <form:select class="form-select" path="type">
                                                <form:option value="Diver">Diver</form:option>
                                                <form:option value="Smart">Smart</form:option>
                                                <form:option value="Digital">Digital</form:option>
                                                <form:option value="Analog">Analog</form:option>
                                            </form:select>
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Factory:</label>
                                            <form:select class="form-select" path="factory">
                                                <form:option value="APPLE">Apple</form:option>
                                                <form:option value="CITIZEN">Citizen</form:option>
                                                <form:option value="CASIO">Casio</form:option>
                                                <form:option value="ROLEX">Rolex</form:option>
                                                <form:option value="SEIKO">Seiko</form:option>
                                                <form:option value="ORIENT">Orient</form:option>
                                            </form:select>
                                        </div>

                                        <div class="mb-3 col-12 col-md-12">
                                            <label class="form-label">Short description:</label>
                                            <c:set var = "errorShortDesc">
                                                <form:errors path = "shortDesc" cssClass = "invalid-feedback" />
                                            </c:set>

                                            <form:input type="text" class="form-control ${not empty errorShortDesc ? 'is-invalid':''}" path="shortDesc" />
                                            ${errorShortDesc}
                                        </div>
                                        <div class="mb-3 col-12">
                                            <label class="form-label">Detail description:</label>
                                            <c:set var = "errorDetailDesc">
                                                <form:errors path = "detailDesc" cssClass="invalid-feedback"/>
                                            </c:set>

                                            <form:textarea type="text" class="form-control ${not empty errorDetailDesc ? 'is-invalid':''}" path="detailDesc" />
                                            ${errorDetailDesc}
                                        </div>
            
                                        <div class="mb-3 col-12 col-md-6">
                                            <label for="avatarFile" class="form-label">Image:</label>
                                            <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg, .jpeg"
                                                name="thienFile">
                                        </div>
                                        <div class="col-12 mb-3">
                                            <img style="max-height: 250px; display: none;" alt="avatar preview"
                                                id="avatarPreview" />
                                        </div>
                                        <div class="form-btn col-12 mb-5">
                                            <button type="submit" class="btn btn-primary">Create</button>
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