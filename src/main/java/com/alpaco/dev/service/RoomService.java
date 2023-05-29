package com.alpaco.dev.service;

import com.alpaco.dev.dto.room.RoomDetailResponseDto;
import com.alpaco.dev.dto.room.RoomListResponseDto;
import com.alpaco.dev.mapper.RoomMapper;
import com.alpaco.dev.util.exception.RoomException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alpaco.dev.util.BaseResponseStatus.INACTIVE_ROOM;
import static com.alpaco.dev.util.BaseResponseStatus.NONE_ROOM;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {
    private final RoomMapper roomMapper;

    public Page<List<RoomListResponseDto>> findAllDesc(int pageNo, String orderKind) {
        PageHelper.startPage(pageNo, 5);
        Page<List<RoomListResponseDto>> allDesc = roomMapper.findAllDesc(pageNo, orderKind);

        return allDesc;
    }

    public RoomDetailResponseDto getRoomDetail(Long roomId, Long userId) {

        log.info("roomMapper.findById(roomId)={}", roomMapper.findById(roomId));

        String result = roomMapper.findById(roomId); // 'ACTIVE or INACTIVE'

        if (result == null) throw new RoomException(NONE_ROOM);
        if (result.toUpperCase().equals("INACTIVE")) throw new RoomException(INACTIVE_ROOM);

        RoomDetailResponseDto dto = roomMapper.findByRoomId(roomId);
        log.info("roomDto={}", dto);

        return dto;
    }
}
