
package com.instituicao.SistemaInstituicao.repositorios;

import com.instituicao.SistemaInstituicao.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioAluno extends JpaRepository<Aluno,Long> {
    
}
