package com.alpaco.dev.mapper;

import com.alpaco.dev.dto.user.UserSignUpRequestDto;
import com.alpaco.dev.entity.Reservation;
import com.alpaco.dev.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ReservationMapper {
    Optional<Reservation> findByIdAndUserId(Long reservationId, Long userId);
    void updateReviewStatus(Long reservationId);
}
