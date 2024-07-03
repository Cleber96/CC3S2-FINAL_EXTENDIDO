package org.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClimaTest {

    @Mock
    private Sensor mockSensor;

    private Clima clima;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clima = new Clima();
        List<Sensor> sensores = new ArrayList<>();
        for (int i = 0; i < clima.sensores.size(); i++) {
            sensores.add(mock(Sensor.class));
        }
        clima.sensores = sensores;
    }

    @Test
    public void testAlertas() {
        for (Sensor sensor : clima.sensores) {
            when(sensor.verificarAlerta()).thenReturn(true);
            when(sensor.getMensajeAlerta()).thenReturn("Alerta");
            when(sensor.getAuxiliar()).thenReturn("Auxiliar");
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        clima.alertas();

        for (Sensor sensor : clima.sensores) {
            verify(sensor).verificarAlerta();
            assertTrue(outContent.toString().contains("Alerta - Auxiliar"));
        }
    }

    @Test
    public void testOpcionElegida() {
        Sensor mockSensor = mock(Sensor.class);
        clima.sensores.set(0, mockSensor);
        Clima.in = new Scanner("1\n25.0\n");
        clima.opcionElegida(1);
        verify(mockSensor).setValor(1.0);
    }

    @Test
    public void testMenu() {
        Sensor mockSensor = mock(Sensor.class);
        clima.sensores.set(0, mockSensor);
        Clima.in = new Scanner("1\n25.0\n");
        clima.menu();
        verify(mockSensor).setValor(25.0);
    }
}
