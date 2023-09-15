package fabriciocarvalho348.com.github.vendasapi.exception;

import java.util.Arrays;
import java.util.List;

public class ApiErros {
    private List<String> erros;

    public ApiErros(List<String> erros) {
        this.erros = erros;
    }

    public ApiErros(String erro) {
        this.erros = Arrays.asList(erro);
    }

    public List<String> getErros() {
        return erros;
    }

}
