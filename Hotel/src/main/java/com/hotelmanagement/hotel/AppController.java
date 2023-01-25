package com.hotelmanagement.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class AppController {

    @Autowired
    private hotelReservationRepository hotelReservationRepository;

    @GetMapping("/allReservations")
    public Iterable<HotelReservation> getHotelReservasions(){
        return hotelReservationRepository.findAll();
    }

    @PostMapping("newReservation")
    public HotelReservation addReservation(@RequestBody Map<String,String> body){
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setPersonsName(body.get("personsName"));
        hotelReservation.setDate(body.get("Date"));
        hotelReservation.setDaysReserved(Integer.valueOf(body.get("daysReserved")));
        return hotelReservationRepository.save(hotelReservation);
    }

    @GetMapping("/find/{id}")
    public Optional<HotelReservation> findByID(@PathVariable Integer id){
        return hotelReservationRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteByID(@PathVariable Integer id){
        hotelReservationRepository.deleteById(id);
        return "Deleted!";
    }

    @PutMapping("/updateCase")
        public String updateHotelReservation(@RequestBody Map<String,String> body){
            Optional<HotelReservation> hotelReservationOptional
                    = hotelReservationRepository.findById(Integer.parseInt(body.get("id")));
            if(hotelReservationOptional.isPresent()){
             HotelReservation hotelReservation = hotelReservationOptional.get();
                hotelReservation.setPersonsName(body.get("personsName"));
                hotelReservation.setDate(body.get("Date"));
                hotelReservation.setDaysReserved(Integer.valueOf(body.get("daysReserved")));
                hotelReservationRepository.save(hotelReservation);
            }else return "The provided id doesn't exist...";


            return "hh";

    }
}
