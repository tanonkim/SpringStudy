package com.alpaco.dev.service;

import com.alpaco.dev.dto.room.RoomListResponseDto;
import com.alpaco.dev.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {
    private final RoomMapper roomMapper;

    public List<RoomListResponseDto> findAllDesc(String orderKind) {
        List<RoomListResponseDto> allDesc = roomMapper.findAllDesc(orderKind);

        return allDesc;
    }
}
