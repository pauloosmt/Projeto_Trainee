package com.br.emakers.apiProjeto.data.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.emakers.apiProjeto.data.dto.request.PessoaRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pessoa")

public class Pessoa implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idPessoa;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column (name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 100 )
    private String senha;

    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    
    @Builder 
    public Pessoa(PessoaRequestDTO pessoaRequestDTO, String senha) {
        this.nome = pessoaRequestDTO.nome();
        this.cpf = pessoaRequestDTO.cpf();
        this.cep = pessoaRequestDTO.cep();
        this.email = pessoaRequestDTO.email();
        this.senha = senha;
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
       return senha;
    }
       
    @Override
    public String getUsername() {
        return email;
    }

    @Override
public boolean isEnabled() {
    return true; 
}

@Override
public boolean isCredentialsNonExpired() {
    return true; 
}

@Override
public boolean isAccountNonExpired() {
    return true; 
}

@Override
public boolean isAccountNonLocked() {
    return true; 
}


}
