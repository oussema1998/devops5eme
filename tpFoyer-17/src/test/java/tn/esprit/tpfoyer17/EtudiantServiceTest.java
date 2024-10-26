package tn.esprit.tpfoyer17;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.services.impementations.EtudiantService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    @BeforeEach
    void setUp() {
        // No need to initialize mocks explicitly since we're using MockitoExtension
    }

    @Test
    void testAddEtudiants() {
        // Step 1: Prepare the data
        Etudiant etudiant1 = Etudiant.builder()
                .nomEtudiant("John")
                .prenomEtudiant("Doe")
                .cinEtudiant(123456789L)
                .dateNaissance(new Date())
                .build();

        Etudiant etudiant2 = Etudiant.builder()
                .nomEtudiant("Jane")
                .prenomEtudiant("Smith")
                .cinEtudiant(987654321L)
                .dateNaissance(new Date())
                .build();

        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);

        // Mock repository behavior
        when(etudiantRepository.saveAll(etudiants)).thenReturn(etudiants);

        // Step 2: Apply the method to test
        List<Etudiant> result = etudiantService.addEtudiants(etudiants);

        // Step 3: Verify the result
        assertEquals(etudiants, result);
        verify(etudiantRepository, times(1)).saveAll(etudiants);
    }

    @Test
    void testAddEtudiants_ExceptionHandling() {
        // Step 1: Prepare the data
        Etudiant etudiant = Etudiant.builder()
                .nomEtudiant("John")
                .prenomEtudiant("Doe")
                .cinEtudiant(123456789L)
                .dateNaissance(new Date())
                .build();

        List<Etudiant> etudiants = Arrays.asList(etudiant);

        // Mock repository behavior to throw an exception
        when(etudiantRepository.saveAll(etudiants)).thenThrow(new RuntimeException("Database error"));

        // Step 2: Apply the method to test and catch exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            etudiantService.addEtudiants(etudiants);
        });

        // Step 3: Verify the exception message
        assertEquals("Database error", exception.getMessage());
        verify(etudiantRepository, times(1)).saveAll(etudiants);
    }
}
