package com.rbs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "postconstructenabled=false" })
class CaseStudyApplicationTests {

	@Test
	void contextLoads() {
	}
	@PostConstruct
	private void initDb() {}

}
