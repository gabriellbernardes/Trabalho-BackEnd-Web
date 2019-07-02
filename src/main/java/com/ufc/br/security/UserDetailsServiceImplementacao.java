package com.ufc.br.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Cliente;
import com.ufc.br.repository.ClienteRepository;
import com.ufc.br.service.ClienteService;


@Repository
@Transactional
public class UserDetailsServiceImplementacao implements UserDetailsService {

    @Autowired
    private ClienteService clienteService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteService.buscarPorEmail(email);

        if(cliente == null) {
            throw new UsernameNotFoundException("Cliente não encontrado");
        }

        return new User(cliente.getUsername(), cliente.getPassword(),true,true,
                true,true, cliente.getAuthorities());
    }
}
