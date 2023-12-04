<#-- @ftlvariable name="cell" type="storage.dto.CellDto" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Renting storage cells</title>

    <link href="./resources/css/style.css" rel="stylesheet">
    <link href="./resources/css/menu.css" rel="stylesheet">
    <link href="./resources/css/profile.css" rel="stylesheet">

    <script src="./resources/js/jquery.min.js"></script>
    <script src="./resources/js/profile.js"></script>
</head>
<body>
<div class="container">

    <#include "menu.ftl">
    <div class="center-content">
        <div class="container">
            <div class="title">Renting storage cells</div>
            <h1>Welcome to our Storage sell Rental</h1>
            <p>Please select a storage sell to rent:</p>
            <#list 1..100 as i>
                <form action="/main" method="post">
                    <#if ${cell.status}??><div class="free-cell">Unit ${i} is free</div>
                    <#else><div class="occupied-cell">Unit ${i} is occupied</div>
                    </#if>
                    <button type="submit" name="unitNumber" value="${i}">Unit ${i}</button>
                </form>
            </#list>
        </div>
    </div>
</div>
</body>
</html>