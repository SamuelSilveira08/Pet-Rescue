package com.example.petrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.petrescue.domain.UsuarioDTO;
import com.example.petrescue.domain.enums.TipoUsuario;
import com.example.petrescue.service.RetrofitConfig;
import com.example.petrescue.service.UsuarioService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText etNome;
    private TextInputEditText etEmail;
    private TextInputEditText etSenha;
    private TextInputEditText etFoto;

    private TextInputEditText etNomeOng;
    private TextInputEditText etCpfCnpj;
    private TextInputEditText etDescricaoOng;

    private RadioGroup rgTipoUsuario;
    private Button btCadastroUsuario;

    private UsuarioDTO usuarioDTO;
    private Retrofit retrofit;
    private UsuarioService usuarioService;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.iniciarComponentes();

        this.rgTipoUsuario.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rb_individuo_cadastrousuario:
                    this.usuarioDTO.setTipoUsuario(TipoUsuario.INDIVIDUO);
                    break;
                case R.id.rb_instituiucao_cadastrousuario:
                    this.usuarioDTO.setTipoUsuario(TipoUsuario.INSTITUCIONAL);
                    break;
            }
        });

        this.btCadastroUsuario.setOnClickListener(v -> {
            this.usuarioDTO.setNome(this.etNome.getText().toString());
            this.usuarioDTO.setEmail(this.etEmail.getText().toString());
            this.usuarioDTO.setSenha(this.etSenha.getText().toString());
            this.usuarioDTO.setFoto(this.etFoto.getText().toString());
            if(this.usuarioDTO.getTipoUsuario().equals(TipoUsuario.INDIVIDUO)){
                this.usuarioDTO.setNomeOng(null);
                this.usuarioDTO.setCpfCnpj(null);
                this.usuarioDTO.setDescricao(null);
            }else{
                this.usuarioDTO.setNomeOng(this.etNomeOng.getText().toString());
                this.usuarioDTO.setCpfCnpj(this.etCpfCnpj.getText().toString());
                this.usuarioDTO.setDescricao(this.etDescricaoOng.getText().toString());
            }
            this.cadastrarUsuario(this.usuarioDTO);
        });
    }

    private void iniciarComponentes(){
        this.etNome = this.findViewById(R.id.et_nome_cadastrousuario);
        this.etEmail = this.findViewById(R.id.et_nome_cadastrousuario);
        this.etSenha = this.findViewById(R.id.et_senha_cadastrousuario);
        this.etFoto = this.findViewById(R.id.et_foto_cadastrousuario);
        this.etNomeOng = this.findViewById(R.id.et_nome_ong_cadastrousuario);
        this.etCpfCnpj = this.findViewById(R.id.et_nome_cadastrousuario);
        this.etDescricaoOng = this.findViewById(R.id.et_descricao_cadastrousuario);
        this.rgTipoUsuario = this.findViewById(R.id.rg_tipo_cadastrousuario);
        this.btCadastroUsuario = this.findViewById(R.id.bt_cadastrar_cadastrousuario);
        this.usuarioDTO = new UsuarioDTO();
        this.usuarioDTO.setTipoUsuario(TipoUsuario.INDIVIDUO);
    }

    private void cadastrarUsuario(UsuarioDTO usuarioDTO){
        this.retrofit = RetrofitConfig.generateRetrofit();
        this.usuarioService = retrofit.create(UsuarioService.class);
        this.usuarioService.cadastrar(usuarioDTO).enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                if (response.isSuccessful()){
                    intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    intent.putExtra("usuario", response.body());
                    startActivity(intent);
                    finish();
                }else{
                    Log.i("DEBUG", "THROW ERROR: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                Log.i("DEBUG", "THROW ERROR: " + t.getMessage());
            }
        });
    }
}