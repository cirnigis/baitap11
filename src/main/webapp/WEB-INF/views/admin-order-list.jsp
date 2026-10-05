<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Order Management</title>

</head>

<body>

    <div class="admin-page">

        <div class="d-flex justify-content-between align-items-center mb-4">

            <h1 class="page-title mb-0">
                Order Management
            </h1>

            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/admin/books">
                Back to Books
            </a>

        </div>

        <div class="card">

            <div class="card-body p-0">

                <div class="table-responsive">

                    <table class="table table-hover mb-0">

                        <thead>

                            <tr>
                                <th>Order ID</th>
                                <th>Date</th>
                                <th>Customer</th>
                                <th>Phone</th>
                                <th>Total</th>
                                <th>Payment</th>
                                <th>Payment Status</th>
                                <th>Order Status</th>
                                <th>Update</th>
                            </tr>

                        </thead>

                        <tbody>

                            <c:choose>

                                <c:when test="${empty orders}">

                                    <tr>

                                        <td colspan="9"
                                            class="text-center py-4">
                                            No orders found.
                                        </td>

                                    </tr>

                                </c:when>

                                <c:otherwise>

                                    <c:forEach var="order"
                                               items="${orders}">

                                        <tr>

                                            <td>
                                                #${order.orderId}
                                            </td>

                                            <td>
                                                <fmt:formatDate
                                                    value="${order.createdAt}"
                                                    pattern="dd/MM/yyyy HH:mm" />
                                            </td>

                                            <td>
                                                ${order.recipientName}
                                            </td>

                                            <td>
                                                ${order.phone}
                                            </td>

                                            <td>
                                                <fmt:formatNumber
                                                    value="${order.totalAmount}"
                                                    type="number"
                                                    minFractionDigits="2"
                                                    maxFractionDigits="2" />
                                            </td>

                                            <td>
                                                ${order.paymentMethod}
                                            </td>

                                            <td>
                                                ${order.paymentStatus}
                                            </td>

                                            <td>

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

                                                    <c:otherwise>

                                                        ${order.orderStatus}

                                                    </c:otherwise>

                                                </c:choose>

                                            </td>

                                            <td>

                                                <c:choose>

                                                    <%-- NEW -> CONFIRMED / CANCELED --%>
                                                    <c:when test="${order.orderStatus == 'NEW'}">

                                                        <form method="post"
                                                              action="${pageContext.request.contextPath}/admin/orders/status"
                                                              class="order-status-form">

                                                            <input type="hidden"
                                                                   name="orderId"
                                                                   value="${order.orderId}">

                                                            <select name="status"
                                                                    class="form-select form-select-sm"
                                                                    required>

                                                                <option value="CONFIRMED">
                                                                    Confirmed
                                                                </option>

                                                                <option value="CANCELED">
                                                                    Canceled
                                                                </option>

                                                            </select>

                                                            <button type="submit"
                                                                    class="btn btn-sm btn-primary">
                                                                Update
                                                            </button>

                                                        </form>

                                                    </c:when>

                                                    <%-- CONFIRMED -> DELIVERING / CANCELED --%>
                                                    <c:when test="${order.orderStatus == 'CONFIRMED'}">

                                                        <form method="post"
                                                              action="${pageContext.request.contextPath}/admin/orders/status"
                                                              class="order-status-form">

                                                            <input type="hidden"
                                                                   name="orderId"
                                                                   value="${order.orderId}">

                                                            <select name="status"
                                                                    class="form-select form-select-sm"
                                                                    required>

                                                                <option value="DELIVERING">
                                                                    Delivering
                                                                </option>

                                                                <option value="CANCELED">
                                                                    Canceled
                                                                </option>

                                                            </select>

                                                            <button type="submit"
                                                                    class="btn btn-sm btn-primary">
                                                                Update
                                                            </button>

                                                        </form>

                                                    </c:when>

                                                    <%-- DELIVERING -> DELIVERED --%>
                                                    <c:when test="${order.orderStatus == 'DELIVERING'}">

                                                        <form method="post"
                                                              action="${pageContext.request.contextPath}/admin/orders/status"
                                                              class="order-status-form">

                                                            <input type="hidden"
                                                                   name="orderId"
                                                                   value="${order.orderId}">

                                                            <select name="status"
                                                                    class="form-select form-select-sm"
                                                                    required>

                                                                <option value="DELIVERED">
                                                                    Delivered
                                                                </option>

                                                            </select>

                                                            <button type="submit"
                                                                    class="btn btn-sm btn-primary">
                                                                Update
                                                            </button>

                                                        </form>

                                                    </c:when>

                                                    <%-- DELIVERED -> RETURN_REFUND --%>
                                                    <c:when test="${order.orderStatus == 'DELIVERED'}">

                                                        <form method="post"
                                                              action="${pageContext.request.contextPath}/admin/orders/status"
                                                              class="order-status-form">

                                                            <input type="hidden"
                                                                   name="orderId"
                                                                   value="${order.orderId}">

                                                            <select name="status"
                                                                    class="form-select form-select-sm"
                                                                    required>

                                                                <option value="RETURN_REFUND">
                                                                    Return-Refund
                                                                </option>

                                                            </select>

                                                            <button type="submit"
                                                                    class="btn btn-sm btn-primary">
                                                                Update
                                                            </button>

                                                        </form>

                                                    </c:when>

                                                    <%-- CANCELED --%>
                                                    <c:when test="${order.orderStatus == 'CANCELED'}">

                                                        <span class="text-muted">
                                                            No further action
                                                        </span>

                                                    </c:when>

                                                    <%-- RETURN_REFUND --%>
                                                    <c:when test="${order.orderStatus == 'RETURN_REFUND'}">

                                                        <span class="text-muted">
                                                            No further action
                                                        </span>

                                                    </c:when>

                                                    <c:otherwise>

                                                        <span class="text-muted">
                                                            No available action
                                                        </span>

                                                    </c:otherwise>

                                                </c:choose>

                                            </td>

                                        </tr>

                                    </c:forEach>

                                </c:otherwise>

                            </c:choose>

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    </div>

</body>

</html>