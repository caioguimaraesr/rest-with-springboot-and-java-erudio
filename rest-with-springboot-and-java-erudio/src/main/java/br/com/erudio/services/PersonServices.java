package br.com.erudio.services;

import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class PersonServices {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired // injetar a instância dele, caso necessário
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Finding one Person!");
        return repository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding one Person!");
        return repository.findById(id).orElseThrow(() -> new ResourceAccessException("No records found for this ID"));
    }

    public Person create(Person person){
        logger.info("Creating one Person!");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one Person!");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceAccessException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceAccessException("No records found for this ID"));
        repository.delete(entity);
    }
}