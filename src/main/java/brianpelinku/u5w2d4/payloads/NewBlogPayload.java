package brianpelinku.u5w2d4.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class NewBlogPayload {
    @NotEmpty(message = "Campo obbligatorio")
    private String categoria;
    @NotEmpty(message = "Campo obbligatorio")
    private String titolo;
    @NotEmpty(message = "Campo obbligatorio")
    private String contenuto;
    @NotEmpty(message = "Campo obbligatorio")
    private int tempoDiLettura;
    private int authorId;
}
