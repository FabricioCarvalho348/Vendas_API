package fabriciocarvalho348.com.github.vendasapi.service;

import fabriciocarvalho348.com.github.vendasapi.model.entity.Cliente;
import fabriciocarvalho348.com.github.vendasapi.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente incluir(Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }
}
