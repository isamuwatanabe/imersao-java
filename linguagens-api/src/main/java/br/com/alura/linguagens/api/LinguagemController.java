package br.com.alura.linguagens.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens(){

        return repositorio.findAll(Sort.by("ranking"));
    }

    @PostMapping("/linguagens")
    public ResponseEntity cadastrarLinguagem(@RequestBody Linguagem linguagem){
        if(repositorio.exists(Example.of(linguagem))){
            return new ResponseEntity<>("Language Already Exists",HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(repositorio.save(linguagem), HttpStatus.CREATED);
    }

    @DeleteMapping("/linguagens/{id}")
    public ResponseEntity deletarLinguagem(@PathVariable String id){
        if((repositorio.findById(id).isEmpty())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        repositorio.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/linguagens/{id}")
    public void atualizarLinguagem(@RequestBody Linguagem linguagem, @PathVariable String id){
        linguagem.setId(repositorio.findById(id).get().getId());

        if(linguagem.getTitle() == null){
            linguagem.setTitle((repositorio.findById(id).get().getTitle()));
        }

        if(linguagem.getImage() == null){
            linguagem.setImage(repositorio.findById(id).get().getImage());
        }

        if(linguagem.getRanking() == 0){
            linguagem.setRanking(repositorio.findById(id).get().getRanking());
        }

        repositorio.save(linguagem);
    }
    
}
