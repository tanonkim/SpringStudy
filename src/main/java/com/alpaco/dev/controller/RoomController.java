package com.alpaco.dev.controller;

import com.alpaco.dev.dto.room.RoomListResponseDto;
import com.alpaco.dev.service.RoomService;
import com.alpaco.dev.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class RoomController {
    private final RoomService roomService;


    @GetMapping("/rooms")
    public BaseResponse<List<RoomListResponseDto>> get(@RequestParam(required = false, defaultValue = "ID_DESC") String sort) {

        String orderKind = sort;

        List<RoomListResponseDto> listPageInfo = roomService.findAllDesc(orderKind);
        return new BaseResponse<>(listPageInfo);
    }
}
