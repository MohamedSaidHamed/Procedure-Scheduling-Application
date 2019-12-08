package com.procedure.demo.schedulingapp.controller;

import com.procedure.demo.schedulingapp.entity.Room;
import com.procedure.demo.schedulingapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomController {

    @Autowired
    RoomService roomService;

    /**
     * A method that returns a list of all available rooms
     * @return
     */
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
