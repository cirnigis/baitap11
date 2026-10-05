<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Register</title>

</head>

<body>

    <div class="auth-container">

        <h1 class="auth-title">Register</h1>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>

        <form method="post"
              action="${pageContext.request.contextPath}/register">

            <div class="form-group">
                <label class="form-label">Email</label>
                <input type="email"
                       name="email"
                       class="form-control"
                       required>
            </div>

            <div class="form-group">
                <label class="form-label">Full Name</label>
                <input type="text"
                       name="fullname"
                       class="form-control"
                       required>
            </div>

            <div class="form-group">
                <label class="form-label">Phone</label>
                <input type="text"
                       name="phone"
                       class="form-control"
                       required>
            </div>

            <div class="form-group">
                <label class="form-label">Password</label>
                <input type="password"
                       name="passwd"
                       class="form-control"
                       required>
            </div>

            <button type="submit" class="btn btn-primary w-100">
                Register
            </button>

        </form>

        <div class="auth-links">

            <span>Already have an account?</span>

            <a href="${pageContext.request.contextPath}/login">
                Login
            </a>

        </div>

    </div>

</body>

</html>