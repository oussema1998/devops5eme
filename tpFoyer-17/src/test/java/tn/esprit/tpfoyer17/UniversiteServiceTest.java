package tn.esprit.tpfoyer17;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.UniversiteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private UniversiteService universiteService;

    private Universite universite;
    private Foyer foyer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        universite = Universite.builder()
                .idUniversite(1L)
                .nomUniversite("Université Exemple")
                .adresse("Adresse Exemple")
                .build();

        foyer = new Foyer();
        foyer.setNomFoyer("Foyer Exemple");
        foyer.setCapaciteFoyer(100);
    }

    @Test
    void testRetrieveAllUniversities() {
        List<Universite> universities = new ArrayList<>();
        universities.add(universite);

        when(universiteRepository.findAll()).thenReturn(universities);

        List<Universite> result = universiteService.retrieveAllUniversities();

        assertEquals(1, result.size());
        assertEquals(universite.getNomUniversite(), result.get(0).getNomUniversite());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversity() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversity(universite);

        assertNotNull(result);
        assertEquals(universite.getNomUniversite(), result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testUpdateUniversity() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversity(universite);

        assertNotNull(result);
        assertEquals(universite.getNomUniversite(), result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversity() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversity(1L);

        assertNotNull(result);
        assertEquals(universite.getNomUniversite(), result.getNomUniversite());
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testDesaffecterFoyerAUniversite() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.desaffecterFoyerAUniversite(1L);

        assertNotNull(result);
        assertNull(result.getFoyer());
        verify(universiteRepository, times(1)).findById(1L);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testAffecterFoyerAUniversite() {
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));
        when(universiteRepository.findByNomUniversiteLike("Université Exemple")).thenReturn(universite);
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.affecterFoyerAUniversite(1L, "Université Exemple");

        assertNotNull(result);
        assertEquals(foyer, result.getFoyer());
        verify(foyerRepository, times(1)).findById(1L);
        verify(universiteRepository, times(1)).findByNomUniversiteLike("Université Exemple");
        verify(universiteRepository, times(1)).save(universite);
    }
}