package therookies.thanhliem.fresh_foods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import therookies.thanhliem.fresh_foods.security.service.EmailSenderService;

@SpringBootApplication
public class FreshFoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreshFoodsApplication.class, args);

	}


}
