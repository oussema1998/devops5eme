package tn.esprit.tpfoyer17.services.impementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private ChambreService chambreService;

    private Chambre chambre;

    @BeforeEach
    public void setUp() {
        chambre = Chambre.builder()
                .numeroChambre(101)
                .typeChambre(TypeChambre.SIMPLE)
                .build();
    }

    @Test
    public void testAddChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre savedChambre = chambreService.addChambre(chambre);

        assertEquals(chambre, savedChambre);
        verify(chambreRepository).save(chambre);
    }

    @Test
    public void testRetrieveAllChambres() {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(chambre);

        when(chambreRepository.findAll()).thenReturn(chambres);

        List<Chambre> retrievedChambres = chambreService.retrieveAllChambres();

        assertEquals(chambres, retrievedChambres);
        verify(chambreRepository).findAll();
    }

    @Test
    public void testUpdateChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre updatedChambre = chambreService.updateChambre(chambre);

        assertEquals(chambre, updatedChambre);
        verify(chambreRepository).save(chambre);
    }

    @Test
    public void testRetrieveChambre() {
        long idChambre = 1L;
        when(chambreRepository.findById(idChambre)).thenReturn(Optional.of(chambre));

        Chambre retrievedChambre = chambreService.retrieveChambre(idChambre);

        assertEquals(chambre, retrievedChambre);
        verify(chambreRepository).findById(idChambre);
    }

    @Test
    public void testFindByTypeChambre() {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(chambre);

        when(chambreRepository.findByTypeChambreAndReservationsEstValide(TypeChambre.DOUBLE, true)).thenReturn(chambres);

        List<Chambre> result = chambreService.findByTypeChambre();

        assertEquals(chambres, result);
        verify(chambreRepository).findByTypeChambreAndReservationsEstValide(TypeChambre.DOUBLE, true);
    }

    @Test
    public void testAffecterChambresABloc() {
        long idBloc = 1L;
        List<Long> numChambres = List.of(101L, 102L);
        Bloc bloc = new Bloc();

        List<Chambre> chambres = new ArrayList<>();
        chambres.add(chambre);

        when(blocRepository.findById(idBloc)).thenReturn(Optional.of(bloc));
        when(chambreRepository.findByNumeroChambreIn(numChambres)).thenReturn(chambres);

        Bloc affectedBloc = chambreService.affecterChambresABloc(numChambres, idBloc);

        assertEquals(bloc, affectedBloc);
        verify(blocRepository).findById(idBloc);
        verify(chambreRepository).findByNumeroChambreIn(numChambres);
        verify(chambreRepository).save(chambre);
    }

    @Test
    public void testGetChambresNonReserveParNomUniversiteEtTypeChambre() {
        String nomUniversite = "Esprit";
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(chambre);

        when(chambreRepository.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, typeChambre)).thenReturn(chambres);

        List<Chambre> result = chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, typeChambre);

        assertEquals(chambres, result);
        verify(chambreRepository).getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, typeChambre);
    }
}
