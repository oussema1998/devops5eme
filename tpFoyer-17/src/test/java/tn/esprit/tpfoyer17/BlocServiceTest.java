package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void testAddBloc() {
        // 1. Préparation des données
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();

        // 2. Appel à la méthode à tester
        Bloc savedBloc = blocService.addBloc(bloc);

        // 3. Vérification des résultats
        assertNotNull(savedBloc);
        assertEquals(bloc.getNomBloc(), savedBloc.getNomBloc());
        assertEquals(bloc.getCapaciteBloc(), savedBloc.getCapaciteBloc());

        Bloc foundBloc = blocRepository.findById(savedBloc.getIdBloc()).orElse(null);
        assertNotNull(foundBloc);
        assertEquals("Bloc A", foundBloc.getNomBloc());
        assertEquals(100, foundBloc.getCapaciteBloc());

        log.info("Test addBloc passé avec succès pour le bloc : {}", savedBloc);
    }

    @Test
    @Order(2)
    @Transactional
    public void testRetrieveBlocs() {
        // 1. Préparation des données
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc B")
                .capaciteBloc(150)
                .build();
        blocService.addBloc(bloc);

        // 2. Appel à la méthode à tester
        List<Bloc> blocs = blocService.retrieveBlocs();

        // 3. Vérification des résultats
        assertNotNull(blocs);
        assertTrue(blocs.size() > 0, "Expected at least one bloc in the database");

        log.info("Test retrieveBlocs passé avec succès, nombre de blocs récupérés: {}", blocs.size());
    }

    @Test
    @Order(3)
    @Transactional
    public void testRetrieveBloc() {
        // 1. Préparation des données
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc C")
                .capaciteBloc(120)
                .build();
        bloc = blocService.addBloc(bloc);

        // 2. Appel à la méthode à tester
        Bloc foundBloc = blocService.retrieveBloc(bloc.getIdBloc());

        // 3. Vérification des résultats
        assertNotNull(foundBloc);
        assertEquals("Bloc C", foundBloc.getNomBloc());
        assertEquals(120, foundBloc.getCapaciteBloc());

        log.info("Test retrieveBloc passé avec succès pour le bloc: {}", foundBloc);
    }

    @Test
    @Order(4)
    @Transactional
    public void testUpdateBloc() {
        // 1. Préparation des données
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc D")
                .capaciteBloc(130)
                .build();
        bloc = blocService.addBloc(bloc);

        // Modifier le bloc
        bloc.setNomBloc("Bloc D Updated");
        bloc.setCapaciteBloc(140);

        // 2. Appel à la méthode à tester
        Bloc updatedBloc = blocService.updateBloc(bloc);

        // 3. Vérification des résultats
        assertNotNull(updatedBloc);
        assertEquals("Bloc D Updated", updatedBloc.getNomBloc());
        assertEquals(140, updatedBloc.getCapaciteBloc());

        log.info("Test updateBloc passé avec succès pour le bloc mis à jour : {}", updatedBloc);
    }

    @Test
    @Order(5)
    @Transactional
    public void testRemoveBloc() {
        // 1. Préparation des données
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc E")
                .capaciteBloc(90)
                .build();
        bloc = blocService.addBloc(bloc);
        long idBloc = bloc.getIdBloc();

        // 2. Appel à la méthode à tester
        blocService.removeBloc(idBloc);

        // 3. Vérification des résultats
        Bloc deletedBloc = blocService.retrieveBloc(idBloc);
        assertNull(deletedBloc);

        log.info("Test removeBloc passé avec succès pour le bloc ID: {}", idBloc);
    }
}
