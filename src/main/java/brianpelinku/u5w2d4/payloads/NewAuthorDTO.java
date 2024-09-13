package brianpelinku.u5w2d4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome deve essere copreso tra 3 3 30 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome deve essere copreso tra 3 3 30 caratteri")
        String cognome,
        @NotEmpty(message = "L'email è obbligatorio")
        @Email
        String email,
       // @NotEmpty(message = "La data di nascita è obbligatorio")
        LocalDate dataDiNascita) {
}
