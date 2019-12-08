package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Room;
import com.procedure.demo.schedulingapp.guiController.SchedulingappUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.testfx.framework.junit.ApplicationTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class RoomServiceTest extends ApplicationTest {

    @Autowired
    RoomService roomService;

    @BeforeAll
    static void beforeAll() throws Exception {
        ApplicationTest.launch(SchedulingappUI.class);
    }

    @Test
    void findInitializedRooms() {
        Room room = new Room();
        room.setName("Room A");
        List<Room> result = roomService.getAllRooms();
        assertEquals(result.get(0).getName(), room.getName());
    }

    @Test
    void findNonInitializedDoctors() {
        Room room = new Room();
        room.setName("ICU-4");
        List<Room> result = roomService.getAllRooms();
        assertNotEquals(result.get(0).getName(), room.getName());
    }
}