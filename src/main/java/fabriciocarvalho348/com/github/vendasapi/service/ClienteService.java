package fabriciocarvalho348.com.github.vendasapi.service;

import fabriciocarvalho348.com.github.vendasapi.model.entity.Cliente;
import fabriciocarvalho348.com.github.vendasapi.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {
    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente pesquisarPorId(Long id) {
        return clienteRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }

    public List<Cliente> pesquisarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente incluir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente, Long id) {
//        Cliente encontrado = pesquisarPorId(id);
//        cliente.setId(encontrado.getId());
//        return clienteRepository.save(cliente);

        // API STREAM JAVA
        return clienteRepository.findById(id)
                .map(m -> {
                    cliente.setId(m.getId());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }

    public void excluir(Long id) {
//        Cliente encontrado = pesquisarPorId(id);
//        clienteRepository.delete(encontrado);
        clienteRepository.findById(id)
                .map(m -> {
                    clienteRepository.delete(m);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }
}
