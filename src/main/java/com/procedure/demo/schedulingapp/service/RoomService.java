package com.procedure.demo.schedulingapp.service;

import com.procedure.demo.schedulingapp.entity.Room;
import com.procedure.demo.schedulingapp.reposotiry.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomService {
    @Autowired
    RoomRepo roomRepo;

    /**
     * A method that returns a list of all available rooms
     *
     * @return
     */
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

}
