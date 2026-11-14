package com.example.androidformulario;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Patterns;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

        btnContinuar.setOnClickListener(v -> validarFormulario());
    }

    private void validarFormulario() {

        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String fecha = etFechaNacimiento.getText().toString().trim();

        // Comprobar campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty() ||
                telefono.isEmpty() || email.isEmpty() ||
                direccion.isEmpty() || fecha.isEmpty()) {

            mostrarMensaje("Completá todos los campos");
            return;
        }

        // Validar nombre y apellido
        if (!nombre.matches("[\\p{L} '’-]+") ||
                !apellido.matches("[\\p{L} '’-]+")) {

            mostrarMensaje("Nombre y apellido no pueden contener números");
            return;
        }

        // Validar teléfono
        if (!telefono.matches("[0-9-]+")) {
            mostrarMensaje("El teléfono solo admite números y guiones");
            return;
        }

        // Validar email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mostrarMensaje("Ingresá un email válido");
            return;
        }

        // Validar fecha
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        formato.setLenient(false);

        try {
            formato.parse(fecha);
        } catch (Exception e) {
            mostrarMensaje("Ingresá una fecha válida (DD/MM/AAAA)");
            return;
        }

        Intent intent = new Intent(MainActivity.this, DatosContactoActivity.class);

        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("telefono", telefono);
        intent.putExtra("tipoTelefono", spinnerTelefono.getSelectedItem().toString());
        intent.putExtra("email", email);
        intent.putExtra("tipoEmail", spinnerEmail.getSelectedItem().toString());
        intent.putExtra("direccion", direccion);
        intent.putExtra("fecha", fecha);

        startActivity(intent);
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}