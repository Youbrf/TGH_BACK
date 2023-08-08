package be.icc.tgh.service;

import be.icc.tgh.model.Reservation;
import be.icc.tgh.model.User;
import be.icc.tgh.repository.ReservationR;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationS {
    @Autowired
    private ReservationR repo;
    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    @Value("${BASE_URL}")
    private String baseURL;

    public List<Reservation> getAllReservation(){
        return repo.findAll();
    }
    public Reservation getReservationByID(Long id){
        return repo.findById(id).orElse(null);
    }
    public Reservation creerReservation(Reservation reservation){
        reservation.setDateCreation(LocalDate.now());
        reservation.setEtatPaiement("UNPAID");
        reservation.setStatutReservation("PENDING");
        return repo.save(reservation);
    }
    public Reservation updateReservation(Reservation reservation){
        reservation.setDateModification(LocalDate.now());
        return repo.save(reservation);
    }
    public void deleteReservation(Long id){
        repo.deleteById(id);
    }

    public List<Reservation> findReservationByEmployer(Integer id, LocalDate date) {
        return  repo.findReservationByEmployer(id,date);
    }

    public List<Reservation> findByUser(User user) {
        return repo.findByUser(user);
    }

    public List<Reservation> findByEmployer(User user) {
        return repo.findByEmployer(user);
    }

    public Session createSession(Reservation reservation) throws StripeException {
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (be.icc.tgh.model.Service service : reservation.getServices()) {
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(1L)
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("eur")
                                    .setUnitAmount((long) (service.getPrix() * 100))
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(service.getNom())
                                                    .build())
                                    .build())
                    .build();


            lineItems.add(lineItem);
        }

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.BANCONTACT)
                        .setSuccessUrl(baseURL + "success?reservation=" + reservation.getId())
                        .setCustomerEmail(reservation.getUser().getEmail())
                        .addAllLineItem(lineItems)
                        .build();

        return Session.create(params);
    }

}
