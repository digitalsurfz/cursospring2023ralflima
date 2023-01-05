package br.com.projeto.api.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;

@RestController
public class Controle {

  // O objeto Repositorio obtem as ações do banco de dados:
  // Cadastrar, Selecionar, Alterar e Excluir.
  @Autowired // Garantirá instanciação do objeto ao executar o Spring
  private Repositorio acao;

  @Autowired
    private Servico servico;

  @PostMapping("/api")
  public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
    return servico.cadastrar(obj);
  }

  @GetMapping("/api")
  public ResponseEntity<?> selecionar() {
    return servico.selecionar();
  }

  @GetMapping("/api/{codigo}")
  public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
    return servico.selecionarPeloCodigo(codigo);
  }

  @PutMapping("/api")
  public Pessoa editar(@RequestBody Pessoa obj) {
    return acao.save(obj);
  }

  @DeleteMapping("/api/{codigo}")
  public void remover(@PathVariable int codigo) {
   // Pessoa obj = selecionarPeloCodigo(codigo);
   // acao.delete(obj);
  }

  @GetMapping("/api/contador")
  public long contador() {
    return acao.count();
  }

  @GetMapping("/api/ordenarNomes")
  public List<Pessoa> ordenarNomes() {
    return acao.findByOrderByNome();
  }

  @GetMapping("/api/ordenarNomes2")
  public List<Pessoa> ordenarNomes2() {
    return acao.findByNomeOrderByIdadeDesc("Jr.");
  }

  @GetMapping("/api/nomeContem")
  public List<Pessoa> nomeContem() {
    return acao.findByNomeContaining("m");
  }

  @GetMapping("/api/iniciaCom")
  public List<Pessoa> iniciaCom() {
    return acao.findByNomeStartsWith("m");
  }

  @GetMapping("/api/terminaCom")
  public List<Pessoa> terminaCom() {
    return acao.findByNomeEndsWith("a");
  }

  @GetMapping("/api/somaIdades")
  public int somaIdades() {
    return acao.somaIdades();
  }

  @GetMapping("/api/idadeMaiorIgual")
  public List<Pessoa> idadeMaiorIgual() {
    return acao.idadeMaiorIgual(34);
  }

  @GetMapping("/boasvindas")
  public String boasVindas() {
    return "Seja bem vindo(a).";
  }

  @GetMapping("/boasvindas/{nome}")
  public String boasVindas(@PathVariable String nome) {
    return "Seja bem vindo(a) " + nome;
  }

  @PostMapping("/pessoa")
  public Pessoa pessoa(@RequestBody Pessoa p) {
    return p;
  }

  @GetMapping("/status")
  public ResponseEntity<?> status() {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
