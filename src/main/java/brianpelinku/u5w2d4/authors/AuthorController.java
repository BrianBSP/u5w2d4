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


import brianpelinku.u5w2d4.exceptions.BadRequestException;
import brianpelinku.u5w2d4.payloads.NewAuthorDTO;
import brianpelinku.u5w2d4.payloads.NewAuthorRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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

    // 3. Post: /users + body
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewAuthorRespDTO createAuthor(@RequestBody @Validated NewAuthorDTO bodyAuthor, BindingResult validationResult) {

        if (validationResult.hasErrors()){
            String messages = validationResult
                    .getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Si sono verificati errori nel payload. " + messages);
        } else {
            return new NewAuthorRespDTO(this.authorService.saveAuthor(bodyAuthor).getId());
        }
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
