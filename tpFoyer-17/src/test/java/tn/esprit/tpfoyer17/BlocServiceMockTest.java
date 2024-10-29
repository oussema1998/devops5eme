package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.impementations.BlocService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlocServiceMockTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocService blocService;

    private Bloc bloc;

    @BeforeEach
    void setUp() {
        // Initialisation de l'objet Bloc pour les tests
        bloc = Bloc.builder()
                .idBloc(1L)
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();
    }

    @Test
    void testRetrieveBlocs() {
        // Arrange : on simule la méthode findAll de blocRepository
        when(blocRepository.findAll()).thenReturn(Arrays.asList(bloc));

        // Act : on appelle la méthode à tester
        List<Bloc> blocs = blocService.retrieveBlocs();

        // Assert : on vérifie le résultat attendu
        assertEquals(1, blocs.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void testUpdateBloc() {
        // Arrange
        when(blocRepository.save(bloc)).thenReturn(bloc);

        // Act
        Bloc updatedBloc = blocService.updateBloc(bloc);

        // Assert
        assertNotNull(updatedBloc);
        assertEquals(bloc.getIdBloc(), updatedBloc.getIdBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testAddBloc() {
        // Arrange
        when(blocRepository.save(bloc)).thenReturn(bloc);

        // Act
        Bloc addedBloc = blocService.addBloc(bloc);

        // Assert
        assertNotNull(addedBloc);
        assertEquals(bloc.getIdBloc(), addedBloc.getIdBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testRetrieveBloc() {
        // Arrange
        long idBloc = 1L;
        when(blocRepository.findById(idBloc)).thenReturn(Optional.of(bloc));

        // Act
        Bloc foundBloc = blocService.retrieveBloc(idBloc);

        // Assert
        assertNotNull(foundBloc);
        assertEquals(idBloc, foundBloc.getIdBloc());
        verify(blocRepository, times(1)).findById(idBloc);
    }

    @Test
    void testRemoveBloc() {
        // Arrange
        long idBloc = 1L;
        doNothing().when(blocRepository).deleteById(idBloc);

        // Act
        blocService.removeBloc(idBloc);

        // Assert
        verify(blocRepository, times(1)).deleteById(idBloc);
    }

    @Test
    void testFindByFoyerIdFoyer() {
        // Arrange
        long idFoyer = 1L;
        when(blocRepository.findByFoyerIdFoyer(idFoyer)).thenReturn(Arrays.asList(bloc));

        // Act
        List<Bloc> blocs = blocService.findByFoyerIdFoyer(idFoyer);

        // Assert
        assertEquals(1, blocs.size());
        verify(blocRepository, times(1)).findByFoyerIdFoyer(idFoyer);
    }

    @Test
    void testFindByChambresIdChambre() {
        // Arrange
        long idChambre = 1L;
        when(blocRepository.findByChambresIdChambre(idChambre)).thenReturn(bloc);

        // Act
        Bloc foundBloc = blocService.findByChambresIdChambre(idChambre);

        // Assert
        assertNotNull(foundBloc);
        assertEquals(bloc.getIdBloc(), foundBloc.getIdBloc());
        verify(blocRepository, times(1)).findByChambresIdChambre(idChambre);
    }
}