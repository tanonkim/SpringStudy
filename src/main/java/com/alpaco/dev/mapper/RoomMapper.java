package com.alpaco.dev.mapper;

import com.alpaco.dev.dto.room.RoomListResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    List<RoomListResponseDto> findAllDesc(String orderKind);
}
