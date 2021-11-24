 package com.example.appjardas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {

    TextView tv_nomeInserido;
    Button bt_ok;
    EditText et_nome;
    Spinner sp_medida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nome = findViewById(R.id.et_nome);
        bt_ok = findViewById(R.id.bt_ok);
        tv_nomeInserido = findViewById(R.id.tv_nomeInserido);
        sp_medida = findViewById(R.id.sp_medida);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.medida_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp_medida.setAdapter(adapter);

        final String[] medida = new String[1];

        sp_medida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medida[0] = parent.getItemAtPosition(position).toString();
                escondeTeclado(view);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double valor = Double.parseDouble(et_nome.getText().toString());

                //1 jarda equivale a 0,9144 metro ou 1 jarda = 0,9144 m
                if(medida[0].equals("Jarda para Centimetro")){
                    valor = valor * 91.44;
                    tv_nomeInserido.setText(valor + " centimetros");
                }
                else {
                    valor = valor/91.44 ;
                    tv_nomeInserido.setText(valor + " jardas");
                }
                escondeTeclado(v);
            }
        });

    }

     private void escondeTeclado(View view) {
         if (view != null) {
             InputMethodManager inputMethodManager = (InputMethodManager)
                     getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
             inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
         }

     }
}