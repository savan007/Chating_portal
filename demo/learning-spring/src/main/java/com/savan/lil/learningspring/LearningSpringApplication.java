package com.savan.lil.learningspring;

import com.savan.lil.learningspring.data.entity.Guest;
import com.savan.lil.learningspring.data.entity.Reservation;
import com.savan.lil.learningspring.data.entity.Room;
import com.savan.lil.learningspring.data.repository.GuestRepository;
import com.savan.lil.learningspring.data.repository.ReservationRepository;
import com.savan.lil.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class  LearningSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringApplication.class, args);
    }

/*    @RestController
    @RequestMapping("/rooms")
    public class RoomController{
        @Autowired
        private RoomRepository roomRepository;

        @GetMapping
        public Iterable<Room> getRooms(){
            return this.roomRepository.findAll();
        }
    }
    @RestController
    @RequestMapping("/guest")
    public class GuestConrolller{
        @Autowired
        private GuestRepository guestRepository;

        @GetMapping
        public Iterable<Guest> getGuest(){
            return this.guestRepository.findAll();
        }
    }

    @RestController
    @RequestMapping("/reservation")
    public class ReservationConrolller{
        @Autowired
        private ReservationRepository reservationRepository;

        @GetMapping
        public Iterable<Reservation> getReservation(){
            return this.reservationRepository.findAll();
        }
    }*/
}
