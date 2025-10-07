package AndresFlores_DiegoGabriel_1_B.PruebaFinal.Exception;

public class ExceptionDatosDuplicados extends RuntimeException {
    public String getcampoduplicado;

    public ExceptionDatosDuplicados(String message) {
        super(message);
    }
}
