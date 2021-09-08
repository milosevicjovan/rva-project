package rva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import rva.jpa.Nationality;
import rva.repositories.NationalityRepository;

import java.util.List;


@RestController
public class NationalityController {

    @Autowired
    private NationalityRepository repository;

    @GetMapping("nationalities/all")
    public List<Nationality> getNationalitiesAll() {
        return repository.findAll();
    }

    @GetMapping("nationalities/{id}")
    public Nationality getNationalityById(@PathVariable("id") Integer id) {
        return repository.getById(id);
    }

    @GetMapping("nationalities/search/{name}")
    public List<Nationality> getNationalitiesByName(@PathVariable("name") String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @PostMapping("nationalities/insert")
    public ResponseEntity<Nationality> insertNationality(@RequestBody Nationality nationality) {
        if (repository.existsById(nationality.getId())) {
            return new ResponseEntity<Nationality>(HttpStatus.CONFLICT);
        }
        repository.save(nationality);
        return new ResponseEntity<Nationality>(HttpStatus.OK);
    }

    @PutMapping("nationalities/update")
    public ResponseEntity<Nationality> updateNationality(@RequestBody Nationality nationality) {
        if (!repository.existsById(nationality.getId())) {
            return new ResponseEntity<Nationality>(HttpStatus.NO_CONTENT);
        }
        repository.save(nationality);
        return new ResponseEntity<Nationality>(HttpStatus.OK);
    }

    @DeleteMapping("nationalities/{id}/delete")
    public ResponseEntity<Nationality> deleteNationality(@PathVariable("id") Integer id) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<Nationality>(HttpStatus.NO_CONTENT);
        }
        repository.deleteById(id);
        return new ResponseEntity<Nationality>(HttpStatus.OK);
    }
}
