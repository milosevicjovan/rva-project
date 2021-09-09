package rva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rva.jpa.League;
import rva.repositories.LeagueRepository;

import java.util.List;

@RestController
public class LeagueController {

    @Autowired
    private LeagueRepository repository;

    @GetMapping("leagues/all")
    public List<League> getLeaguesAll() {
        return repository.findAll();
    }

    @GetMapping("leagues/{id}")
    public League getLeagueById(@PathVariable("id") Integer id) {
        return repository.getById(id);
    }

    @GetMapping("leagues/search/{name}")
    public List<League> getLeaguesByName(@PathVariable("name") String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @PostMapping("leagues/insert")
    public ResponseEntity<League> insertLeague(@RequestBody League league) {
        if (repository.existsById(league.getId())) {
            return new ResponseEntity<League>(HttpStatus.CONFLICT);
        }
        repository.save(league);
        return new ResponseEntity<League>(HttpStatus.OK);
    }

    @PutMapping("leagues/update")
    public ResponseEntity<League> updateLeague(@RequestBody League league) {
        if (!repository.existsById(league.getId())) {
            return new ResponseEntity<League>(HttpStatus.NO_CONTENT);
        }
        repository.save(league);
        return new ResponseEntity<League>(HttpStatus.OK);
    }

    @DeleteMapping("leagues/{id}/delete")
    public ResponseEntity<League> deleteLeague(@PathVariable("id") Integer id) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<League>(HttpStatus.NO_CONTENT);
        }
        repository.deleteById(id);
        return new ResponseEntity<League>(HttpStatus.OK);
    }
}
