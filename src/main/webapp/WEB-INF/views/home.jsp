<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>User Home</title>

</head>

<body>

    <div class="page-container">

        <div class="d-flex justify-content-between align-items-center mb-4">

            <h1 class="page-title mb-0">
                User Home
            </h1>

        </div>

        <div class="card mb-4">

            <div class="card-body">

                <div class="d-flex align-items-center flex-wrap gap-2">

                    <strong class="me-2">
                        Author:
                    </strong>

                    <c:forEach var="author" items="${authors}">

                        <a
                            href="${pageContext.request.contextPath}/home?authorId=${author.authorId}&page=1"
                            class="btn btn-sm ${author.authorId == selectedAuthor.authorId ? 'btn-primary' : 'btn-outline-secondary'}">

                            ${author.authorName}

                        </a>

                    </c:forEach>

                </div>

            </div>

        </div>

        <c:if test="${selectedAuthor != null}">

            <div class="d-flex justify-content-between align-items-center mb-4">

                <h2 class="section-title mb-0">
                    Author: ${selectedAuthor.authorName}
                </h2>

            </div>

        </c:if>

        <c:choose>

            <c:when test="${not empty books}">

                <div class="row g-4">

                    <c:forEach var="book" items="${books}">

                        <div class="col-md-4">

                            <div class="card book-card">

                                <c:choose>

                                    <c:when test="${not empty book.coverImage}">

                                        <img
                                            src="${pageContext.request.contextPath}/images/${book.coverImage}"
                                            class="card-img-top"
                                            alt="${book.title}">

                                    </c:when>

                                    <c:otherwise>

                                        <div
                                            class="card-img-top d-flex justify-content-center align-items-center bg-light text-muted">

                                            No Image

                                        </div>

                                    </c:otherwise>

                                </c:choose>

                                <div class="card-body d-flex flex-column">

                                    <h3 class="card-title">

                                        <a
                                            href="${pageContext.request.contextPath}/book-detail?id=${book.bookid}"
                                            class="text-decoration-none">

                                            ${book.title}

                                        </a>

                                    </h3>

                                    <div class="mt-2">

                                        <p class="mb-2">
                                            <strong>ISBN:</strong>
                                            ${book.isbn}
                                        </p>

                                        <p class="mb-2">
                                            <strong>Author:</strong>
                                            ${selectedAuthor.authorName}
                                        </p>

                                        <p class="mb-2">
                                            <strong>Publisher:</strong>
                                            ${book.publisher}
                                        </p>

                                        <p class="mb-2">
                                            <strong>Publish Date:</strong>
                                            ${book.publishDate}
                                        </p>

                                        <p class="mb-2">
                                            <strong>Quantity:</strong>
                                            ${book.quantity}
                                        </p>

                                        <p class="mb-0">
                                            <strong>Review:</strong>
                                            10
                                        </p>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </c:forEach>

                </div>

            </c:when>

            <c:otherwise>

                <div class="alert alert-info">
                    No books found for this author.
                </div>

            </c:otherwise>

        </c:choose>

        <c:if test="${totalPages > 0}">

            <nav aria-label="Book pagination">

                <ul class="pagination justify-content-center">

                    <c:if test="${currentPage > 1}">

                        <li class="page-item">

                            <a
                                class="page-link"
                                href="${pageContext.request.contextPath}/home?authorId=${selectedAuthor.authorId}&page=${currentPage - 1}">

                                Previous

                            </a>

                        </li>

                    </c:if>

                    <c:forEach var="i"
                               begin="1"
                               end="${totalPages}">

                        <c:choose>

                            <c:when test="${i == currentPage}">

                                <li class="page-item active">

                                    <span class="page-link">
                                        ${i}
                                    </span>

                                </li>

                            </c:when>

                            <c:otherwise>

                                <li class="page-item">

                                    <a
                                        class="page-link"
                                        href="${pageContext.request.contextPath}/home?authorId=${selectedAuthor.authorId}&page=${i}">

                                        ${i}

                                    </a>

                                </li>

                            </c:otherwise>

                        </c:choose>

                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">

                        <li class="page-item">

                            <a
                                class="page-link"
                                href="${pageContext.request.contextPath}/home?authorId=${selectedAuthor.authorId}&page=${currentPage + 1}">

                                Next

                            </a>

                        </li>

                    </c:if>

                </ul>

            </nav>

        </c:if>

    </div>

</body>

</html>