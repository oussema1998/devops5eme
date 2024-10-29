package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // Préparer les données
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .build();

        // Appeler la méthode à tester
        Bloc savedBloc = blocService.addBloc(bloc);

        // Vérifier le résultat attendu
        assertEquals(bloc.getNomBloc(), savedBloc.getNomBloc());
        assertEquals(bloc.getCapaciteBloc(), savedBloc.getCapaciteBloc());

        // Vérification dans la base de données
        Bloc foundBloc = blocRepository.findById(savedBloc.getIdBloc()).orElse(null);
        assertEquals("Bloc A", foundBloc.getNomBloc());
        assertEquals(100, foundBloc.getCapaciteBloc());

        log.info("Test addBloc passé avec succès pour le bloc : {}", savedBloc);
    }
}