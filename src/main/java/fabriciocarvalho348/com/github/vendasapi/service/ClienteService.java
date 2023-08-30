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

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente pesquisarPorId(Long id) {
        Cliente encontrado = clienteRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        return encontrado;
    }

    public List<Cliente> pesquisarTodos() {
        List<Cliente> lista = clienteRepository.findAll();
        return lista;
    }

    public Cliente incluir(Cliente cliente) {
        Cliente salvo = clienteRepository.save(cliente);
        return salvo;
    }

    public Cliente atualizar(Cliente cliente, Long id) {
        Cliente encontrado = pesquisarPorId(id);
        cliente.setId(encontrado.getId());
        Cliente salvo = clienteRepository.save(cliente);
        return salvo;
    }

    public void excluir(Long id) {
        Cliente encontrado = pesquisarPorId(id);

        clienteRepository.delete(encontrado);
    }
}
