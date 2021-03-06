package com.example.petrescue.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petrescue.ControleActivity;
import com.example.petrescue.R;
import com.example.petrescue.domain.Usuario;
import com.example.petrescue.domain.enums.TipoUsuario;
import com.example.petrescue.domain.subClasses.ErrorResponse;
import com.example.petrescue.service.RetrofitConfig;
import com.example.petrescue.service.UsuarioService;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

public class EditarUsuarioFragment extends Fragment {

    private Retrofit retrofit;
    private UsuarioService usuarioService;

    private Button salvar;
    private TextInputEditText nome;
    private TextInputEditText email;
    private TextInputEditText cpfCnpj;
    private TextInputEditText descricao;
    private LinearLayout instituccional;
    private Button btnFoto;
    private TextView imgMessage;

    private Usuario usuario;

    private static final int IMG_REQUEST_CODE = 21;
    private Bitmap bitmap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_editar_usuario, container, false);

        this.inicializaComponentes(v);

        this.btnFoto.setOnClickListener(v1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMG_REQUEST_CODE);
        });

        this.salvar.setOnClickListener(v1 -> {
            this.usuario.setNome(this.nome.getText().toString());
            this.usuario.setEmail(this.email.getText().toString());
            if (bitmap != null) this.usuario.setFoto(imageToBase64());
            if (TipoUsuario.INSTITUCIONAL.equals(this.usuario.getTipoUsuario())) {
                this.usuario.setDescricao(this.descricao.getText().toString());
                this.usuario.setCpfCnpj(this.cpfCnpj.getText().toString());
            }
            this.editarUsuario(this.usuario);
        });

        return v;
    }

    public String imageToBase64() {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteOutput);
        byte[] imgBytes = byteOutput.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            this.imgMessage.setText("Imagem carregada!");
            this.imgMessage.setVisibility(View.VISIBLE);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Erro", "deu erro isso ae");
            }
        }

    }

    private void editarUsuario(Usuario usuario) {
        this.usuarioService.editar(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    ControleActivity.USUARIO = response.body();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getActivity(), ErrorResponse.formatErrorResponse(response), Toast.LENGTH_LONG).show();
                    Log.i("DEBUG", "RESPONSE ERROR: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getActivity(), "Falha ao conectar com o servidor, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                Log.i("DEBUG", "THROW ERROR: " + t.getMessage());
            }
        });
    }

    private void inicializaComponentes(View v) {
        this.nome = v.findViewById(R.id.et_nome_editarusuario);
        this.email = v.findViewById(R.id.et_email_editarusuario);
        this.cpfCnpj = v.findViewById(R.id.et_cpf_cnpj_editarusuario);
        this.descricao = v.findViewById(R.id.et_descricao_editarusuario);
        this.salvar = v.findViewById(R.id.bt_salvar_editarusuario);
        this.instituccional = v.findViewById(R.id.ll_instituiucao_editarusuario);
        this.btnFoto = v.findViewById(R.id.btn_foto_editar_usuario);
        this.imgMessage = v.findViewById(R.id.img_message_editar_usuario);
        this.imgMessage.setVisibility(View.GONE);

        this.retrofit = RetrofitConfig.generateRetrofit();
        this.usuarioService = this.retrofit.create(UsuarioService.class);
        this.usuario = ControleActivity.USUARIO.getCopy();

        this.carregarCampos();
    }

    private void carregarCampos() {
        this.nome.setText(this.usuario.getNome());
        this.email.setText(this.usuario.getEmail());

        if (TipoUsuario.INSTITUCIONAL.equals(this.usuario.getTipoUsuario())) {
            this.instituccional.setVisibility(View.VISIBLE);
            this.cpfCnpj.setText(this.usuario.getCpfCnpj());
            this.descricao.setText(this.usuario.getDescricao());
        }
    }
}
