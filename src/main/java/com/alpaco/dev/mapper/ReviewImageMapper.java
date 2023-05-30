package com.alpaco.dev.mapper;

import com.alpaco.dev.entity.Reservation;
import com.alpaco.dev.entity.ReviewImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ReviewImageMapper {
    void save(@Param("reviewImage") ReviewImage reviewImage);
}
