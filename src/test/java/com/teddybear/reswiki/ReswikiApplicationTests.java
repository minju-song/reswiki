package com.teddybear.reswiki;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReswikiApplicationTests {
	@Value("${jasypt_encryptor_password}")
	private String password;

	@Test
	void contextLoads() {
		String url = "jdbc:mariadb://svc.sel4.cloudtype.app:31531/reswiki";
		String username = "root";
		String pass = "root";

		System.out.println("url - "+jasyptEncoding(url));
		System.out.println("user - "+jasyptEncoding(username));
	}

	public String jasyptEncoding(String value) {
		String key = password;
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
		encryptor.setPassword(key);
		encryptor.setIvGenerator(new RandomIvGenerator());
		return encryptor.encrypt(value);
	}

}
