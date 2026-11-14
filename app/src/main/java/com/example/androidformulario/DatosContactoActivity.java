package com.example.androidformulario;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DatosContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);

        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String telefono = getIntent().getStringExtra("telefono");
        String email = getIntent().getStringExtra("email");

        TextView tvDatosContacto = findViewById(R.id.tvDatosContacto);

        tvDatosContacto.setText(
                "Nombre: " + nombre +
                        "\nApellido: " + apellido +
                        "\nTeléfono: " + telefono +
                        "\nEmail: " + email
        );
    }
}