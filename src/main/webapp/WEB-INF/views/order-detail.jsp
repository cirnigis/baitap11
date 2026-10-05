<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Order Detail</title>
</head>

<body>

<div class="page-container">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="page-title mb-0">
            Order #${order.orderId}
        </h1>

        <a href="${pageContext.request.contextPath}/orders"
           class="btn btn-secondary">
            Back to Orders
        </a>
    </div>

    <!-- Order Information -->
    <div class="card mb-4">

        <div class="card-header">
            Order Information
        </div>

        <div class="card-body">

            <div class="row">

                <div class="col-md-6 mb-3">
                    <strong>Order ID:</strong>
                    ${order.orderId}
                </div>

                <div class="col-md-6 mb-3">
                    <strong>Order Date:</strong>
                    <fmt:formatDate
                        value="${order.createdAt}"
                        pattern="dd/MM/yyyy HH:mm" />
                </div>

                <div class="col-md-6 mb-3">
                    <strong>Order Status:</strong>
                    ${order.orderStatus}
                </div>

                <div class="col-md-6 mb-3">
                    <strong>Payment Method:</strong>
                    ${order.paymentMethod}
                </div>

                <div class="col-md-6 mb-3">
                    <strong>Payment Status:</strong>
                    ${order.paymentStatus}
                </div>

            </div>

        </div>
    </div>


    <!-- Shipping Information -->
    <div class="card mb-4">

        <div class="card-header">
            Shipping Information
        </div>

        <div class="card-body">

            <p>
                <strong>Recipient:</strong>
                ${order.recipientName}
            </p>

            <p>
                <strong>Phone:</strong>
                ${order.phone}
            </p>

            <p class="mb-0">
                <strong>Shipping Address:</strong>
                ${order.shippingAddress}
            </p>

        </div>
    </div>


    <!-- Order Items -->
    <div class="card mb-4">

        <div class="card-header">
            Order Items
        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-bordered table-hover mb-0">

                    <thead class="table-light">

                        <tr>
                            <th>Book ID</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Subtotal</th>
                        </tr>

                    </thead>

                    <tbody>

                        <c:forEach var="detail"
                                   items="${orderDetails}">

                            <tr>

                                <td>
                                    ${detail.bookId}
                                </td>

                                <td>
                                    ${detail.quantity}
                                </td>

                                <td>
                                    <fmt:formatNumber
                                        value="${detail.unitPrice}"
                                        type="number"
                                        minFractionDigits="2"
                                        maxFractionDigits="2" />
                                </td>

                                <td>
                                    <fmt:formatNumber
                                        value="${detail.subtotal}"
                                        type="number"
                                        minFractionDigits="2"
                                        maxFractionDigits="2" />
                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                    <tfoot>

                        <tr>
                            <th colspan="3"
                                class="text-end">
                                Total:
                            </th>

                            <th>
                                <fmt:formatNumber
                                    value="${order.totalAmount}"
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2" />
                            </th>
                        </tr>

                    </tfoot>

                </table>

            </div>

        </div>
    </div>


    <div class="text-end">

        <a href="${pageContext.request.contextPath}/orders"
           class="btn btn-secondary">
            Back to Orders
        </a>

        <a href="${pageContext.request.contextPath}/home"
           class="btn btn-primary">
            Continue Shopping
        </a>

    </div>

</div>

</body>
</html>