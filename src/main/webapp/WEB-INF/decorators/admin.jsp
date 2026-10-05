<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>
        <sitemesh:write property="title" />
    </title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

    <sitemesh:write property="head" />

</head>

<body>

    <header class="header">

        <div class="logo">
            Book Store - Administration
        </div>

        <nav class="menu">

            <a class="home-link"
               href="${pageContext.request.contextPath}/home">
                Home
            </a>

            <a href="${pageContext.request.contextPath}/admin/books">
                Books
            </a>

            <a href="${pageContext.request.contextPath}/admin/orders">
                Orders
            </a>

            <c:if test="${not empty sessionScope.user}">

                <span class="user-info">
                    ${sessionScope.user.fullname}
                </span>

                <a class="logout-link"
                   href="${pageContext.request.contextPath}/logout"
                   onclick="return confirm('Are you sure you want to log out?');">
                    Logout
                </a>

            </c:if>

        </nav>

    </header>

    <main class="main-content">

        <div class="admin-page">

            <sitemesh:write property="body" />

        </div>

    </main>

    <footer class="footer">

        <p>
            <strong>Student Name:</strong> CAO BAO NGOC
        </p>

        <p>
            <strong>Student ID:</strong> 24162080
        </p>

        <p>
            <strong>Exam Code:</strong> 02
        </p>

    </footer>

</body>

</html>