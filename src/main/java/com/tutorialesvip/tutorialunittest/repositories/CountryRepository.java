package com.tutorialesvip.tutorialunittest.repositories;

import com.tutorialesvip.tutorialunittest.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
Clase con una funcion que busca los paises por el codio ISO, por ejemplo CO es COlombia y nos devuelve respuesta de tipo pais
 */

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findCountryByIsoCode(String isoCode);
}
