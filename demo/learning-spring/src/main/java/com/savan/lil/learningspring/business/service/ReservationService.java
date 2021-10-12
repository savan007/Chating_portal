package com.savan.lil.learningspring.business.service;

import com.savan.lil.learningspring.business.domain.RoomReservation;
import com.savan.lil.learningspring.data.entity.Guest;
import com.savan.lil.learningspring.data.entity.Reservation;
import com.savan.lil.learningspring.data.entity.Room;
import com.savan.lil.learningspring.data.repository.GuestRepository;
import com.savan.lil.learningspring.data.repository.ReservationRepository;
import com.savan.lil.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    //dependency injection
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime() ));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });

        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long id: roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(id));
        }

        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {

                if(o1.getRoomNumber() == o2.getRoomNumber()){
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber()) ;
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }
}
