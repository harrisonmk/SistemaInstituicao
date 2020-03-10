package com.instituicao.SistemaInstituicao.controle;

import com.instituicao.SistemaInstituicao.model.Instituicao;
import com.instituicao.SistemaInstituicao.repositorios.RepositorioInstituicao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/instituicoes") //para acessar qualquer metodo desse controle vai ter que usar o /instituicoes antes
public class InstituicaoControle {
    
    @Autowired
    private RepositorioInstituicao repositorioInstituicao;

    //Metodo para retornar a pagina de listagem de instituicoes
    @GetMapping("/index")
    public ModelAndView index() {
        
        ModelAndView resultado = new ModelAndView("instituicao/index");
        List<Instituicao> instituicoes = repositorioInstituicao.findAll();
        resultado.addObject("instituicoes", instituicoes);
        
        return resultado;
        
    }

    //Metodo para ir para a pagina de insercao de instituicoes
    // instituicao = pasta
    //inserir = arquivo html
    @GetMapping("/inserir")
    public ModelAndView inserir() {
        
        ModelAndView resultado = new ModelAndView("instituicao/inserir");
        resultado.addObject("instituicao", new Instituicao());
        
        return resultado;
        
    }

    //salva as informacoes no banco de dados e redireciona
    @PostMapping("/inserir")
    public String inserir(Instituicao instituicao) {
        
        repositorioInstituicao.save(instituicao);
        
        return "redirect:/instituicoes/index";        
    }

    //Usar se o ModelAndView para renderizar o metodo dentro do thymeleaf
    //metodo para pegar o id daquela instituicao e retornar a pagina html de editar
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        
        Instituicao instituicao = repositorioInstituicao.getOne(id);
        ModelAndView resultado = new ModelAndView("instituicao/editar");
        resultado.addObject("instituicao", instituicao);
        
        return resultado;
    }

    //Metodo para salvar as alteracoes de uma instituicao especifica
    @PostMapping("/editar")
    public String editar(Instituicao instituicao) {
        
        repositorioInstituicao.save(instituicao);
        
        return "redirect:/instituicoes/index";        
    }
    
    
    //Metodo para excluir uma instituicao no banco de dados
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        
        repositorioInstituicao.deleteById(id);
        
        return "redirect:/instituicoes/index";
        
    }
    
    //Pode ser passado um parametro com o nome da instituicao ou pode nao ser passado nenhuma instituicao
    @GetMapping({"/pesquisarPorNome/{nome}","/pesquisarPorNome"})
    public @ResponseBody List<Instituicao> pesquisarPorNome(@PathVariable Optional<String> nome){
       
    //se o nome tiver um valor retorna true se nao retorna false    
    if(nome.isPresent()){
        
     //retorna apenas a instituicao buscada   
     return repositorioInstituicao.findByNomeContaining(nome.get());
        
        
    }else{
        //Retorna todas as instituicoes cadastradas
        return repositorioInstituicao.findAll();
    }    
        
        
    }
}
