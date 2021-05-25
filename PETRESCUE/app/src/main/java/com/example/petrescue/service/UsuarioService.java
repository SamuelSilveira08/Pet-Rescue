package com.example.petrescue.service;

import com.example.petrescue.domain.CarteiraDTO;
import com.example.petrescue.domain.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("usuario/cadastrar")
    Call<Usuario> cadastrar(@Body Usuario usuario);

    @POST("usuario/editar")
    Call<Usuario> editar(@Body Usuario usuario);

    @POST("usuario/login")
    Call<Usuario> logar(@Body Usuario usuario);

    @GET("usuario/{idusuario}")
    Call<Usuario> buscarUsuarioId(@Path("idusuario") Integer idusuario);

    @POST("usuario/depositar")
    Call<Usuario> depositarSaldo(@Body CarteiraDTO carteiraDTO);
}
