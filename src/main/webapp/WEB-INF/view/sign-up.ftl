<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/sign-in.css">
</head>
<body>

<form class="form-center-content" method="post">
    <div class="form-signin-heading">SIGN UP</div>
    <label>First name
        <input class="form-control" name="firstName" type="text">
    </label>
    <label>Last name
        <input class="form-control" name="lastName" type="text">
    </label>
    <label>Email
        <input class="form-control" name="email" type="email">
    </label>
    <label>Password
        <input class="form-control" name="password" type="password">
    </label>
    <label>Birthdate
        <input class="form-control" name="birthdate" type="date">
    </label>
    <#if errorMessage??>
        <div class="error_message">${errorMessage}</div>
    </#if>
    <button class="register-button" type="submit">SUBMIT</button>
    <button  type="submit" class="login-button"><a href="sign-in">SIGN-IN</a></button>
</form>

</body>
</html>