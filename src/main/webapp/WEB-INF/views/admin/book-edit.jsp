<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Edit Book</title>

</head>

<body>

    <div class="admin-page">

        <div class="d-flex justify-content-between align-items-center mb-4">

            <h1 class="page-title mb-0">
                Edit Book
            </h1>

            <a href="${pageContext.request.contextPath}/admin/books"
               class="btn btn-secondary">
                Back to Books
            </a>

        </div>

        <div class="card">

            <div class="card-body p-4">

                <form method="post"
                      action="${pageContext.request.contextPath}/admin/books/edit">

                    <input type="hidden"
                           name="bookid"
                           value="${book.bookid}">

                    <div class="row">

                        <div class="col-md-6">

                            <div class="form-group">

                                <label class="form-label">
                                    ISBN
                                </label>

                                <input type="number"
                                       name="isbn"
                                       class="form-control"
                                       value="${book.isbn}"
                                       required>

                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="form-group">

                                <label class="form-label">
                                    Title
                                </label>

                                <input type="text"
                                       name="title"
                                       class="form-control"
                                       value="${book.title}"
                                       required>

                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="form-group">

                                <label class="form-label">
                                    Publisher
                                </label>

                                <input type="text"
                                       name="publisher"
                                       class="form-control"
                                       value="${book.publisher}">

                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="form-group">

                                <label class="form-label">
                                    Price
                                </label>

                                <input type="number"
                                       name="price"
                                       class="form-control"
                                       step="0.01"
                                       min="0"
                                       value="${book.price}"
                                       required>

                            </div>

                        </div>

                        <div class="col-md-12">

                            <div class="form-group">

                                <label class="form-label">
                                    Description
                                </label>

                                <textarea name="description"
                                          class="form-control"
                                          rows="4">${book.description}</textarea>

                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="form-group">

                                <label class="form-label">
                                    Publish Date
                                </label>

                                <input type="date"
                                       name="publishDate"
                                       class="form-control"
                                       value="${book.publishDate}">

                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="form-group">

                                <label class="form-label">
                                    Cover Image
                                </label>

                                <input type="text"
                                       name="coverImage"
                                       class="form-control"
                                       value="${book.coverImage}">

                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="form-group">

                                <label class="form-label">
                                    Quantity
                                </label>

                                <input type="number"
                                       name="quantity"
                                       class="form-control"
                                       min="0"
                                       value="${book.quantity}"
                                       required>

                            </div>

                        </div>

                    </div>

                    <div class="admin-actions mt-3">

                        <button type="submit"
                                class="btn btn-primary">
                            Update
                        </button>

                        <a href="${pageContext.request.contextPath}/admin/books"
                           class="btn btn-secondary">
                            Cancel
                        </a>

                    </div>

                </form>

            </div>

        </div>

    </div>

</body>

</html>