package calaru.config;

import java.util.ArrayList;

//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//import org.jasypt.encryption.pbe.config.SimplePBEConfig;
//import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPAConfigBase {

	public String getPropertyValue(String originalValue, String decryptionKey) {
		String retValue = originalValue;
		return retValue;
	}

	@Bean(name = "girelib.encryptionPassword")
	public String getEncryptionPassword() {
		return "";
	}

	@Bean(name = "gireJndiLocations")
	public ArrayList<String> getGireJndiLocations() {
		return new ArrayList<String>();
	}
}
