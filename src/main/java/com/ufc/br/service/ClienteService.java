package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ufc.br.model.Cliente;
import com.ufc.br.model.Role;
import com.ufc.br.repository.ClienteRepository;
import com.ufc.br.repository.RoleRepository;

@Service
public class ClienteService {
	@Autowired
    ClienteRepository clienteRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
    public void save(Cliente cliente) {

        cliente.setSenha(new BCryptPasswordEncoder().encode( cliente.getSenha()));
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleRepo.findByPapel("ROLE_USER"));
        cliente.setRoles(roles);
        clienteRepo.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    public void excluirCliente(Long id) {
        clienteRepo.deleteById(id);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepo.getOne(id);
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepo.findByEmail(email);
    }

}
