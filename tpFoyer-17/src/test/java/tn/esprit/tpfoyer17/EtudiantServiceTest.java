package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.services.impementations.EtudiantService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        // Initialisation de l'objet Etudiant pour les tests
        etudiant = Etudiant.builder()
                .idEtudiant(1L)
                .nomEtudiant("Doe")
                .prenomEtudiant("John")
                .cinEtudiant(12345678L)
                .dateNaissance(new Date())
                .build();
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Arrange
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant));

        // Act
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(1, etudiants.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiants() {
        // Arrange
        List<Etudiant> etudiants = Arrays.asList(etudiant);
        when(etudiantRepository.saveAll(etudiants)).thenReturn(etudiants);

        // Act
        List<Etudiant> addedEtudiants = etudiantService.addEtudiants(etudiants);

        // Assert
        assertEquals(1, addedEtudiants.size());
        verify(etudiantRepository, times(1)).saveAll(etudiants);
    }

    @Test
    void testUpdateEtudiant() {
        // Arrange
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        // Assert
        assertNotNull(updatedEtudiant);
        assertEquals(etudiant.getIdEtudiant(), updatedEtudiant.getIdEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRetrieveEtudiant() {
        // Arrange
        long idEtudiant = 1L;
        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant foundEtudiant = etudiantService.retrieveEtudiant(idEtudiant);

        // Assert
        assertNotNull(foundEtudiant);
        assertEquals(idEtudiant, foundEtudiant.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(idEtudiant);
    }

    @Test
    void testRemoveEtudiant() {
        // Arrange
        long idEtudiant = 1L;
        doNothing().when(etudiantRepository).deleteById(idEtudiant);

        // Act
        etudiantService.removeEtudiant(idEtudiant);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(idEtudiant);
    }

    @Test
    void testFindByReservationsAnneeUniversitaire() {
        // Arrange
        LocalDate currentYear = LocalDate.now();
        when(etudiantRepository.findByReservationsAnneeUniversitaire(currentYear)).thenReturn(Arrays.asList(etudiant));

        // Act
        List<Etudiant> etudiants = etudiantService.findByReservationsAnneeUniversitaire();

        // Assert
        assertEquals(1, etudiants.size());
        verify(etudiantRepository, times(1)).findByReservationsAnneeUniversitaire(currentYear);
    }
}
