
package com.instituicao.SistemaInstituicao.repositorios;

import com.instituicao.SistemaInstituicao.model.Instituicao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioInstituicao extends JpaRepository<Instituicao,Long> {
    
   //pesquisa o nome das instituicoes que conterem o nome que o usuario digitar 
  List<Instituicao>findByNomeContaining(String nome);  
    
    
}
