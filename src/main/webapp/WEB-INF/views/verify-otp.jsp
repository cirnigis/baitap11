<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Verify OTP</title>

</head>

<body>

    <div class="auth-container">

        <h1 class="auth-title">
            Verify OTP
        </h1>

        <p class="auth-subtitle">
            Please enter the OTP sent to your email.
        </p>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>

        <form method="post"
              action="${pageContext.request.contextPath}/verify-otp">

            <div class="form-group">

                <label class="form-label">
                    OTP
                </label>

                <input type="text"
                       name="otp"
                       maxlength="6"
                       class="form-control"
                       required>

            </div>

            <button type="submit"
                    class="btn btn-primary w-100">
                Verify
            </button>

        </form>

    </div>

</body>

</html>