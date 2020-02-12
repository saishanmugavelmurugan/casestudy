package com.rbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class CaseStudyApplication {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("#{new Boolean('${postconstructenabled}')}")
	public Boolean isPostConstructEnabled;
	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
		String sqlStatements[] = {
				"INSERT INTO Account (id,accountnumber, balance) VALUES  (1,'rbs123456', 2000);",
		        "INSERT INTO Account (id,accountnumber, balance) VALUES   (2,'rbs123457', 1000);",
		        "INSERT INTO Account (id,accountnumber, balance) VALUES   (3,'rbs123458', 200.66);",
				"alter sequence HIBERNATE_SEQUENCE restart with 4"

		};
		if(isPostConstructEnabled) {
			Arrays.asList(sqlStatements).forEach(sql -> {
				jdbcTemplate.execute(sql);
			});
		}

		// Query test data and print results
	}
}

