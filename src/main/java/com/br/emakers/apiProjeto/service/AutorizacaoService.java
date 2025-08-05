package com.br.emakers.apiProjeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.emakers.apiProjeto.repository.PessoaRepository;

@Service
public class AutorizacaoService implements UserDetailsService {

    @Autowired
    PessoaRepository pessoaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pessoaRepository.findByEmail(username);
    }

}
