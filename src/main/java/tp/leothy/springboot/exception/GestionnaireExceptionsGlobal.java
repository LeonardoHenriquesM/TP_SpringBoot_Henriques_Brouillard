package tp.leothy.springboot.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GestionnaireExceptionsGlobal {

    @ExceptionHandler(RessourceNonTrouveeException.class)
    public ResponseEntity<ApiErreur> gererRessourceNonTrouvee(RessourceNonTrouveeException ex,
                                                              HttpServletRequest requete) {
        ApiErreur erreur = ApiErreur.builder()
                .horodatage(LocalDateTime.now())
                .statut(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .chemin(requete.getRequestURI())
                .details(List.of("La ressource demandée n'existe pas."))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erreur);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErreur> gererValidation(MethodArgumentNotValidException ex,
                                                     HttpServletRequest requete) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(this::formaterErreurChamp)
                .collect(Collectors.toList());

        ApiErreur erreur = ApiErreur.builder()
                .horodatage(LocalDateTime.now())
                .statut(HttpStatus.BAD_REQUEST.value())
                .message("Erreur de validation des données.")
                .chemin(requete.getRequestURI())
                .details(details)
                .build();

        return ResponseEntity.badRequest().body(erreur);
    }

    private String formaterErreurChamp(FieldError erreur) {
        return erreur.getField() + " : " + erreur.getDefaultMessage();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErreur> gererExceptionGenerique(Exception ex,
                                                             HttpServletRequest requete) {
        ApiErreur erreur = ApiErreur.builder()
                .horodatage(LocalDateTime.now())
                .statut(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erreur interne du serveur.")
                .chemin(requete.getRequestURI())
                .details(List.of(ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erreur);
    }
}
