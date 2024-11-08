package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.FoyerService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FoyerServiceTest {

    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private BlocRepository blocRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private FoyerService foyerService;

    private Foyer foyer;
    private Universite universite;

    @BeforeEach
    public void setUp() {
        // Initialiser les mocks
        MockitoAnnotations.openMocks(this);

        // Créer un objet Foyer et Universite pour les tests
        foyer = new Foyer();
        foyer.setNomFoyer("Foyer 1"); // Utilisez setNomFoyer au lieu de setName
        foyer.setBlocs(new HashSet<>()); // Initialiser l'ensemble de blocs pour éviter NullPointerException

        universite = new Universite();
        universite.setNomUniversite("Université 1"); // Assurez-vous de setter un nom pour l'université

        // Mocker le comportement des méthodes
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);
    }

    @Test
    public void testAddFoyer() {
        // Test l'ajout d'un foyer
        Foyer result = foyerService.addFoyer(foyer);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("Foyer 1", result.getNomFoyer()); // Utilisez getNomFoyer
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    public void testRetrieveFoyer() {
        // Test la récupération d'un foyer par son ID
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.retrieveFoyer(1L);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("Foyer 1", result.getNomFoyer()); // Utilisez getNomFoyer
        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    public void testRemoveFoyer() {
        // Test de suppression d'un foyer
        doNothing().when(foyerRepository).deleteById(1L);

        foyerService.removeFoyer(1L);

        // Vérification que la méthode de suppression a bien été appelée
        verify(foyerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testAjouterFoyerEtAffecterAUniversite() {
        // Test de l'ajout d'un foyer et de son affectation à une université
        Foyer result = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, 1L);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("Foyer 1", result.getNomFoyer()); // Utilisez getNomFoyer
        verify(foyerRepository, times(1)).save(foyer);
        verify(universiteRepository, times(1)).save(universite);
    }
}
