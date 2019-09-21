package com.example.evaldo.firebase.activity.Administrador.Manutencao;

import android.content.Intent;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CadastrarDispositivoAdministradorActivity extends AppCompatActivity {

    EditText nomeDispositivo;
    TextView idDispositivo;
    Spinner statusDispositivo;

    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference DispositivosAdministradoresReferencia = databaseReferencia.child("Dispositivos Administradores");

    String postId = UUID.randomUUID().toString();
    String dataCadastro = pegandoHora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dispositivo);

        idDispositivo = findViewById(R.id.tv_id_dispositivo);
        idDispositivo.setText("Id idDispositivo= " + pegarIDDispositivo());
        nomeDispositivo = findViewById(R.id.et_dispositivo_nome);

        statusDispositivo =  findViewById(R.id.sp_status_administrador);
        ArrayAdapter statusSppinerAdapter = ArrayAdapter.createFromResource(this, R.array.lista_de_status_dos_dispositivos, android.R.layout.simple_spinner_item);
        statusDispositivo.setAdapter(statusSppinerAdapter);


    }

    public void clickDispositivoAtivar(View view) {
        if  (nomeDispositivo.getText().toString().equals("") ){
            Toast.makeText(this, "Por favor insira o nome do Dispositivo", Toast.LENGTH_LONG).show();
        } else if (false) {
            Toast.makeText(this, "Por favor insira o status do Dispositivo", Toast.LENGTH_LONG).show();
        }else {

            DispositivosAdministradoresReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("idDispositivo").setValue(pegarIDDispositivo());
            DispositivosAdministradoresReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("nomeDispositivo").setValue(nomeDispositivo.getText().toString());
            DispositivosAdministradoresReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("status").setValue(statusDispositivo.getSelectedItem().toString());
            DispositivosAdministradoresReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("dataAtivacao").setValue(dataCadastro);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //////////////////////////MENU CARREGANDO//////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_compartilhar:
                Toast.makeText(this, "Compartilhar", Toast.LENGTH_LONG).show();
                return true;

            case R.id.item_criar_pdf:
                Toast.makeText(this, "Gerar PDF", Toast.LENGTH_LONG).show();
                //item_gerarPDF();
                return true;
            case R.id.item_voltar:
                Toast.makeText(this, "Voltar", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                finish();
                startActivity(intent);
                //item_voltar();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
