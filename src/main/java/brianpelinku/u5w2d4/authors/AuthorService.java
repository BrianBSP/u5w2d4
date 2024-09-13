package brianpelinku.u5w2d4.authors;

import brianpelinku.u5w2d4.exceptions.BadRequestException;
import brianpelinku.u5w2d4.exceptions.NotFoundException;
import brianpelinku.u5w2d4.payloads.NewAuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthorService {
    //private List<Author> authorsList = new ArrayList<>();

    @Autowired
    private AuthorRepository authorRepository;

    // salviamo un nuovo autore nel DB
    public Author saveAuthor(NewAuthorDTO body) {
        // Faccio le verifiche prima di creare un nuovo record nel DB
        // 1. verifico l'email se non già utilizzata e se lo è BAD REQUEST 400
        this.authorRepository.findByEmail(body.email()).ifPresent(author -> {
            throw new BadRequestException("L'email " + body.email() + " è già in uso.");
        });
        // 2. se tutto ok -> aggiungo campi creati dal server (server-generated)

        Author newAuthor = new Author(body.nome(), body.cognome(), body.email(), body.dataDiNascita(),
                "https://ui-avatars.com/api/?name="+ body.nome() + "+" + body.cognome());
//        body.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        // salvo il nuovo record
        return this.authorRepository.save(newAuthor);
    }

    public Page<Author> findAll(int page, int size, String sortBy) {
        if (page > 20) page = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorRepository.findAll(pageable);
    }

    public Author findById(int authorId) {
        return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public void findByIdAndDelete(int authorId) {
        Author found = this.findById(authorId);
        this.authorRepository.delete(found);
    }

    public Author findByIdAndUpdate(int authorId, Author updateAuthor) {
        this.authorRepository.findByEmail(updateAuthor.getEmail()).ifPresent(author -> {
            throw new BadRequestException("L'email " + updateAuthor.getEmail() + " è già in uso.");
        });
        Author found = this.findById(authorId);
        found.setNome(updateAuthor.getNome());
        found.setCognome(updateAuthor.getCognome());
        found.setEmail(updateAuthor.getEmail());
        found.setDataDiNascita(updateAuthor.getDataDiNascita());
        found.setAvatar("https://ui-avatars.com/api/?name=" + updateAuthor.getNome() + "+" + updateAuthor.getCognome());
        return this.authorRepository.save(found);
    }


/*
    // 1. Get: /users --> findAll
    public List<Author> getAllAuthors() {
        return this.authorsList;
    }

    // 2. Get: /users/{userId} --> findById
    public Author getAuthor(int userId) {
        Author found = null;
        for (Author user : this.authorsList) {
            if (user.getId() == userId) found = user;
        }
        if (found == null) throw new NotFoundException(userId);
        return found;
    }


    // 4. Put: /users/{userId}
    public Author getAuthorByIdAndUpdate(int userId, Author updateUser) {
        Author found = null;
        for (Author user : this.authorsList) {
            if (user.getId() == userId) {
                found = user;
                found.setNome(updateUser.getNome());
                found.setCognome(updateUser.getCognome());
                found.setEmail(updateUser.getEmail());
            }
        }
        if (found == null) throw new NotFoundException(userId);
        return found;
    }

    // 5. Delete: /users/{userId}
    public void getByIdAndDelete(int userId) {
        Author found = null;
        for (Author user : this.authorsList) {
            if (user.getId() == userId) found = user;
        }
        if (found == null) throw new NotFoundException(userId);
        this.authorsList.remove(found);
    }*/

}
