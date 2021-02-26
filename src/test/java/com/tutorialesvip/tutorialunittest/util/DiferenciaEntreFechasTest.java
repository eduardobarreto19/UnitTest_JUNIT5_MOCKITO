package com.tutorialesvip.tutorialunittest.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.Period;

public class DiferenciaEntreFechasTest {

    //Para que spring lo inyecte se usa la anotacion @Autowired
    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas;


    /*
    Para la prueba se nececita un valor esperado y el valor con el que vamos a comparar
    En las pruebas no se debe imprimir ni ejecutar LOGS
    Las pruebas unitarias deben ser rapidas.
     */
    @Test
    void calculateYearsOfIndependency() {
        diferenciaEntreFechas = new DiferenciaEntreFechas();
        LocalDate today = LocalDate.now();
        String independenceDay = "20/07/1810";

        Period result = diferenciaEntreFechas.calculateYearsOfIndependency(independenceDay);

        Assertions.assertEquals(7, result.getMonths());
        Assertions.assertEquals(6, result.getDays());
        Assertions.assertEquals(210, result.getYears());

    }

    @Test
    void prueba (){
        int num1 = 2;
        int num2 = 3;
        Assertions.assertEquals(5, num1+num2);
    }
}