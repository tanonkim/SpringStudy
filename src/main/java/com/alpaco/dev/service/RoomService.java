package com.alpaco.dev.service;

import com.alpaco.dev.dto.room.RoomListResponseDto;
import com.alpaco.dev.mapper.RoomMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
