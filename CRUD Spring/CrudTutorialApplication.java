package com.example.CRUD_Tutorial;

import com.example.CRUD_Tutorial.Entity.Menu;
import com.example.CRUD_Tutorial.Entity.OrderStatus;
import com.example.CRUD_Tutorial.Entity.User;
import com.example.CRUD_Tutorial.Entity.UserRole;
import com.example.CRUD_Tutorial.Repository.UserRepository;
import com.example.CRUD_Tutorial.Service.MenuService;
import com.example.CRUD_Tutorial.Service.OrderService;
import com.example.CRUD_Tutorial.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class CrudTutorialApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderService orderService;


	public static void main(String[] args) {
		SpringApplication.run(CrudTutorialApplication.class, args);



	}

	@Bean
	public CommandLineRunner demo(UserService userService, MenuService menuService, PasswordEncoder passwordEncoder) {
		return (args) -> {

			User user = new User();
			user.setUsername("oana2002");
			user.setPassword("oana2002");
			user.setRole(UserRole.ADMINISTRATOR);
			//userService.save(user);

			User user2 = new User();
			user2.setUsername("ioana2002");
			user2.setPassword("ioana2002");
			user2.setRole(UserRole.EMPLOYEE);
			//userService.save(user2);

			String imagePath = "path/to/your/image.jpg";

			byte[] imageBytes = readImageBytes(imagePath);
			Menu menu1 = new Menu();
			menu1.setName("Pizza Margherita");
			menu1.setPrice(25.0f);
			menu1.setStock(10);

			Menu menu2 = new Menu();
			menu2.setName("Pasta Carbonara");
			menu2.setPrice(18.0f);
			menu2.setStock(15);

			Menu menu3 = new Menu();
			menu3.setName("Pizza Romana");
			menu3.setPrice(18.0f);
			menu3.setStock(20);

			Menu menu4 = new Menu();
			menu4.setName("Pizza Prosciutto e funghi");
			menu4.setPrice(19.0f);
			menu4.setStock(22);

			Menu menu5 = new Menu();
			menu5.setName("Pizza ai quattro formaggi");
			menu5.setPrice(20.0f);
			menu5.setStock(18);

			Menu menu6 = new Menu();
			menu6.setName("Fettucine all'Alfredo.");
			menu6.setPrice(22.0f);
			menu6.setStock(25);


			Menu menu7 = new Menu();
			menu7.setName("Spaghete alla Norma");
			menu7.setPrice(23.0f);
			menu7.setStock(19);

			Menu menu8 = new Menu();
			menu8.setName("Burger");
			menu8.setPrice(25.0f);
			menu8.setStock(90);



			List<Integer> menuIds = new ArrayList<>();
			menuIds.add(4);
			menuIds.add(4);
			menuIds.add(5);
			//orderService.createOrderWithMenus(4, menuIds);

			//orderService.updateOrderStatus(1, OrderStatus.NEW);

			/*menuService.saveMenu(menu8);
			menuService.saveMenu(menu1);
			menuService.saveMenu(menu2);
			menuService.saveMenu(menu3);
			menuService.saveMenu(menu4);
			menuService.saveMenu(menu5);
			menuService.saveMenu(menu6);
			menuService.saveMenu(menu7);*/

	};

}
	private byte[] readImageBytes(String imagePath) {
		Path path = Paths.get(imagePath);
		if (!Files.exists(path)) {
			System.err.println("Fișierul de imagine nu există la calea specificată: " + imagePath);
			return null;
		}
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			System.err.println("Eroare la citirea fișierului de imagine: " + e.getMessage());
			return null;
		}
	}


}
