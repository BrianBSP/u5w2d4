package brianpelinku.u5w2d4.authors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // faccio una query che verifichi se l'email è già stata inserita nel db
    // entrambi i metodi sono validi
    // 1
    boolean existsByEmail(String email);
    // 2
    Optional<Author> findByEmail(String email);
}
