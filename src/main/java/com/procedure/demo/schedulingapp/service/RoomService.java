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

    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    public List<Room> findAllRoomsBySearch(String name) {
        return roomRepo.findRoomsByCustomSearch(name);
    }

    public Room findByName(String name) throws Exception {
        return roomRepo.findByName(name);
    }
}
