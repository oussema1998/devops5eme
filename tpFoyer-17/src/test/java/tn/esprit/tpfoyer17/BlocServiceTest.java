package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.impementations.BlocService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Permet de spécifier l'ordre d'exécution des tests
@Slf4j // Ajoute le support pour le logging avec Lombok
public class BlocServiceTest {

    @Autowired
    private BlocRepository blocRepository; // Injection du repository pour interagir avec la base de données

    @Autowired
    private BlocService blocService; // Injection du service Bloc

    @Test
    @Order(1) // Définit l'ordre d'exécution pour ce test
    @Transactional // Rendre la méthode transactionnelle pour garantir l'intégrité des données
    public void testAddBloc() {
        // Préparer les données
        Bloc bloc = Bloc.builder()
                .nomBloc("Bloc A") // Nom du bloc à ajouter
                .capaciteBloc(100) // Capacité du bloc
                .build();

        // Appeler la méthode à tester
        Bloc savedBloc = blocService.addBloc(bloc);

        // Vérifier le résultat attendu
        assertEquals(bloc.getNomBloc(), savedBloc.getNomBloc()); // Vérifie que le nom du bloc enregistré est correct
        assertEquals(bloc.getCapaciteBloc(), savedBloc.getCapaciteBloc()); // Vérifie que la capacité du bloc enregistré est correcte

        // Vérification dans la base de données
        Bloc foundBloc = blocRepository.findById(savedBloc.getIdBloc()).orElse(null); // Recherche le bloc dans la DB
        assertEquals("Bloc A", foundBloc.getNomBloc()); // Vérifie que le nom du bloc trouvé est correct
        assertEquals(100, foundBloc.getCapaciteBloc()); // Vérifie que la capacité du bloc trouvé est correcte

        log.info("Test addBloc passé avec succès pour le bloc : {}", savedBloc); // Log le succès du test
    }
}
