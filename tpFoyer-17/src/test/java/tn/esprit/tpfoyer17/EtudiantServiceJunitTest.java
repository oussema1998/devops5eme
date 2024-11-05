package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.services.impementations.EtudiantService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@SpringBootTest

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class EtudiantServiceJunitTest {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EtudiantService etudiantService;

    @Test
    @Order(1)
    public void testAddEtudiant() {
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("John")
                .prenomEtudiant("Doe")
                .cinEtudiant(12345678L)
                .build();

        Etudiant savedEtudiant = etudiantService.addEtudiants(List.of(etudiant)).get(0);

        assertNotNull(savedEtudiant);
        assertEquals("John", savedEtudiant.getNomEtudiant());
        assertEquals("Doe", savedEtudiant.getPrenomEtudiant());

        Etudiant foundEtudiant = etudiantRepository.findById(savedEtudiant.getIdEtudiant()).orElse(null);
        assertNotNull(foundEtudiant);
        assertEquals("John", foundEtudiant.getNomEtudiant());
        assertEquals("Doe", foundEtudiant.getPrenomEtudiant());

        log.info("Test addEtudiant succeeded for etudiant: {}", savedEtudiant);
    }

    @Test
    @Order(2)
    public void testRetrieveAllEtudiants() {
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Jane")
                .prenomEtudiant("Smith")
                .cinEtudiant(87654321L)
                .build();
        etudiantService.addEtudiants(List.of(etudiant));

        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        assertNotNull(etudiants);
        assertTrue(etudiants.size() > 0, "Expected at least one etudiant in the database");

        log.info("Test retrieveAllEtudiants succeeded, number of etudiants retrieved: {}", etudiants.size());
    }

    @Test
    @Order(3)
    public void testRetrieveEtudiant() {
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Paul")
                .prenomEtudiant("Anderson")
                .cinEtudiant(11223344L)
                .build();
        etudiant = etudiantService.addEtudiants(List.of(etudiant)).get(0);

        Etudiant foundEtudiant = etudiantService.retrieveEtudiant(etudiant.getIdEtudiant());

        assertNotNull(foundEtudiant);
        assertEquals("Paul", foundEtudiant.getNomEtudiant());
        assertEquals("Anderson", foundEtudiant.getPrenomEtudiant());

        log.info("Test retrieveEtudiant succeeded for etudiant: {}", foundEtudiant);
    }

    @Test
    @Order(4)
    public void testUpdateEtudiant() {
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Alice")
                .prenomEtudiant("Brown")
                .cinEtudiant(44556677L)
                .build();
        etudiant = etudiantService.addEtudiants(List.of(etudiant)).get(0);

        etudiant.setNomEtudiant("Alice Updated");
        etudiant.setCinEtudiant(99887766L);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertNotNull(updatedEtudiant);
        assertEquals("Alice Updated", updatedEtudiant.getNomEtudiant());
        assertEquals(99887766L, updatedEtudiant.getCinEtudiant());

        log.info("Test updateEtudiant succeeded for updated etudiant: {}", updatedEtudiant);
    }

    @Test
    @Order(5)
    public void testRemoveEtudiant() {
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Charlie")
                .prenomEtudiant("Delta")
                .cinEtudiant(22334455L)
                .build();
        etudiant = etudiantService.addEtudiants(List.of(etudiant)).get(0);
        long idEtudiant = etudiant.getIdEtudiant();

        etudiantService.removeEtudiant(idEtudiant);

        boolean exists = etudiantRepository.existsById(idEtudiant);
        assertFalse(exists, "Etudiant should no longer exist in the database");

        log.info("Test removeEtudiant succeeded for etudiant ID: {}", idEtudiant);
    }

    /*@Transactional
    @Test
    @Order(6)
    public void testFindByReservationsAnneeUniversitaire() {
        // Prepare data: Create an Etudiant with a reservation for the current academic year
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("Jane")
                .prenomEtudiant("Doe")
                .cinEtudiant(87654321L)
                .build();

        // Create a reservation for the current academic year
        Reservation reservation = new Reservation();
        reservation.setAnneeUniversitaire(LocalDate.of(LocalDate.now().getYear(), 1, 1)); // Set to January 1st of the current year
        reservation.setEstValide(true);

        // Link Etudiant and Reservation
        etudiant.setReservations(Set.of(reservation));
        reservation.setEtudiants(Set.of(etudiant));

        // Save Etudiant and Reservation (CascadeType.ALL should handle it)
        etudiantRepository.save(etudiant);

        // Debug: Ensure Etudiant and Reservation were saved
        Etudiant savedEtudiant = etudiantRepository.findById(etudiant.getIdEtudiant()).orElse(null);
        assertNotNull(savedEtudiant, "Etudiant should be saved in the database");
        assertTrue(savedEtudiant.getReservations().size() > 0, "Etudiant should have at least one reservation");

        // Call the service method
        List<Etudiant> etudiantsWithReservations = etudiantService.findByReservationsAnneeUniversitaire();

        // Verify the result
        assertNotNull(etudiantsWithReservations, "The result should not be null");
        assertTrue(etudiantsWithReservations.stream().anyMatch(e -> e.getNomEtudiant().equals("Jane")),
                "Expected to find an etudiant with a reservation in the current academic year");

        log.info("Test findByReservationsAnneeUniversitaire succeeded, etudiants found: {}", etudiantsWithReservations.size());
    }*/
}

