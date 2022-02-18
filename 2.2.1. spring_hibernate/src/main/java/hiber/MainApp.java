package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

//1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.
//2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
//3. Добавьте этот класс в настройки hibernate.
//4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
//5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Юрий", "Гагарин", "gagarin@mail.su", new Car("ЗИЛ", 2130)));
        userService.add(new User("Сергей", "Королёв", "korolyov@mail.su", new Car("УАЗ", 64)));
        userService.add(new User("Михаил", "Калашников", "kalashnikov@mail.su", new Car("ВАЗ", 2311)));
        userService.add(new User("Павел", "Корчагин", "korchagin@mail.su", new Car("ПАЗ", 5620)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.addByCar("ЗИЛ", 2130));

        context.close();
    }
}
