**Лабораторная работа 4. Часть 1. Реализация standalone-сервиса**

# Поиск с помощью standalone-реализации REST-сервиса

## Задание

> Таблица БД, а также код для работы с ней был взят из предыдущих работ без изменений. 

Выполнить задание из лабораторной работы 1, но с использованием REST-сервиса:

1. Реализовать возможность поиска по любым комбинациям полей с помощью REST-сервиса. Данные для поиска должны передаваться в метод сервиса в качестве аргументов.
2. Веб-сервис реализовать в виде standalone-приложения. 
3. Для демонстрации сервисов следует также разработать клиентское консольное приложение.

## Ход работы

За основу возьмем подготовленный в предыдущих работах код и базу данных. Таким образом в класс Student мы добавляем только аннотацию `@XmlRootElement`. Класс ConnectionUtil оставляем без изменений. В классе PostgreSQLDAO в методе getStudentsByFields принимаем один аргумент `name`, по которому будем производить поиск в БД и выводить результат перед реализацией поиска по любым комбинациям полей. 

В pom.xml добавляем зависимости:

```xml
    <dependencies>
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-server</artifactId>
            <version>1.19.4</version>
        </dependency>
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-servlet</artifactId>
            <version>1.19.4</version>
        </dependency>
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-grizzly2</artifactId>
            <version>1.19.4</version>
        </dependency>
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-json</artifactId>
            <version>1.19.4</version>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.20</version>
        </dependency>
    </dependencies>
```

В соответствии с методическим пособием создаем класс App. Затем добавляем класс StudentResource:

```java
@Path("/students")
@Produces({MediaType.APPLICATION_JSON})

public class StudentResource {
    @GET
    public LinkedHashSet<Student> getStudents(@QueryParam("name") String name) {
        LinkedHashSet<Student> students = new PostgreSQLDAO().getStudentsByFields(name);
        return students;
    }
}
```

Теперь при запросе в браузере (после записку сервиса, соответственно):

```http
http://localhost:8080/rest/students?name=Jim
```

Получаем следующий ответ в JSON:

```json
{"student":{"age":"26","mark":"good","name":"Jim","student_id":"234548","surname":"Carrey"}}
```

После этого приступаем к реализации поиска по комбинациям полей.







## Реализация клиентского приложения



