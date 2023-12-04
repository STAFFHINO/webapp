<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/sign-in.css">
</head>
<body>
<div class="container">

    <form class="form-center-content" method="post">
        <div class="form-signin-heading">SIGN IN</div>
        <label>
            <input class="form-control" name="email" type="email" placeholder="Email">
        </label>
        <label>
            <input class="form-control" name="password" type="password" placeholder="Password">
        </label>
        <#if errorMessagr??>
            <div class="error_message">${errorMessage}</div>
        </#if>
        <button class="login-button" type="submit">SUBMIT</button>
        <button  type="submit" class="register-button"><a href="sign-up">SIGN-UP</a></button>
    </form>

</div>
</body>
</html>