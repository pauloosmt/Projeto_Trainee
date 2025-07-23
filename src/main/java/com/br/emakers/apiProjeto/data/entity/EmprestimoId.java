package com.br.emakers.apiProjeto.data.entity;


import java.io.Serializable;
import java.util.Objects;



public class EmprestimoId  implements Serializable{
    private Long livro;
    private Long pessoa;

    public EmprestimoId() {}

    @Override
    public boolean equals(Object x) { //Metodo equals para conferir quando dois objetos sao iguais(mesmo idLivro e idPessoa)
        if(this == x) return true;
        if(!(x instanceof EmprestimoId)) return false;
        EmprestimoId that = (EmprestimoId) x;
        return Objects.equals(livro, that.livro) && Objects.equals(pessoa, that.pessoa);
    }

    @Override
    public int hashCode() { //Garantir que dois obejtos iguais tenham o mesmo codigo hash
        return Objects.hash(livro, pessoa);
    }

}
