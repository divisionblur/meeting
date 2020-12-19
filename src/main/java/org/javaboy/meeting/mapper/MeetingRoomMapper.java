package org.javaboy.meeting.mapper;

import org.javaboy.meeting.model.MeetingRoom;

import java.util.List;


public interface MeetingRoomMapper {
    List<MeetingRoom> getAllMr();

    MeetingRoom getMrById(Integer roomid);

    Integer updateRoom(MeetingRoom meetingRoom);

    Integer addMr(MeetingRoom meetingRoom);
}
