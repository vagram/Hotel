package com.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
@Entity
@Where(clause = "soft_delete_book is null")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "check_in")
    private Date checkIn;
    @Column(name = "check_out")
    private Date checkOut;
    @Column(name = "active_booking")
    private boolean activeBooking;
    @Column(name = "soft_delete_book")
    private Date softDelBook;

}
