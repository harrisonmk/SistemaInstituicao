package com.instituicao.SistemaInstituicao.controle;

import com.instituicao.SistemaInstituicao.model.Aluno;
import com.instituicao.SistemaInstituicao.model.Instituicao;
import com.instituicao.SistemaInstituicao.repositorios.RepositorioAluno;
import com.instituicao.SistemaInstituicao.repositorios.RepositorioInstituicao;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/alunos")
public class AlunoControle {

    @Autowired
    private RepositorioAluno repositorioAluno;

    @Autowired
    private RepositorioInstituicao repositorioInstituicao;

    //Metodo para retornar para a pagina index com lista de alunos
    @GetMapping("/index")
    public ModelAndView index() {

        ModelAndView resultado = new ModelAndView("aluno/index");
        resultado.addObject("alunos", repositorioAluno.findAll());

        return resultado;
    }

    //Metodo para ir para a pagina de inserir e manipular ela
    @GetMapping("/inserir")
    public ModelAndView inserir() {

        ModelAndView resultado = new ModelAndView("aluno/inserir");
        Aluno aluno = new Aluno();
        aluno.setInstituicao(new Instituicao()); //inicializa o set para evitar nullpointerException
        resultado.addObject("aluno", aluno);
        resultado.addObject("instituicoes", repositorioInstituicao.findAll());//pega a lista de todas as instituicoes

        return resultado;
    }

    //Metodo post para salvar um aluno no banco de dados
    @PostMapping("/inserir")
    public String inserir(Aluno aluno) {

        repositorioAluno.save(aluno);
        return "redirect:/alunos/index";

    }

    //Metodo get para editar um aluno
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {

        Optional<Aluno> aluno = repositorioAluno.findById(id);

        ModelAndView resultado = new ModelAndView("aluno/editar");

        resultado.addObject("aluno", aluno);
        resultado.addObject("instituicoes", repositorioInstituicao.findAll());

        return resultado;
    }

    //Metodo post para editar um aluno
    @PostMapping("/editar")
    public String editar(Aluno aluno) {

        repositorioAluno.save(aluno);

        return "redirect:/alunos/index";
    }

    //Metodo para excluir um aluno do banco de dados
    @GetMapping("/exluir/{id}")
    public String excluir(@PathVariable Long id) {

        repositorioAluno.deleteById(id);

        return "redirect:/alunos/index";
    }
    
    

}
