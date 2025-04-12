<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

<body class="bg-primary">
    <div id="layoutAuthentication">
        <div id="layoutAuthentication_content">
            <main>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-7">
                            <div class="card shadow-lg border-0 rounded-lg mt-5">
                                <div class="card-header">
                                    <h3 class="text-center font-weight-light my-4">Create Account</h3>
                                </div>
                                <div class="card-body">
                                    <form:form method="post" action="/register" modelAttribute="registerUser">
                                        <c:set var="errorFirstName">
                                            <form:errors path="firstName" cssClass="invalid-feedback" />
                                        </c:set>
                                        <c:set var="errorPassword">
                                            <form:errors path="confirmPassword" cssClass="invalid-feedback" />
                                        </c:set>
                                        <c:set var="errorEmail">
                                            <form:errors path="email" cssClass="invalid-feedback" />
                                        </c:set>
                                        <div class="row mb-3">
                                            <div class="col-md-6">
                                                <div class="form-floating mb-3 mb-md-0">
                                                    <label>First name</label>
                                                    <form:input class="form-control ${not empty errorFirstName ? 'is-invalid':''}" type="text" placeholder="Enter your first name" path="firstName"/>
                                                    ${errorFirstName}
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-floating">
                                                    <label>Last name</label>
                                                    <form:input class="form-control" type="text" placeholder="Enter your last name" path="lastName"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <label>Email address</label>
                                            <form:input class="form-control ${not empty errorEmail ? 'is-invalid':''}" type="email" placeholder="name@example.com" path="email"/>
                                            ${errorEmail}
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-md-6">
                                                <div class="form-floating mb-3 mb-md-0">
                                                    <label>Password</label>
                                                    <form:input class="form-control ${not empty errorPassword ? 'is-invalid':''}" type="password" placeholder="Create a password" path="password" />
                                                    ${errorPassword}
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-floating mb-3 mb-md-0">
                                                    <label>Confirm Password</label>
                                                    <form:input class="form-control" type="password" placeholder="Confirm password" path="confirmPassword" />
                                                    
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mt-4 mb-0">
                                            <div class="d-grid"><button type="submit" class="btn btn-primary">Create Account</button></div>
                                        </div>
                                    </form:form>
                                </div>
                                <div class="card-footer text-center py-3">
                                    <div class="small"><a href="/login">Have an account? Go to login</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>

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