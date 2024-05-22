package com.example.hotel.controller;
import com.example.hotel.personDTO.GetPersonDTO;
import com.example.hotel.personDTO.PersonDTO;
import com.example.hotel.personDTO.PersonResponseDTO;
import com.example.hotel.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/users")
public class PersonController {

    @Autowired
    private PersonService personService;
    @PostMapping
    public PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
        return personService.savePerson(personDTO);
    }
    @GetMapping("/get/{id}")
    public GetPersonDTO getPerson(@PathVariable Long id) {
        return personService.getPersonById(id);
    }
    @GetMapping("/all")
    public List<PersonResponseDTO> getAllPersons() {
        return personService.getAllPersons();
    }
    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
    }
}
