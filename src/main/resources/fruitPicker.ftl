<html>
    <head>
        <title>Fruit Picker</title>
    </head>
    <body>
        <form action="/favourite_fruit" method="post">
            <p>What is your favourite fruit?</p>
            <#list fruits as fruit>
                <p>
                    <input type="radio" name="fruit" value="${fruit}">${fruit}</input>
                </p>
            </#list>
            <input type="submit" value="SUBMIT">
        </form>
    </body>
</html>