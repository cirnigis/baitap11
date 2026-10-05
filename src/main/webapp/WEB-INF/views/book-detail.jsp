<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>${book.title}</title>

</head>

<body>

    <div class="page-container">

        <div class="d-flex justify-content-between align-items-center mb-4">

            <h1 class="page-title mb-0">
                Book Details
            </h1>

            <a href="${pageContext.request.contextPath}/home"
               class="btn btn-secondary">
                Back to Home
            </a>

        </div>

        <div class="card mb-4">

            <div class="card-body p-4">

                <div class="row g-4">

                    <div class="col-md-4 text-center">

                        <c:if test="${not empty book.coverImage}">

                            <img
                                src="${pageContext.request.contextPath}/images/${book.coverImage}"
                                class="book-detail-image"
                                alt="${book.title}">

                        </c:if>

                        <c:if test="${empty book.coverImage}">

                            <div class="text-muted">
                                No cover image
                            </div>

                        </c:if>

                    </div>

                    <div class="col-md-8">

                        <h2 class="section-title">
                            ${book.title}
                        </h2>

                        <div class="mb-3">

                            <p>
                                <strong>ISBN:</strong>
                                ${book.isbn}
                            </p>

                            <p>
                                <strong>Publisher:</strong>
                                ${book.publisher}
                            </p>

                            <p>
                                <strong>Publish Date:</strong>
                                ${book.publishDate}
                            </p>

                            <p>
                                <strong>Quantity:</strong>
                                ${book.quantity}
                            </p>

                            <p>
                                <strong>Price:</strong>
                                ${book.price}
                            </p>

                            <p>
                                <strong>Description:</strong>
                                ${book.description}
                            </p>

                            <p>
                                <strong>Reviews:</strong>
                                ${reviewCount}
                            </p>

                        </div>

                        <div class="order-summary">

                            <form method="post"
                                  action="${pageContext.request.contextPath}/cart/add">

                                <input type="hidden"
                                       name="bookId"
                                       value="${book.bookid}">

                                <div class="row align-items-end g-3">

                                    <div class="col-md-4">

                                        <label class="form-label">
                                            Quantity
                                        </label>

                                        <input type="number"
                                               name="quantity"
                                               class="form-control"
                                               value="1"
                                               min="1"
                                               max="${book.quantity}"
                                               required>

                                    </div>

                                    <div class="col-md-8">

                                        <button type="submit"
                                                class="btn btn-primary">
                                            Add to Cart
                                        </button>

                                    </div>

                                </div>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <div class="card mb-4">

            <div class="card-header">
                <h2 class="section-title mb-0">
                    Reviews (${reviewCount})
                </h2>
            </div>

            <div class="card-body">

                <c:choose>

                    <c:when test="${empty reviews}">

                        <p class="text-muted mb-0">
                            No reviews yet.
                        </p>

                    </c:when>

                    <c:otherwise>

                        <c:forEach var="review" items="${reviews}">

                            <div class="border-bottom py-3">

                                <div class="fw-bold mb-2">
                                    ${review.userName}
                                </div>

                                <div>
                                    ${review.reviewText}
                                </div>

                            </div>

                        </c:forEach>

                    </c:otherwise>

                </c:choose>

            </div>

        </div>

        <div class="card">

            <div class="card-header">

                <h2 class="section-title mb-0">
                    Add Review
                </h2>

            </div>

            <div class="card-body p-4">

                <form method="post"
                      action="${pageContext.request.contextPath}/review-add">

                    <input type="hidden"
                           name="bookId"
                           value="${book.bookid}">

                    <div class="form-group">

                        <label class="form-label">
                            Your Review
                        </label>

                        <textarea name="reviewText"
                                  class="form-control"
                                  rows="5"
                                  placeholder="Write your review..."
                                  required></textarea>

                    </div>

                    <button type="submit"
                            class="btn btn-primary">
                        Submit Review
                    </button>

                </form>

            </div>

        </div>

    </div>

</body>

</html>