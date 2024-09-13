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
		String id = "9c1eb3ec967ca14a10ddab8621bdddef";
		String secret = "E8DxDhZbWlA7Rp3Qvfob5K01WQ554qD3";
		String redirect = "http://localhost:9892/login/oauth2/code/kakao";

		System.out.println("id - "+jasyptEncoding(id));
		System.out.println("secret - "+jasyptEncoding(secret));
		System.out.println("redirect - "+jasyptEncoding(redirect));
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
