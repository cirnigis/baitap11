<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Order Success - Book Store</title>

</head>

<body>

    <div class="page-container">

        <div class="card text-center">

            <div class="card-body p-5">

                <h2 class="text-success mb-3">
                    Order Placed Successfully
                </h2>

                <p class="mb-2">
                    Thank you for your order.
                </p>

                <p>
                    Your Order ID is:
                    <strong>#${orderId}</strong>
                </p>

                <p>
                    Payment Method:
                    <strong>Cash on Delivery (COD)</strong>
                </p>

                <div class="mt-4">

                    <a href="${pageContext.request.contextPath}/home"
                       class="btn btn-primary">
                        Continue Shopping
                    </a>

                </div>

            </div>

        </div>

    </div>

</body>

</html>