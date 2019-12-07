package com.procedure.demo.schedulingapp.reposotiry;

import com.procedure.demo.schedulingapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Integer>, JpaSpecificationExecutor<Room> {


    @Query("select r from Room r where lower(r.name) like lower(concat('%', :nameToFind,'%'))")
    public List<Room> findRoomsByCustomSearch(@Param("nameToFind") String name);

    public Room findByName(String name);
}
