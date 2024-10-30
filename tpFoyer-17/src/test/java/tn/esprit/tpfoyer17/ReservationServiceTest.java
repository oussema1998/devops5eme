package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.repositories.ReservationRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.ReservationService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    EtudiantRepository etudiantRepository;

    @Mock
    ChambreRepository chambreRepository;

    @Mock
    UniversiteRepository universiteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllReservation() {
        // Préparation des données
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Appel à la méthode
        List<Reservation> result = reservationService.retrieveAllReservation();

        // Vérification
        assertEquals(2, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateReservation() {
        // Préparation des données
        Reservation reservation = new Reservation();
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Appel à la méthode
        Reservation result = reservationService.updateReservation(reservation);

        // Vérification
        assertNotNull(result);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    public void testRetrieveReservation() {
        // Préparation des données
        String idReservation = "101-BLOC1-2024";
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(idReservation)).thenReturn(Optional.of(reservation));

        // Appel à la méthode
        Reservation result = reservationService.retrieveReservation(idReservation);

        // Vérification
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(idReservation);
    }

    @Test
    public void testAnnulerReservation() {
        // Préparation des données
        long cinEtudiant = 12345L;
        Etudiant etudiant = new Etudiant();
        etudiant.setReservations(new HashSet<>());

        Reservation reservation = Reservation.builder()
                .idReservation("101-BLOC1-2024")
                .etudiants(new HashSet<>(Collections.singletonList(etudiant)))
                .estValide(true)
                .build();

        etudiant.getReservations().add(reservation);

        Chambre chambre = Chambre.builder()
                .idChambre(1L)
                .numeroChambre(101)
                .typeChambre(TypeChambre.SIMPLE)
                .reservations(new HashSet<>(Collections.singletonList(reservation)))
                .build();

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(etudiant);
        when(chambreRepository.findByReservationsIdReservation("101-BLOC1-2024")).thenReturn(chambre);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Appel à la méthode
        Reservation result = reservationService.annulerReservation(cinEtudiant);

        // Vérification
        assertNull(result);
        assertTrue(chambre.getReservations().isEmpty());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    public void testGetReservationParAnneeUniversitaireEtNomUniversite() {
        // Préparation des données
        LocalDate anneeUniversite = LocalDate.of(2023, 1, 1);
        String nomUniversite = "UniversiteTest";
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepository.recupererParBlocEtTypeChambre(nomUniversite, anneeUniversite)).thenReturn(reservations);

        // Appel à la méthode
        List<Reservation> result = reservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversite, nomUniversite);

        // Vérification
        assertEquals(2, result.size());
        verify(reservationRepository, times(1)).recupererParBlocEtTypeChambre(nomUniversite, anneeUniversite);
    }

    @Test
    public void testGetReservationParAnneeUniversitaireEtNomUniversiteKeyWord() {
        // Préparation des données
        LocalDate anneeUniversite = LocalDate.of(2023, 1, 1);
        String nomUniversite = "UniversiteTest";
        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(universiteRepository.findByFoyerBlocsChambresReservationsAnneeUniversitaireAndNomUniversite(anneeUniversite, nomUniversite)).thenReturn(reservations);

        // Appel à la méthode
        List<Reservation> result = reservationService.getReservationParAnneeUniversitaireEtNomUniversiteKeyWord(anneeUniversite, nomUniversite);

        // Vérification
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findByFoyerBlocsChambresReservationsAnneeUniversitaireAndNomUniversite(anneeUniversite, nomUniversite);
    }


}
