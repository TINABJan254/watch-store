<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<aside id="sidebar">
    <div class="">
        <div class="sidebar-title">
            <i class="">TWatch store</i>
        </div>
    </div>
    <ul class="sidebar-nav">
        <li class="sidebar-item">
            <a href="/admin" class="sidebar-link">
                <i class="lni lni-dashboard"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="/admin/user" class="sidebar-link">
                <i class="lni lni-user"></i>
                <span>User</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="/admin/product" class="sidebar-link">
                <i class="lni lni-package"></i>
                <span>Product</span>
            </a>
        </li>
        <li class="sidebar-item">
            <a href="/admin/order" class="sidebar-link">
                <i class="lni lni-shopping-basket"></i>
                <span>Order</span>
            </a>
        </li>
    </ul>
    <div class="sidebar-footer">
        <a href="#" class="sidebar-link">
            <i class="lni lni-exit"></i>
            <span>Logout</span>
        </a>
    </div>
</aside>