<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Shopping Cart</title>

</head>

<body>

    <div class="page-container">

        <div class="d-flex justify-content-between align-items-center mb-4">

            <h1 class="page-title mb-0">
                Shopping Cart
            </h1>

            <a href="${pageContext.request.contextPath}/home"
               class="btn btn-secondary">
                Continue Shopping
            </a>

        </div>

        <c:choose>

            <c:when test="${empty cartItems}">

                <div class="card">

                    <div class="card-body text-center py-5">

                        <h3 class="mb-3">
                            Your cart is empty.
                        </h3>

                        <p class="text-muted mb-4">
                            Add some books to your cart before checking out.
                        </p>

                        <a href="${pageContext.request.contextPath}/home"
                           class="btn btn-primary">
                            Continue Shopping
                        </a>

                    </div>

                </div>

            </c:when>

            <c:otherwise>

                <div class="card">

                    <div class="card-body p-0">

                        <div class="table-responsive">

                            <table class="table table-hover mb-0 align-middle">

                                <thead>

                                    <tr>
                                        <th class="text-center">Image</th>
                                        <th>Book</th>
                                        <th class="text-end">Price</th>
                                        <th class="text-center">Quantity</th>
                                        <th class="text-center">Stock</th>
                                        <th class="text-end">Subtotal</th>
                                        <th class="text-center">Action</th>
                                    </tr>

                                </thead>

                                <tbody>

                                    <c:forEach var="item" items="${cartItems}">

                                        <tr>

                                            <td class="text-center">

                                                <c:if test="${not empty item.coverImage}">

                                                    <img
                                                        class="cart-image"
                                                        src="${pageContext.request.contextPath}/images/${item.coverImage}"
                                                        alt="${item.title}">

                                                </c:if>

                                                <c:if test="${empty item.coverImage}">
                                                    -
                                                </c:if>

                                            </td>

                                            <td>

                                                <strong>
                                                    ${item.title}
                                                </strong>

                                            </td>

                                            <td class="text-end">
                                                ${item.price}
                                            </td>

                                            <td class="text-center">

                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/cart/update"
                                                      class="d-flex justify-content-center align-items-center gap-2">

                                                    <input type="hidden"
                                                           name="cartId"
                                                           value="${item.cartId}">

                                                    <input
                                                        class="form-control cart-quantity"
                                                        type="number"
                                                        name="quantity"
                                                        value="${item.quantity}"
                                                        min="1"
                                                        max="${item.stock}"
                                                        required>

                                                    <button
                                                        class="btn btn-primary btn-sm"
                                                        type="submit">
                                                        Update
                                                    </button>

                                                </form>

                                            </td>

                                            <td class="text-center">
                                                ${item.stock}
                                            </td>

                                            <td class="text-end fw-bold">
                                                ${item.subtotal}
                                            </td>

                                            <td class="text-center">

                                                <a
                                                    class="btn btn-danger btn-sm"
                                                    href="${pageContext.request.contextPath}/cart/delete?cartId=${item.cartId}"
                                                    onclick="return confirm('Are you sure you want to remove this item?');">
                                                    Remove
                                                </a>

                                            </td>

                                        </tr>

                                    </c:forEach>

                                </tbody>

                            </table>

                        </div>

                    </div>

                </div>

                <div class="d-flex justify-content-between align-items-center mt-4">

                    <a href="${pageContext.request.contextPath}/home"
                       class="btn btn-secondary">
                        Continue Shopping
                    </a>

                    <a href="${pageContext.request.contextPath}/checkout"
                       class="btn btn-success">
                        Checkout with COD
                    </a>

                </div>

            </c:otherwise>

        </c:choose>

    </div>

</body>

</html>