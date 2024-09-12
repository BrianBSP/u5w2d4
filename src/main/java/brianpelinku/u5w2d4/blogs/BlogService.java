package brianpelinku.u5w2d4.blogs;

import brianpelinku.u5w2d4.authors.Author;
import brianpelinku.u5w2d4.authors.AuthorService;
import brianpelinku.u5w2d4.exceptions.NotFoundException;
import brianpelinku.u5w2d4.payloads.NewBlogPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AuthorService authorService;

    // 1. Get: /blogs --> findAll
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // 2. Get: /blogs/{blogId} --> findById
    public Blog getBlog(int blogId) {
        return blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
    }

    // 3. Post: /blogs
    public Blog saveBlog(NewBlogPayload blogBody) {
        Author author = authorService.findById(blogBody.getAuthorId());
        Blog newBlog = new Blog();
        newBlog.setTitolo(blogBody.getTitolo());
        newBlog.setContenuto(blogBody.getContenuto());
        newBlog.setCategoria(blogBody.getCategoria());
        newBlog.setTempoDiLettura(blogBody.getTempoDiLettura());
        newBlog.setAuthor(author);
        newBlog.setCover("https://picsum.photos/200/300");

        return this.blogRepository.save(newBlog);
    }

    // 4. Put: /blogs/{blogId}
    public Blog getBlogByIdAndUpdate(int blogId, NewBlogPayload bodyBlog) {
        Blog found = this.getBlog(blogId);

        found.setTitolo(bodyBlog.getTitolo());
        found.setContenuto(bodyBlog.getContenuto());
        found.setCategoria(bodyBlog.getCategoria());
        found.setTempoDiLettura(bodyBlog.getTempoDiLettura());

        if (found.getAuthor().getId() != bodyBlog.getAuthorId()) {
            Author newAuthor = authorService.findById(bodyBlog.getAuthorId());
            found.setAuthor(newAuthor);
        }
        return blogRepository.save(found);
    }

    // 5. Delete: /blogs/{blogId}
    public void getBlogByIdAndDelete(int blogId) {
        Blog found = this.getBlog(blogId);
        blogRepository.delete(found);
    }
}
