package com.procedure.demo.schedulingapp.guiController;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.loadui.testfx.GuiTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.assertions.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class NewPatientViewControllerTest extends ApplicationTest {

    @BeforeAll
    static void beforeAll() throws Exception {
        ApplicationTest.launch(SchedulingappUI.class);

    }


    @Test
    void updateNewPatient() {
        clickOn("#addPatientBtn");
        TextField fullNameField = (TextField) GuiTest.find("#fullNameField");
        fullNameField.setText("John Smith");
        clickOn("#submitButton");
        FxAssert.verifyThat("#alertDialogBtn", NodeMatchers.isEnabled());
        assertThat(targetWindow("Registration Successful!").lookup(".button").queryButton()).hasText("OK");
        clickOn("#alertDialogBtn");
    }

    @Test
    void updateInvalidPatient() {
        clickOn("#submitButton");
        FxAssert.verifyThat("#alertDialogBtn", NodeMatchers.isEnabled());
        assertThat(targetWindow("Error!").lookup(".button").queryButton()).hasText("OK");
        clickOn("#alertDialogBtn");
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


}