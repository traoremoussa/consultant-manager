package com.kodiatech.traore;

import com.kodiatech.traore.config.ApplicationConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class AuthenticationControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;



    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


}
//TODO --> https://www.baeldung.com/integration-testing-in-spring
//TODO Mockito.mock() vs @Mock vs @MockBean
//---> https://www.baeldung.com/java-spring-mockito-mock-mockbean
