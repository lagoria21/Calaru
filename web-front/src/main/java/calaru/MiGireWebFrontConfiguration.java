package calaru;

import java.io.File;
import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("calaru.repository")
public class MiGireWebFrontConfiguration extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MiGireWebFrontConfiguration.class).properties(getProperties());
	}

	public static void main(String[] args) {
		SpringApplicationBuilder spb = new SpringApplicationBuilder(MiGireWebFrontConfiguration.class);
		spb.properties(getProperties()).run(args);
	}

	static Properties getProperties() {
		File f1 = new File("/etc/calaru/front/");
		File f2 = new File("calaru/front/");
		File f3 = new File("application.properties");
		if(!f1.exists() && !f2.exists() && !f3.exists())
			throw new IllegalStateException("Mal configurado! Falta el archivo de configuracion");
		Properties props = new Properties();
		props.put("spring.config.location", "file:calaru/front/,file:/etc/calaru/front/");
		return props;
	}
}