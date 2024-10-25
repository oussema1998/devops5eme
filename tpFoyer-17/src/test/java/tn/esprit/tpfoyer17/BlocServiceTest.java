package tn.esprit.tpfoyer17;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.impementations.BlocService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class BlocServiceTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocService blocService;

    @Test
    @Order(1)
    public void testCreateBloc() {
        Bloc newBloc = new Bloc(); // Créez une nouvelle instance de Bloc
        newBloc.setNomBloc("Bloc Test"); // Vérifiez que cette méthode existe également

        when(blocRepository.save(any(Bloc.class))).thenReturn(newBloc);

        Bloc createdBloc = blocService.addBloc(newBloc); // Appelez la méthode de votre service
        assertEquals("Bloc Test", createdBloc.getNomBloc()); // Vérifiez le nom du bloc créé
        verify(blocRepository, times(1)).save(newBloc); // Vérifiez que save() a été appelé une fois
    }




}
