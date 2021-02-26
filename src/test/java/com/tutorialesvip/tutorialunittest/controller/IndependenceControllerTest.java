package com.tutorialesvip.tutorialunittest.controller;

import com.tutorialesvip.tutorialunittest.models.Country;
import com.tutorialesvip.tutorialunittest.models.CountryResponse;
import com.tutorialesvip.tutorialunittest.repositories.CountryRepository;
import com.tutorialesvip.tutorialunittest.util.DiferenciaEntreFechas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

class IndependenceControllerTest {

    @Autowired
    CountryResponse countryResponse;

    @Autowired
    Optional<Country> country;

    //Mockito evita que se vaya a la base de datos
    //Mock clase falsa que mockito manipula
    CountryRepository countryRepositoryMock = Mockito.mock(CountryRepository.class);

    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas = new DiferenciaEntreFechas();

    @Autowired
    IndependenceController independenceController = new IndependenceController(countryRepositoryMock,diferenciaEntreFechas);

    /*
    Se ejecuta antes de cada @Test
     */
    @BeforeEach
    void setUp() {
        Country mockCountry = new Country();
        mockCountry.setIsoCode("ME");
        mockCountry.setCountryIdependenceDate("20/07/1810");
        mockCountry.setCountryId((long) 1);
        mockCountry.setCountryName("Mexico");
        mockCountry.setCountryCapital("mexico");

        Mockito.when(countryRepositoryMock.findCountryByIsoCode("ME")).thenReturn(mockCountry);

    }

    @Test
    void getCountryDetailsWithValidCountryCode() {
        ResponseEntity<CountryResponse> respuestaServicio;
        respuestaServicio = independenceController.getCountries("ME");
        Assertions.assertEquals("Mexico",respuestaServicio.getBody().getCountryName());
        Assertions.assertEquals("mexico",respuestaServicio.getBody().getCapitalName());
        Assertions.assertEquals("20/07/1810",respuestaServicio.getBody().getIndependenceDate());
        Assertions.assertEquals(210,respuestaServicio.getBody().getYearsOfIndependency());
        Assertions.assertEquals(7,respuestaServicio.getBody().getMonthsOfIndependency());
        Assertions.assertEquals(6,respuestaServicio.getBody().getDayssOfIndependency());

    }

    @Test
    void getCountryDetailsWithInvalidCountryCode() {
        ResponseEntity<CountryResponse> respuestaServicio;
        respuestaServicio = independenceController.getCountries("BR");
        Assertions.assertNull(respuestaServicio.getBody().getCountryName());
        Assertions.assertNull(respuestaServicio.getBody().getCapitalName());
        Assertions.assertNull(respuestaServicio.getBody().getIndependenceDate());
        Assertions.assertEquals(0,respuestaServicio.getBody().getYearsOfIndependency());
        Assertions.assertEquals(0,respuestaServicio.getBody().getMonthsOfIndependency());
        Assertions.assertEquals(0,respuestaServicio.getBody().getDayssOfIndependency());
    }




}