package com.example.hotel.services;

import com.example.hotel.classes.Person;
import com.example.hotel.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person) {
        log.info("Person value in controller: " + person);
        return personRepository.save(person);

    }

    public Person getPerson(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
