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
import tn.esprit.tpfoyer17.entities.Universite; // Import for Universite entity
import tn.esprit.tpfoyer17.repositories.UniversiteRepository; // Import for Universite repository
import tn.esprit.tpfoyer17.services.impementations.UniversiteService; // Import for UniversiteService

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class UniversiteServiceJUnitTest {

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private UniversiteService universiteService;

    @Test
    @Order(1)
    public void testAddUniversity() {
        Universite universite = Universite.builder()
                .nomUniversite("Université Exemple")
                .adresse("Adresse Exemple")
                .build();

        Universite savedUniversity = universiteService.addUniversity(universite);

        assertNotNull(savedUniversity);
        assertEquals("Université Exemple", savedUniversity.getNomUniversite());
        log.info("Test addUniversity succeeded for university: {}", savedUniversity);
    }

    @Test
    @Order(2)
    public void testRetrieveAllUniversities() {
        List<Universite> universities = universiteService.retrieveAllUniversities();

        assertNotNull(universities);
        assertTrue(universities.size() > 0, "Expected at least one university in the database");
        log.info("Test retrieveAllUniversities succeeded, number of universities retrieved: {}", universities.size());
    }

    @Test
    @Order(3)
    public void testRetrieveUniversity() {
        Universite universite = universiteService.retrieveUniversity(1L); // Assurez-vous que l'ID 1 existe

        assertNotNull(universite);
        assertEquals("Université Exemple", universite.getNomUniversite());
        log.info("Test retrieveUniversity succeeded for university: {}", universite);
    }

    @Test
    @Order(4)
    public void testUpdateUniversity() {
        Universite universite = universiteService.retrieveUniversity(1L); // Assurez-vous que l'ID 1 existe
        universite.setAdresse("Nouvelle Adresse");

        Universite updatedUniversity = universiteService.updateUniversity(universite);

        assertNotNull(updatedUniversity);
        assertEquals("Nouvelle Adresse", updatedUniversity.getAdresse(), "Expected address to be updated");
        log.info("Test updateUniversity succeeded for updated university: {}", updatedUniversity);
    }

//    @Test
//    @Order(5)
//    public void testDeleteUniversity() {
//        Universite universite = universiteService.retrieveUniversity(1L); // Assurez-vous que l'ID 1 existe
//
//        universiteService.deleteUniversity(universite.getIdUniversite());
//
//        Universite deletedUniversity = universiteService.retrieveUniversity(1L);
//        assertNull(deletedUniversity, "Expected the university to be deleted");
//        log.info("Test deleteUniversity succeeded for university ID: {}", universite.getIdUniversite());
//    }
}