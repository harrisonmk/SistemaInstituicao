
package com.instituicao.SistemaInstituicao.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //controle baseado em MVC
public class SistemaInstituicaoControle {
    
    @RequestMapping("/")
    public String hello(Model model){
     //a string "mensagem" que eh acessada no html com thymeleaf com expression language   
     model.addAttribute("mensagem","olá spring boot com thymeleaf");
        
     return "index";
    }
    
    
    
    
}
