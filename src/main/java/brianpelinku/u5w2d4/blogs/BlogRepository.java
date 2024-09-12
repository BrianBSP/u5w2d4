package brianpelinku.u5w2d4.blogs;

import brianpelinku.u5w2d4.authors.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findByAuthor(Author author);
}
