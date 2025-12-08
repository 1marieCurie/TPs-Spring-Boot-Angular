<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>User Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            width: 100%;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            background: white;
        }
        .login-container .jumbotron {
            border-radius: 8px;
        }
        .input-group-text {
            background-color: #007bff;
            color: white;
            border-radius: 0.25rem 0 0 0.25rem;
        }
        .input-group-prepend .input-group-text {
            border-right: 0;
        }
        .form-control {
            border-radius: 0 0.25rem 0.25rem 0;
        }
    </style>
</head>
<body>

<div class="login-container p-4">
    <div class="jumbotron border p-4">
        <h2 class="text-center">User Login</h2>
        <!-- Important: name="username" pour Spring Security -->
        <form action="/userloginvalidate" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" name="username" id="username" placeholder="Username*" required class="form-control form-control-lg">
                </div>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    </div>
                    <input type="password" class="form-control form-control-lg" placeholder="Password*" required name="password" id="password">
                </div>
            </div>
            <span>Don't have an account? <a class="linkControl" href="/register">Register here</a></span> <br><br>
            <input type="submit" value="Login" class="btn btn-primary btn-block">
            <h3 class="text-center text-danger mt-3">${msg}</h3>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
