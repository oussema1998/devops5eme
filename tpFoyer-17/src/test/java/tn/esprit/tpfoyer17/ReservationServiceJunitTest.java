package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpfoyer17.entities.Chambre; // Import for Chambre entity
import tn.esprit.tpfoyer17.entities.Etudiant; // Import for Etudiant entity
import tn.esprit.tpfoyer17.entities.Reservation; // Import for Reservation entity
import tn.esprit.tpfoyer17.repositories.ReservationRepository; // Import for Reservation repository
import tn.esprit.tpfoyer17.repositories.EtudiantRepository; // Import for Etudiant repository
import tn.esprit.tpfoyer17.repositories.ChambreRepository; // Import for Chambre repository
import tn.esprit.tpfoyer17.services.impementations.ReservationService; // Import for ReservationService
import tn.esprit.tpfoyer17.services.impementations.EtudiantService; // Import for EtudiantService
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class ReservationServiceJunitTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

//    @Test
//    @Order(1)
//    public void testAddReservation() {
//        // Create and save an Etudiant
//        Etudiant etudiant = Etudiant.builder()
//                .nomEtudiant("John Doe")
//                .cinEtudiant(123456)
//                .build();
//        etudiantRepository.save(etudiant);
//
//        // Create a Bloc and Chambre
//        Chambre chambre = Chambre.builder()
//                .numeroChambre(101)
//                .typeChambre(TypeChambre.SIMPLE)
//                .build();
//        chambreRepository.save(chambre);
//
//        // Now create a Reservation
//        Reservation savedReservation = reservationService.ajouterReservation(chambre.getIdChambre(), etudiant.getCinEtudiant());
//
//        assertNotNull(savedReservation);
//        assertTrue(savedReservation.getEtudiants().contains(etudiant), "Expected the reservation to contain the student");
//
//        // Ensure the reservation can be found by its ID
//        Reservation foundReservation = reservationRepository.findById(savedReservation.getIdReservation()).orElse(null);
//        assertNotNull(foundReservation);
//        assertTrue(foundReservation.getEtudiants().contains(etudiant), "Expected the found reservation to contain the student");
//
//        log.info("Test addReservation succeeded for reservation: {}", savedReservation);
//    }

    @Test
    @Order(2)
    public void testRetrieveReservations() {
        List<Reservation> reservations = reservationService.retrieveAllReservation();

        assertNotNull(reservations);
        assertTrue(reservations.size() > 0, "Expected at least one reservation in the database");
        log.info("Test retrieveReservations succeeded, number of reservations retrieved: {}", reservations.size());
    }

//    @Test
//    @Order(3)
//    public void testRetrieveReservation() {
//        Etudiant etudiant = Etudiant.builder()
//                .nomEtudiant("Bob Smith")
//                .cinEtudiant(654321)
//                .build();
//        etudiantRepository.save(etudiant);
//
//        String reservationId = "1-BlocA-2024";
//
//        Reservation reservation = Reservation.builder()
//                .idReservation(reservationId)
//                .anneeUniversitaire(LocalDate.now())
//                .estValide(true)
//                .etudiants(new HashSet<>(Set.of(etudiant)))
//                .build();
//
//        reservationService.ajouterReservation(1L, etudiant.getCinEtudiant());
//
//        Reservation foundReservation = reservationService.retrieveReservation(reservationId);
//
//        assertNotNull(foundReservation);
//        assertTrue(foundReservation.getEtudiants().contains(etudiant), "Expected the reservation to contain the student");
//
//        log.info("Test retrieveReservation succeeded for reservation: {}", foundReservation);
//    }

//    @Test
//    @Order(4)
//    public void testUpdateReservation() {
//        Etudiant etudiant = Etudiant.builder()
//                .nomEtudiant("Alice Brown")
//                .cinEtudiant(789012)
//                .build();
//        etudiantRepository.save(etudiant);
//
//        String reservationId = "1-BlocA-2024";
//
//        Reservation reservation = Reservation.builder()
//                .idReservation(reservationId)
//                .anneeUniversitaire(LocalDate.now())
//                .estValide(true)
//                .etudiants(new HashSet<>(Set.of(etudiant)))
//                .build();
//        reservationService.ajouterReservation(1L, etudiant.getCinEtudiant());
//
//        Reservation existingReservation = reservationService.retrieveReservation(reservationId);
//        assertNotNull(existingReservation);
//
//        existingReservation.setEstValide(false);
//
//        Reservation updatedReservation = reservationService.updateReservation(existingReservation);
//
//        assertNotNull(updatedReservation);
//        assertFalse(updatedReservation.isEstValide(), "Expected reservation to be invalid after update");
//
//        log.info("Test updateReservation succeeded for updated reservation: {}", updatedReservation);
//    }

//    @Test
//    @Order(7)
//    public void testGetReservationCount() {
//        Etudiant etudiant1 = Etudiant.builder()
//                .nomEtudiant("Emily Stone")
//                .cinEtudiant(112233)
//                .build();
//        etudiantRepository.save(etudiant1);
//        reservationService.ajouterReservation(1L, etudiant1.getCinEtudiant());
//
//        Etudiant etudiant2 = Etudiant.builder()
//                .nomEtudiant("Fiona White")
//                .cinEtudiant(445566)
//                .build();
//        etudiantRepository.save(etudiant2);
//        reservationService.ajouterReservation(1L, etudiant2.getCinEtudiant());
//
//        long count = reservationService.retrieveAllReservation().size();
//        assertEquals(2, count, "Expected reservation count should be 2");
//
//        log.info("Test getReservationCount succeeded, current count: {}", count);
//    }
}
