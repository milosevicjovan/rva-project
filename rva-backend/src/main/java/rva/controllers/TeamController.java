package rva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rva.jpa.Team;
import rva.repositories.TeamRepository;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository repository;

    @GetMapping("teams/all")
    public List<Team> getTeamsAll() {
        return repository.findAll();
    }

    @GetMapping("teams/{id}")
    public Team getTeamById(@PathVariable("id") Integer id) {
        return repository.getById(id);
    }

    @GetMapping("teams/search/{name}")
    public List<Team> getTeamsByName(@PathVariable("name") String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @PostMapping("teams/insert")
    public ResponseEntity<Team> insertTeam(@RequestBody Team team) {
        if (repository.existsById(team.getId())) {
            return new ResponseEntity<Team>(HttpStatus.CONFLICT);
        }
        repository.save(team);
        return new ResponseEntity<Team>(HttpStatus.OK);
    }

    @PutMapping("teams/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        if (!repository.existsById(team.getId())) {
            return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
        }
        repository.save(team);
        return new ResponseEntity<Team>(HttpStatus.OK);
    }

    @DeleteMapping("teams/{id}/delete")
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") Integer id) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
        }
        repository.deleteById(id);
        return new ResponseEntity<Team>(HttpStatus.OK);
    }
}
