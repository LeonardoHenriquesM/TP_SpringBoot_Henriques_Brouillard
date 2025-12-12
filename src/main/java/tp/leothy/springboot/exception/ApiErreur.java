package tp.leothy.springboot.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErreur {

    private LocalDateTime horodatage;
    private int statut;
    private String message;
    private String chemin;
    private List<String> details;
}
