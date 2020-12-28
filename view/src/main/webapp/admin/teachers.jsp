<!DOCTYPE html>
<html>
 <head>
   <title>Ввести новых преподавателей</title>
   <meta charset="utf-8">
   <style> form {margin-left: 30%; }
   table {border-spacing: 20px 10px; } </style>
</head>
<body>
<jsp:include page="/menu.jsp" />
<h2 align=center>Ввести новых преподавателей</h2>
<form action="/admin/addteacher" method="post">
    <p>ФИО <input type="text" name="fio" size="50" required value="Блинов Игорь Игоревич"></p>
    <p>Зарплата за 1 месяц <input type="text" name="z1" size="5" required value="1100"></p>
    <p>Зарплата за 2 месяц <input type="text" name="z2" size="5" required value="1200"></p>
    <p>Зарплата за 3 месяц <input type="text" name="z3" size="5" required value="1300"></p>
    <p>Зарплата за 4 месяц <input type="text" name="z4" size="5" required value="1400"></p>
    <p>Зарплата за 5 месяц <input type="text" name="z5" size="5" required value="1500"></p>
    <p>Зарплата за 6 месяц <input type="text" name="z6" size="5" required value="1600"></p>
    <p>Зарплата за 7 месяц <input type="text" name="z7" size="5" required value="1700"></p>
    <p>Зарплата за 8 месяц <input type="text" name="z8" size="5" required value="1800"></p>
    <p><button type="submit">Ввести</button></p>
</form>
<jsp:include page="/admin/teachers" />
</body>
</html>