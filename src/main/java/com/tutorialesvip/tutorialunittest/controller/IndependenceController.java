package com.tutorialesvip.tutorialunittest.controller;

import com.tutorialesvip.tutorialunittest.models.Country;
import com.tutorialesvip.tutorialunittest.models.CountryResponse;
import com.tutorialesvip.tutorialunittest.repositories.CountryRepository;
import com.tutorialesvip.tutorialunittest.util.DiferenciaEntreFechas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Period;
import java.util.Optional;

/**
 * Author: Luis Eduardo Barreto Santamaria
 */
@RestController()
public class IndependenceController {

    CountryResponse countryResponse;
    Optional<Country> country;
    CountryRepository countryRepository;
    DiferenciaEntreFechas diferenciaEntreFechas;

    public IndependenceController(CountryRepository countryRepository, DiferenciaEntreFechas diferenciaEntreFechas) {
        this.countryRepository = countryRepository;
        this.diferenciaEntreFechas = diferenciaEntreFechas;
    }

    @GetMapping(path = "/country/{countryISO}")
    public ResponseEntity<CountryResponse> getCountries(@PathVariable("countryISO") String countryISO) {
        country = Optional.of(new Country());
        countryResponse = new CountryResponse();

        country = Optional.ofNullable(countryRepository.findCountryByIsoCode(countryISO.toUpperCase()));

        if (country.isPresent()) {
            Period period = diferenciaEntreFechas.calculateYearsOfIndependency(country.get().getCountryIdependenceDate());
            countryResponse.setCountryName(country.get().getCountryName());
            countryResponse.setCapitalName(country.get().getCountryCapital());
            countryResponse.setIndependenceDate(country.get().getCountryIdependenceDate());
            countryResponse.setDayssOfIndependency(period.getDays());
            countryResponse.setMonthsOfIndependency(period.getMonths());
            countryResponse.setYearsOfIndependency(period.getYears());
        }
        return ResponseEntity.ok(countryResponse);
    }
}