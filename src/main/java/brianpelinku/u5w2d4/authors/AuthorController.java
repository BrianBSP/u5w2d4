package brianpelinku.u5w2d4.authors;

/*
 * ------user crud-------
 * 1. Get: /authors --> findAll
 * 2. Get: /authors/{userId} --> findById
 * 3. Post: /authors
 * 4. Put: /authors/{userId}
 * 5. Delete: /authors/{userId}
 *
 * */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // 1. Get: /users --> findAll
    @GetMapping
    public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return this.authorService.findAll(page, size, sortBy);
    }

    // 2. Get: /users/{userId} --> findById
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable int authorId) {
        return authorService.findById(authorId);
    }

    // 3. Post: /users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author bodyAuthor) {
        return authorService.saveAuthor(bodyAuthor);
    }

    // 4. Put: /users/{userId}
    @PutMapping("/{authorId}")
    public Author findAuthorByIdAndUpdate(@PathVariable int authorId, @RequestBody Author authorUpdate) {
        return this.authorService.findByIdAndUpdate(authorId, authorUpdate);
    }

    // 5. Delete: /users/{userId}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAuthorByIdAndDelete(@PathVariable int authorId) {
        authorService.findByIdAndDelete(authorId);
    }


}
