package AndresFlores_DiegoGabriel_1_B.PruebaFinal;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaFinalApplication.class, args);

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
	}

}
