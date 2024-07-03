package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SensorTest {

    @Mock
    private Alerta mockAlerta;

    private Sensor sensor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sensor = new Temperatura(0, mockAlerta);
    }

    @Test
    public void testVerificarAlerta() {
        when(mockAlerta.verificar(sensor.getValor(), 20)).thenReturn(true);
        assertTrue(sensor.verificarAlerta());

        when(mockAlerta.verificar(sensor.getValor(), 20)).thenReturn(false);
        assertFalse(sensor.verificarAlerta());
    }

    @Test
    public void testGetAuxiliar() {
        assertEquals("Activar sistema de riego", sensor.getAuxiliar());
    }

    @Test
    public void testGetMensajeAlerta() {
        assertEquals("Temperatura Alta", sensor.getMensajeAlerta());
    }

    @Test
    public void testSetValor() {
        sensor.setValor(30);
        assertEquals(30, sensor.getValor());
    }
}
