package com.example.androidformulario;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellido;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etDireccion;
    private EditText etFechaNacimiento;

    private Spinner spinnerTelefono;
    private Spinner spinnerEmail;

    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);

        spinnerTelefono = findViewById(R.id.spinnerTelefono);
        spinnerEmail = findViewById(R.id.spinnerEmail);

        String[] opciones = {"Casa", "Trabajo", "Móvil"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opciones
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerTelefono.setAdapter(adapter);
        spinnerEmail.setAdapter(adapter);

        btnContinuar = findViewById(R.id.btnContinuar);

        btnContinuar.setOnClickListener(v -> {

            String nombre = etNombre.getText().toString().trim();
            String apellido = etApellido.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String tipoTelefono = spinnerTelefono.getSelectedItem().toString();
            String tipoEmail = spinnerEmail.getSelectedItem().toString();

            if (nombre.isEmpty() || apellido.isEmpty()
                    || telefono.isEmpty() || email.isEmpty()
                    || etDireccion.getText().toString().trim().isEmpty()
                    || etFechaNacimiento.getText().toString().trim().isEmpty()) {

                Toast.makeText(this,
                        "Completá todos los campos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this,
                    "Contacto: " + nombre + " " + apellido,
                    Toast.LENGTH_LONG).show();
        });
    }
}