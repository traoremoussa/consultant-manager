https://www.youtube.com/watch?v=2MBhxk2chhM&ab_channel=AutomationStepbyStep

https://docs.gitlab.com/runner/register/


<!--    junit 5  start-->
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>5.9.2</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<version>5.9.2</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>5.3.3</version>
			<scope>test</scope>
		</dependency>
		<!--For effective asserting of results, we'll also use Hamcrest and JSON path: -->
		<dependency>
			<groupId>org.hamcrest</groupId>
			<artifactId>hamcrest-library</artifactId>
			<version>2.2</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.jayway.jsonpath</groupId>
			<artifactId>json-path</artifactId>
			<version>2.5.0</version>
			<scope>test</scope>
		</dependency>
		<!--    junit 5  end-->
		
		
		spring-boot-starter-test >2.2.0 comes with Junit 5, so no need for this if you use the most recent version of Spring Boot (or of spring-boot-starter-web).