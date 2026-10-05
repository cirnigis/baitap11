<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Order History</title>

</head>

<body>

<div class="page-container">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h1 class="page-title mb-0">
            Order History
        </h1>

        <a href="${pageContext.request.contextPath}/home"
           class="btn btn-primary">
            Continue Shopping
        </a>

    </div>

    <!-- Status Filter -->

    <div class="card mb-4">

        <div class="card-body">

            <h5 class="section-title">
                Filter by Status
            </h5>

            <div class="d-flex flex-wrap gap-2">

                <a href="${pageContext.request.contextPath}/orders?status=ALL"
                   class="btn ${selectedStatus == 'ALL' ? 'btn-primary' : 'btn-outline-primary'}">
                    All
                </a>

                <a href="${pageContext.request.contextPath}/orders?status=NEW"
                   class="btn ${selectedStatus == 'NEW' ? 'btn-primary' : 'btn-outline-primary'}">
                    New
                </a>

                <a href="${pageContext.request.contextPath}/orders?status=CONFIRMED"
                   class="btn ${selectedStatus == 'CONFIRMED' ? 'btn-primary' : 'btn-outline-primary'}">
                    Confirmed
                </a>

                <a href="${pageContext.request.contextPath}/orders?status=DELIVERING"
                   class="btn ${selectedStatus == 'DELIVERING' ? 'btn-primary' : 'btn-outline-primary'}">
                    Delivering
                </a>

                <a href="${pageContext.request.contextPath}/orders?status=DELIVERED"
                   class="btn ${selectedStatus == 'DELIVERED' ? 'btn-primary' : 'btn-outline-primary'}">
                    Delivered
                </a>

                <a href="${pageContext.request.contextPath}/orders?status=CANCELED"
                   class="btn ${selectedStatus == 'CANCELED' ? 'btn-primary' : 'btn-outline-primary'}">
                    Canceled
                </a>

                <a href="${pageContext.request.contextPath}/orders?status=RETURN_REFUND"
                   class="btn ${selectedStatus == 'RETURN_REFUND' ? 'btn-primary' : 'btn-outline-primary'}">
                    Return-Refund
                </a>

            </div>

        </div>

    </div>

    <!-- Orders -->

    <div class="card">

        <div class="card-header">
            Orders
        </div>

        <div class="card-body p-0">

            <c:choose>

                <c:when test="${empty orders}">

                    <div class="p-5 text-center">

                        <h5>
                            No Orders Found
                        </h5>

                        <p class="text-muted mb-0">
                            There are no orders with this status.
                        </p>

                    </div>

                </c:when>

                <c:otherwise>

                    <div class="table-responsive">

                        <table class="table table-hover mb-0">

                            <thead>

                                <tr>

                                    <th class="text-center">
                                        Order ID
                                    </th>

                                    <th class="text-center">
                                        Date
                                    </th>

                                    <th class="text-center">
                                        Recipient
                                    </th>

                                    <th class="text-center">
                                        Total
                                    </th>

                                    <th class="text-center">
                                        Payment
                                    </th>

                                    <th class="text-center">
                                        Status
                                    </th>

                                    <th class="text-center">
                                        Action
                                    </th>

                                </tr>

                            </thead>

                            <tbody>

                                <c:forEach var="order"
                                           items="${orders}">

                                    <tr>

                                        <td class="text-center">
                                            #${order.orderId}
                                        </td>

                                        <td class="text-center">

                                            <fmt:formatDate
                                                value="${order.createdAt}"
                                                pattern="dd/MM/yyyy HH:mm" />

                                        </td>

                                        <td class="text-center">
                                            ${order.recipientName}
                                        </td>

                                        <td class="text-center">

                                            <fmt:formatNumber
                                                value="${order.totalAmount}"
                                                type="number"
                                                minFractionDigits="2"
                                                maxFractionDigits="2" />

                                        </td>

                                        <td class="text-center">
                                            ${order.paymentMethod}
                                        </td>

                                        <td class="text-center">

                                            <c:choose>

                                                <c:when test="${order.orderStatus == 'NEW'}">
                                                    <span class="badge bg-primary">
                                                        New
                                                    </span>
                                                </c:when>

                                                <c:when test="${order.orderStatus == 'CONFIRMED'}">
                                                    <span class="badge bg-info text-dark">
                                                        Confirmed
                                                    </span>
                                                </c:when>

                                                <c:when test="${order.orderStatus == 'DELIVERING'}">
                                                    <span class="badge bg-warning text-dark">
                                                        Delivering
                                                    </span>
                                                </c:when>

                                                <c:when test="${order.orderStatus == 'DELIVERED'}">
                                                    <span class="badge bg-success">
                                                        Delivered
                                                    </span>
                                                </c:when>

                                                <c:when test="${order.orderStatus == 'CANCELED'}">
                                                    <span class="badge bg-danger">
                                                        Canceled
                                                    </span>
                                                </c:when>

                                                <c:when test="${order.orderStatus == 'RETURN_REFUND'}">
                                                    <span class="badge bg-secondary">
                                                        Return-Refund
                                                    </span>
                                                </c:when>

                                            </c:choose>

                                        </td>

                                        <td class="text-center">

                                            <a href="${pageContext.request.contextPath}/order-detail?id=${order.orderId}"
                                               class="btn btn-sm btn-primary">
                                                View
                                            </a>

                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                    </div>

                </c:otherwise>

            </c:choose>

        </div>

    </div>

</div>

</body>

</html>