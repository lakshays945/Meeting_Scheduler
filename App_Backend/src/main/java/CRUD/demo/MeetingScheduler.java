package CRUD.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication

public class MeetingScheduler {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(MeetingScheduler.class, args);
		DatabaseConnectionService databaseConnectionService = context.getBean(DatabaseConnectionService.class);
		if(databaseConnectionService.isDatabaseConnected()){
			System.out.println("DataBase connection is successful");
		}
		else{
			System.out.println("DataBase connection is unsuccessful");
		}
	}
}
