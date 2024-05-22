package com.example.hotel.services;
import com.example.hotel.personDTO.GetPersonDTO;
import com.example.hotel.personDTO.PersonDTO;
import com.example.hotel.personDTO.PersonResponseDTO;
import com.example.hotel.entity.Person;
import com.example.hotel.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDTO savePerson(PersonDTO personDTO) {
        if (personDTO.getId() != null && personRepository.existsById(personDTO.getId())) {
            throw new RuntimeException("Person with ID " + personDTO.getId() + " already exists");
        }
        Person person = new Person();
        person.setUsername(personDTO.getUsername());
        person.setName(personDTO.getName());
        person.setLastName(personDTO.getLastName());
        person.setPersonBalance(personDTO.getPersonBalance());
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }
    public GetPersonDTO getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        return convertToGetPersonDTO(person) ;
    }
    public List<PersonResponseDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
    private PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setUsername(person.getUsername());
        dto.setName(person.getName());
        dto.setLastName(person.getLastName());
        dto.setPersonBalance(person.getPersonBalance());
        return dto;
    }
    private PersonResponseDTO convertToResponseDTO(Person person) {
        PersonResponseDTO dto = new PersonResponseDTO();
        dto.setUsername(person.getUsername());
        dto.setName(person.getName());
        dto.setLastName(person.getLastName());
        return dto;
    }
    private GetPersonDTO convertToGetPersonDTO(Person person) {
        GetPersonDTO dto = new GetPersonDTO();
        dto.setUsername(person.getUsername());
        dto.setName(person.getName());
        dto.setLastName(person.getLastName());
        dto.setPersonBalance(person.getPersonBalance());
        return dto;
    }
}
