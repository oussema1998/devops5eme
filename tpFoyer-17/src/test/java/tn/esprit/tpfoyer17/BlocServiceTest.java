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
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.impementations.BlocService;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class BlocServiceTest {

    @Autowired
    private BlocRepository blocRepository;

    @Autowired
    private BlocService blocService;

    @Test
    @Order(1)
    public void testAddBloc() {
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();

        Bloc savedBloc = blocService.addBloc(bloc);

        assertNotNull(savedBloc);
        assertEquals("Bloc A", savedBloc.getNomBloc());
        assertEquals(100, savedBloc.getCapaciteBloc());

        Bloc foundBloc = blocRepository.findById(savedBloc.getIdBloc()).orElse(null);
        assertNotNull(foundBloc);
        assertEquals("Bloc A", foundBloc.getNomBloc());
        assertEquals(100, foundBloc.getCapaciteBloc());

        log.info("Test addBloc réussi pour le bloc : {}", savedBloc);
    }

    @Test
    @Order(2)
    public void testRetrieveBlocs() {
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc B")
                .capaciteBloc(150)
                .build();
        blocService.addBloc(bloc);

        List<Bloc> blocs = blocService.retrieveBlocs();

        assertNotNull(blocs);
        assertTrue(blocs.size() > 0, "Expected at least one bloc in the database");

        log.info("Test retrieveBlocs réussi, nombre de blocs récupérés: {}", blocs.size());
    }

    @Test
    @Order(3)
    public void testRetrieveBloc() {
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc C")
                .capaciteBloc(120)
                .build();
        bloc = blocService.addBloc(bloc);

        Bloc foundBloc = blocService.retrieveBloc(bloc.getIdBloc());

        assertNotNull(foundBloc);
        assertEquals("Bloc C", foundBloc.getNomBloc());
        assertEquals(120, foundBloc.getCapaciteBloc());

        log.info("Test retrieveBloc réussi pour le bloc: {}", foundBloc);
    }

    @Test
    @Order(4)
    public void testUpdateBloc() {
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc D")
                .capaciteBloc(130)
                .build();
        bloc = blocService.addBloc(bloc);

        bloc.setNomBloc("Bloc D Updated");
        bloc.setCapaciteBloc(140);

        Bloc updatedBloc = blocService.updateBloc(bloc);

        assertNotNull(updatedBloc);
        assertEquals("Bloc D Updated", updatedBloc.getNomBloc());
        assertEquals(140, updatedBloc.getCapaciteBloc());

        log.info("Test updateBloc réussi pour le bloc mis à jour : {}", updatedBloc);
    }

    @Test
    @Order(5)
    public void testRemoveBloc() {
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc E")
                .capaciteBloc(90)
                .build();
        bloc = blocService.addBloc(bloc);
        long idBloc = bloc.getIdBloc();

        blocService.removeBloc(idBloc);

        // Vérification que le bloc a bien été supprimé
        boolean exists = blocRepository.existsById(idBloc);
        assertFalse(exists, "Bloc should no longer exist in the database");

        log.info("Test removeBloc réussi pour le bloc ID: {}", idBloc);
    }
}
