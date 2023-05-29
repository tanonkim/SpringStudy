package com.alpaco.dev.service;

import com.alpaco.dev.dto.review.ReviewListResponseDto;
import com.alpaco.dev.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public List<ReviewListResponseDto> findAllDesc(int roomId, int limit, int offset) {
        List<ReviewListResponseDto> allDesc = reviewMapper.findAllDesc(roomId, limit, offset);

        return allDesc;
    }
}
