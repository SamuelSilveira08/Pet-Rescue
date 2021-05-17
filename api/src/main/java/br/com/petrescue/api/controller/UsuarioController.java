package br.com.petrescue.api.controller;


import br.com.petrescue.api.controller.dto.CarteiraDTO;
import br.com.petrescue.api.controller.dto.UsuarioDTO;
import br.com.petrescue.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return this.usuarioService.cadastrarUsuario(usuarioDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO loginUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return this.usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping("/{idusuario}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO buscarUsuarioId(@PathVariable("idusuario") Integer idusuario){
        return this.usuarioService.buscarUsuarioId(idusuario);
    }

    @PostMapping("/depositar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO depositarSaldo(@RequestBody CarteiraDTO carteiraDTO){
        return this.usuarioService.depositarSaldo(carteiraDTO);
    }
}