package com.alpaco.dev.mapper;

import com.alpaco.dev.dto.review.ReviewListResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewListResponseDto> findAllDesc(int roomId, int limit, int offset);
}
