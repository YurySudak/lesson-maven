<!DOCTYPE html>
<html>
 <head>
   <title>Ввести новых преподавателей</title>
   <meta charset="utf-8">
</head>
<body>
<jsp:include page="/menu.jsp" />
<h2 align=center>Ввести новых преподавателей</h2>
<form action="/admin/addteacher" method="post">
    <p>ФИО <input type="text" placeholder="Введите ФИО" name="fio" required></p>
    <p>Зарплата за 1 месяц <input type="text" placeholder="Введите зарплату за 1 месяц" name="z1" required></p>
    <p>Зарплата за 2 месяц <input type="text" placeholder="Введите зарплату за 2 месяц" name="z2" required></p>
    <p>Зарплата за 3 месяц <input type="text" placeholder="Введите зарплату за 3 месяц" name="z3" required></p>
    <p>Зарплата за 4 месяц <input type="text" placeholder="Введите зарплату за 4 месяц" name="z4" required></p>
    <p>Зарплата за 5 месяц <input type="text" placeholder="Введите зарплату за 5 месяц" name="z5" required></p>
    <p>Зарплата за 6 месяц <input type="text" placeholder="Введите зарплату за 6 месяц" name="z6" required></p>
    <p>Зарплата за 7 месяц <input type="text" placeholder="Введите зарплату за 7 месяц" name="z7" required></p>
    <p>Зарплата за 8 месяц <input type="text" placeholder="Введите зарплату за 8 месяц" name="z8" required></p>
    <p><button type="submit">Ввести</button></p>
</form>
<jsp:include page="/admin/teachers" />
</body>
</html>