package com.example.evaldo.firebase.activity.Administrador.Manutencao;

import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CadastrarTerminalDePesquisa extends AppCompatActivity {

    EditText nomeDispositivoKiosque;
    TextView idDispositivoKiosque;
    Spinner statusDispositivoKiosque;

    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference DispositivosKiosqueReferencia = databaseReferencia.child("Terminais de Pesquisa");

    String postId = UUID.randomUUID().toString();
    String dataCadastro = pegandoHora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dispostivo_kiosque);

        idDispositivoKiosque = findViewById(R.id.tv_cadastrarDispositivoKiosque_idDispositivo);
        nomeDispositivoKiosque = findViewById(R.id.et_cadastrarDispositivoKiosque_nomeDispositivo);
        idDispositivoKiosque.setText("ID do Dispositivo= " + pegarIDDispositivo());

        statusDispositivoKiosque =  findViewById(R.id.sp_status_kiosque);
        ArrayAdapter statusSppinerAdapter = ArrayAdapter.createFromResource(this, R.array.lista_de_status_dos_dispositivos, android.R.layout.simple_spinner_item);
        statusDispositivoKiosque.setAdapter(statusSppinerAdapter);
    }

    public void clickAtivarDispositivo(View view) {
        if  (idDispositivoKiosque.getText().toString().equals("") ){
            Toast.makeText(this, "Por favor insira o nome do Dispositivo", Toast.LENGTH_LONG).show();
        } else if (false) {
            Toast.makeText(this, "Por favor insira o status do Dispositivo", Toast.LENGTH_LONG).show();
        }else {
            DispositivosKiosqueReferencia.child(pegarIDDispositivo()).child("idDispositivo").setValue(pegarIDDispositivo());
            DispositivosKiosqueReferencia.child(pegarIDDispositivo()).child("nomeDispositivo").setValue(nomeDispositivoKiosque.getText().toString());
            DispositivosKiosqueReferencia.child(pegarIDDispositivo()).child("dataAtivacao").setValue(dataCadastro);
            DispositivosKiosqueReferencia.child(pegarIDDispositivo()).child("status").setValue(statusDispositivoKiosque.getSelectedItem().toString());
            DispositivosKiosqueReferencia.child(pegarIDDispositivo()).child("questionarioAtual").setValue("SEM QUESTIONARIO");

            pegarIDDispositivo();

            Toast.makeText(this, "Dispositivo cadastrado com sucesso", Toast.LENGTH_LONG).show();
            finish();

        }
    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    private String pegandoHora() {
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada ;
        System.out.println("Data " + dataFormatada + " Hora " + horaFormatada );

        return dataEHora;
    }
}
