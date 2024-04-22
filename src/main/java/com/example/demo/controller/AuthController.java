package com.example.demo.controller;


import com.example.demo.dto.user.DtoUser;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.dto.user.DtoUserView;
import com.example.demo.entities.User;
import com.example.demo.infra.security.DataJWT;
import com.example.demo.infra.security.TokenService;
import com.example.demo.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    public AuthenticationManager authenticationManager;
    private TokenService tokenService;

    private IUserService userService;
    private TokenService decodeToken;


    @Operation(summary = "Autenticarse", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoUserLogin.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PostMapping("/login")
    public ResponseEntity userAuth(@RequestBody @Valid DtoUserLogin dtoUserLogin){

        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoUserLogin.getUsername(),
                dtoUserLogin.getPassword());
        authenticationManager.authenticate(authToken);

        var userAuth = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) userAuth.getPrincipal());



        String usuarioId = decodeToken.decodeToken(JWTtoken)[1];
        System.out.println(" usuario "+usuarioId.toString());
        User user = userService.findById(Long.parseLong(usuarioId));

        DtoUser mUsuarioActualizar = new DtoUser(user.getUser_id(),user.getUsername(),user.getPassword(),JWTtoken);
        this.userService.updateUserToken(mUsuarioActualizar);
        return ResponseEntity.ok(new DataJWT(JWTtoken));

    }
    @Operation(summary = "Registrarse", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoUserLogin.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @PostMapping("/register")
    public ResponseEntity userRegister(@RequestBody @Valid DtoUserLogin dtoUserLogin, UriComponentsBuilder uriComponentsBuilder){
        User user = userService.save(dtoUserLogin);
        DtoUserView dtoUserView = DtoUserView.builder()
                                    .user_id(user.getUser_id())
                                    .username(user.getUsername())
                                    .build();
        URI url = uriComponentsBuilder.path("/api/get_user").buildAndExpand(user.getUser_id()).toUri();

        return ResponseEntity.created(url).body(dtoUserView);
    }
    @Operation(summary = "Obtener usuario", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping("/get_user")
    public ResponseEntity<DtoUserView> getUser(@RequestHeader(value = "Authorization",required = false) String encoding)
    {
        User user = userService.findUser(encoding);
        DtoUserView dtoUserView = DtoUserView.builder()
                                    .user_id(user.getUser_id())
                                    .username(user.getUsername())
                                    .build();
        return ResponseEntity.ok(dtoUserView);
    }
}
