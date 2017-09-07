package calaru;

import java.io.File;
import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("calaru.repository")
public class MiGireWebBackConfiguration extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MiGireWebBackConfiguration.class).properties(getProperties());
	}

	public static void main(String[] args) {
		SpringApplicationBuilder spb = new SpringApplicationBuilder(MiGireWebBackConfiguration.class);
		spb.properties(getProperties()).run(args);
	}

	static Properties getProperties() {
		File f1 = new File("/etc/calaru/back/");
		File f2 = new File("calaru/back/");
		File f3 = new File("application.properties");
		if(!f1.exists() && !f2.exists() && !f3.exists())
			throw new IllegalStateException("Mal configurado! Falta el archivo de configuracion");
		Properties props = new Properties();
		props.put("spring.config.location", "file:calaru/back/,file:/etc/calaru/back/");
		return props;
	}
}
