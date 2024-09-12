package brianpelinku.u5w2d4.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("L'elemento con id " + id + " non è stato trovato.");
    }
}
