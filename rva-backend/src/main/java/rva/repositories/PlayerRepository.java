package rva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rva.jpa.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByFirstNameContainingAndLastNameContainingIgnoreCase(String firstName, String lastName);
}
