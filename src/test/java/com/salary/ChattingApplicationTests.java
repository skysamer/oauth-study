package com.salary;

import com.salary.users.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ChattingApplicationTests {
	@Value("${spring.redis.host}")
	private String redisHost;

	@Test
	public void testRedisHostValue() {
		System.out.println("Redis Host: " + redisHost);
	}

	@Test
	void contextLoads() {
		System.out.println(Provider.valueOf("GOOGLE"));
	}

}
