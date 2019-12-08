package com.procedure.demo.schedulingapp;

import com.procedure.demo.schedulingapp.guiViews.SchedulingappUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.testfx.framework.junit.ApplicationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class SchedulingappApplicationTests extends ApplicationTest {

	@BeforeAll
	static void beforeAll() throws Exception {
		ApplicationTest.launch(SchedulingappUI.class);
	}

	@Test
	void contextLoads() {
	}

}
