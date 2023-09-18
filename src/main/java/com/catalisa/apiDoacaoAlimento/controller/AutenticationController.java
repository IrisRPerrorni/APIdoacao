package com.catalisa.apiDoacaoAlimento.controller;

import com.catalisa.apiDoacaoAlimento.config.security.TokenService;
import com.catalisa.apiDoacaoAlimento.model.Usuarios;
import com.catalisa.apiDoacaoAlimento.model.dto.AutenticationDTO;
import com.catalisa.apiDoacaoAlimento.model.dto.LoginRespostaDTO;
import com.catalisa.apiDoacaoAlimento.model.dto.RegistroDto;
import com.catalisa.apiDoacaoAlimento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AutenticationDTO data){
        var usernamePassord = new UsernamePasswordAuthenticationToken(data.getLogin(),data.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassord);
        var token = tokenService.generateToken((Usuarios) auth.getPrincipal());


        return ResponseEntity.ok(new LoginRespostaDTO(token));
    }
    @PostMapping ("/registro")
    public ResponseEntity register(@RequestBody @Valid RegistroDto data){
        if (this.usuarioRepository.findByLogin(data.getLogin())!= null)
            return  ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
        Usuarios novoUsuario = new Usuarios(data.getLogin(),encryptedPassword,data.getRole());

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }



}
