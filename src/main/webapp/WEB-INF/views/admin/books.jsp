<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<title>Book Management</title>

</head>

<body>

	<div class="admin-page">

		<div class="d-flex justify-content-between align-items-center mb-4">

			<h1 class="page-title mb-0">Book Management</h1>

			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/admin/books/add"> Add
				Book </a>

		</div>

		<div class="card">

			<div class="card-body p-0">

				<div class="table-responsive">

					<table class="table table-hover mb-0">

						<thead>

							<tr>
								<th class="text-center">ID</th>
								<th class="text-center">ISBN</th>
								<th class="text-center">Title</th>
								<th class="text-center">Publisher</th>
								<th class="text-center">Price</th>
								<th class="text-center">Publish Date</th>
								<th class="text-center">Quantity</th>
								<th class="text-center">Cover Image</th>
								<th class="text-center">Actions</th>
							</tr>

						</thead>

						<tbody>

							<c:forEach var="book" items="${books}">

								<tr>

									<td>${book.bookid}</td>

									<td>${book.isbn}</td>

									<td><strong> ${book.title} </strong></td>

									<td>${book.publisher}</td>

									<td>${book.price}</td>

									<td>${book.publishDate}</td>

									<td class="text-center">${book.quantity}</td>

									<td><c:if test="${not empty book.coverImage}">

											<img
												src="${pageContext.request.contextPath}/images/${book.coverImage}"
												class="book-cover-small" alt="${book.title}">

										</c:if> <c:if test="${empty book.coverImage}">
                                            -
                                        </c:if></td>

									<td>

										<div class="book-actions">

											<a class="btn btn-sm btn-primary"
												href="${pageContext.request.contextPath}/admin/books/edit?id=${book.bookid}">
												Edit </a> <a class="btn btn-sm btn-danger"
												href="${pageContext.request.contextPath}/admin/books/delete?id=${book.bookid}"
												onclick="return confirm('Are you sure you want to delete this book?');">
												Delete </a>

										</div>

									</td>

								</tr>

							</c:forEach>

							<c:if test="${empty books}">

								<tr>

									<td colspan="9" class="text-center py-4">No books found.</td>

								</tr>

							</c:if>

						</tbody>

					</table>

				</div>

			</div>

		</div>

		<c:if test="${totalPages > 1}">

			<nav aria-label="Book pagination">

				<ul class="pagination justify-content-center">

					<c:if test="${currentPage > 1}">

						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/admin/books?page=${currentPage - 1}">
								Previous </a></li>

					</c:if>

					<c:forEach begin="1" end="${totalPages}" var="pageNumber">

						<c:choose>

							<c:when test="${pageNumber == currentPage}">

								<li class="page-item active"><span class="page-link">
										${pageNumber} </span></li>

							</c:when>

							<c:otherwise>

								<li class="page-item"><a class="page-link"
									href="${pageContext.request.contextPath}/admin/books?page=${pageNumber}">
										${pageNumber} </a></li>

							</c:otherwise>

						</c:choose>

					</c:forEach>

					<c:if test="${currentPage < totalPages}">

						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/admin/books?page=${currentPage + 1}">
								Next </a></li>

					</c:if>

				</ul>

			</nav>

		</c:if>

	</div>

</body>

</html>