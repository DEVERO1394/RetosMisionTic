package Service;

import Model.Client;
import Model.Reservation;
import Repository.ClientRepository;
import Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int  id){
        return  reservationRepository.getReservation(id);
    }

    public Reservation save (Reservation reservation){
        if(reservation.getIdReservation()==null){
            return reservationRepository.save((reservation));
        }else{
            Optional<Reservation> ReservationEncontrada = getReservation(reservation.getIdReservation());
            if(ReservationEncontrada.isEmpty()){
                return reservationRepository.save(reservation);
            }else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if (reservation.getIdReservation()!=null){
            Optional<Reservation> ReservationEncontrado = getReservation(reservation.getIdReservation());
            if (!ReservationEncontrado.isEmpty()){
                if (reservation.getStatus()!=null){
                    ReservationEncontrado.get().setStatus(reservation.getStatus());
                }
                if (reservation.getStartDate()!=null){
                    ReservationEncontrado.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate()!=null){
                    ReservationEncontrado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                return reservationRepository.save(ReservationEncontrado.get());
            }
        }
        return reservation;
    }
    public boolean delete(int id){
        Boolean respuesta = getReservation(id).map(elemento ->{
            reservationRepository.delete(elemento);
            return true;
        }).orElse(false);

        return respuesta;
    }
}
