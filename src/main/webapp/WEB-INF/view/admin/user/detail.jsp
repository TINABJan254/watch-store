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
            const avatarPreview = $("#avatarPreview");

            const avatarFileName = "${user.avatar}"; 
            const avatarFolderPath = "/images/avatar/"; 

            if (avatarFileName) {
                const fullAvatarURL = avatarFolderPath + avatarFileName;
                avatarPreview.attr("src", fullAvatarURL);
                avatarPreview.css({ "display": "block" });
            }
        });
    </script>
</head>

<body>
    <div class="wrapper">
        <jsp:include page = "../layout/sidebar.jsp"/>
        <div class="main">
            <jsp:include page = "../layout/header.jsp"/>
            <div class="content">
                <div class="container-fluid">
                    <div class="content-breadcrum">
                        <div class="container-fluid">
                            <ol class="breadcrumb mt-4 mb-4">
                                <li class="breadcrumb-item"> <a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item"> <a href="/admin/user">User</a></li>
                                <li class="breadcrumb-item active">View detail</li>
                            </ol>
                            <hr>
                        </div>
                    </div>
                    <div class="title">
                        <h3>User detail with id = 1</h3>
                    </div>
                    <div class="inner-wrap">
                        <div class="inner-image">
                            <img src="avt-user.png" alt="">
                            <img alt="avatar preview" id="avatarPreview" />
                            <span>${user.role.name}</span>
                            <span>${user.fullName}</span>
                        </div>

                        <div class="inner-infor">
                            <table class="table table-striped">
                                <tr>
                                    <th>ID</th>
                                    <td>${user.id}</td>
                                </tr>
                                <tr>
                                    <th>Full name</th>
                                    <td>${user.fullName}</td>
                                </tr>
                                <tr>
                                    <th>Email</th>
                                    <td>${user.email}</td>
                                </tr>
                                <tr>
                                    <th>Address</th>
                                    <td>${user.address}</td>
                                </tr>
                                <tr>
                                    <th>Phone number</th>
                                    <td>${user.phone}</td>
                                </tr>
                                <tr>
                                    <th>Role</th>
                                    <td>${user.role.name}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                

            </div>
            
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="script.js"></script>
</body>

</html>