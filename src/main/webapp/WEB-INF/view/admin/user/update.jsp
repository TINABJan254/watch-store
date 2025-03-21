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
            const avatarPreview = $("#avatarPreview");

            const avatarFileName = "${newUser.avatar}"; 
            const avatarFolderPath = "/images/avatar/"; 

            if (avatarFileName) {
                const fullAvatarURL = avatarFolderPath + avatarFileName;
                avatarPreview.attr("src", fullAvatarURL);
                avatarPreview.css({ "display": "block" });
            }

            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                avatarPreview.attr("src", imgURL);
                avatarPreview.css({ "display": "block" });
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
                                <li class="breadcrumb-item"> <a href="/admin/user">User</a></li>
                                <li class="breadcrumb-item active">Update</li>
                            </ol>
                        </div>
                        <hr>
                        <div class="inner-form">
                            <div class="row">
                                <div class="col-12 col-md-6 mx-auto">
                                    <div class="col-12 mx-auto mb-3 d-flex justify-content-center">
                                        <h3>Update the user with id = ${newUser.id}</h3>
                                    </div>

                                    <div class="col-12 mb-3 d-flex justify-content-center">
                                        <img style="max-height: 250px; display: none;" alt="avatar preview"
                                            id="avatarPreview" />
                                    </div>

                                    <form:form method="POST" enctype="multipart/form-data" action="/admin/user/update" modelAttribute="newUser" class="row">
                                        <div class="mb-3" style="display : none">
                                            <label class="form-label">ID:</label>
                                            <form:input type="text" class="form-control" path = "id"/>
                                        </div>
                                        <div class="mb-3 col-12 col-md-12">
                                            <label class="form-label">Full name:</label>
                                            <c:set var="errorFullName">
                                                <form:errors path="fullName" cssClass="invalid-feedback" />
                                            </c:set>
                                    
                                            <form:input type="text" class="form-control ${not empty errorFullName? 'is-invalid':''}" path="fullName" />
                                            ${errorFullName}
                                        </div>
                                        
                                        <div class="mb-3 col-12 col-md-12">
                                            <label class="form-label">Email:</label>
                                            <c:set var="errorEmail">
                                                <form:errors path="email" />
                                            </c:set>
                                    
                                            <form:input type="email" class="form-control ${not empty errorEmail? 'is-invalid':''}" path="email" />
                                            <form:errors path="email" cssClass="invalid-feedback" />
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Phone number:</label>
                                            <form:input type="text" class="form-control" path="phone" />
                                        </div>
                                        <div class="mb-3 col-12">
                                            <label class="form-label">Address:</label>
                                            <form:input type="text" class="form-control" path="address" />
                                        </div>
                                        
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Role:</label>
                                            <form:select class="form-select" path="role.name">
                                                <form:option value="ADMIN">ADMIN</form:option>
                                                <form:option value="USER">USER</form:option>
                                            </form:select>
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label for="avatarFile" class="form-label">Avatar:</label>
                                            <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg, .jpeg" name="avatarImg">
                                        </div>
                                        <div class="form-btn col-12 mb-5">
                                            <button type="submit" class="btn btn-warning">Update</button>
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