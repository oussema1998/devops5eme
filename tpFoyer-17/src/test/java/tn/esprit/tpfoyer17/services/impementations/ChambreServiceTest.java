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
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;

@ExtendWith(MockitoExtension.class)
public class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreService chambreService;

    @BeforeEach
    public void setUp() {
        // Aucune action nécessaire ici car MockitoExtension s'occupe des mocks
    }

    @Test
    public void testAddChambre() {
        // Création d'une instance de Chambre avec le Builder
        Chambre chambre = Chambre.builder()
                .numeroChambre(101)
                .typeChambre(TypeChambre.SIMPLE)
                .build();

        // Simulation du comportement de sauvegarde de la chambre dans le repository
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Appel de la méthode de service
        Chambre savedChambre = chambreService.addChambre(chambre);

        // Vérification que la chambre retournée est bien celle qui a été sauvegardée
        assertEquals(chambre, savedChambre);

        // Vérification que la méthode save() du repository a bien été appelée
        verify(chambreRepository).save(chambre);
    }
}
