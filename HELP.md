#docker
docker run --name smarthr-postgres -e POSTGRES_PASSWORD=password -d -p 54322:54322 postgres:alpine

1.	Получить полную информацию о сотруднике (его данные и ИД компаний, где он работает).
    GET /employees/{id}
2.	Получить список всех сотрудников (их данные и ИД компаний, где они работают). Желательно с поддержкой пагинации, но не обязательно.
    GET /employees
3.	Получить список всех сотрудников, работающих в компании по ее ИД.
    GET /employees/company/{id}
4.	Получить список компаний, в которых работает сотрудник по его ИД.
    GET /companies/employee/{id}
5.	Добавить нового сотрудника.
    POST /employees
6.	Редактировать сотрудника (ИНН, ФИО, дата рождения).
    PUT /employees/{id} + (body)
7.	Удалить сотрудника.
    DELETE /employees/{id}
8.	Добавить сотрудника, работающего в одной или нескольких компаниях.
    POST /employees
9.	Редактировать для существующего сотрудника его места работы (добавить новое, удалить имеющееся). Сделать это при помощи одного запроса.
    PUT /employees/{id}/replaceCompanies (body)