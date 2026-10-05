<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<title>Checkout - Book Store</title>

<div class="page-container">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h1 class="page-title mb-0">
            Checkout
        </h1>

        <a href="${pageContext.request.contextPath}/cart"
           class="btn btn-secondary">
            Back to Cart
        </a>

    </div>

    <c:if test="${not empty error}">

        <div class="alert alert-danger mb-4">
            ${error}
        </div>

    </c:if>

    <div class="row g-4">

        <div class="col-lg-7">

            <div class="card">

                <div class="card-header">
                    Shipping Information
                </div>

                <div class="card-body p-4">

                    <form method="post"
                          action="${pageContext.request.contextPath}/checkout">

                        <div class="form-group">

                            <label class="form-label">
                                Recipient Name
                            </label>

                            <input type="text"
                                   name="recipientName"
                                   class="form-control"
                                   required>

                        </div>

                        <div class="form-group">

                            <label class="form-label">
                                Phone
                            </label>

                            <input type="text"
                                   name="phone"
                                   class="form-control"
                                   required>

                        </div>

                        <div class="form-group">

                            <label class="form-label">
                                Shipping Address
                            </label>

                            <textarea name="shippingAddress"
                                      class="form-control"
                                      rows="4"
                                      required></textarea>

                        </div>

                        <div class="form-group">

                            <label class="form-label">
                                Payment Method
                            </label>

                            <input type="text"
                                   class="form-control"
                                   value="Cash on Delivery (COD)"
                                   readonly>

                        </div>

                        <div class="admin-actions mt-4">

                            <button type="submit"
                                    class="btn btn-success">
                                Place Order
                            </button>

                            <a href="${pageContext.request.contextPath}/cart"
                               class="btn btn-secondary">
                                Back to Cart
                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

        <div class="col-lg-5">

            <div class="order-summary">

                <h2 class="section-title">
                    Order Summary
                </h2>

                <c:set var="total" value="0" />

                <c:forEach var="item" items="${cartItems}">

                    <div class="d-flex justify-content-between align-items-start mb-3">

                        <div class="me-3">

                            <div class="fw-semibold">
                                ${item.title}
                            </div>

                            <small class="text-muted">
                                Quantity: ${item.quantity}
                            </small>

                        </div>

                        <span class="text-nowrap">
                            <fmt:formatNumber
                                value="${item.subtotal}"
                                type="number"
                                minFractionDigits="2" />
                        </span>

                    </div>

                    <c:set var="total"
                           value="${total + item.subtotal}" />

                </c:forEach>

                <hr>

                <div class="d-flex justify-content-between align-items-center">

                    <strong>
                        Total
                    </strong>

                    <strong>
                        <fmt:formatNumber
                            value="${total}"
                            type="number"
                            minFractionDigits="2" />
                    </strong>

                </div>

            </div>

        </div>

    </div>

</div>