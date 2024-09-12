package brianpelinku.u5w2d4.blogs;

import brianpelinku.u5w2d4.payloads.NewBlogPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * ------user crud-------
 * 1. Get: /blogs --> findAll
 * 2. Get: /blogs/{blogId} --> findById
 * 3. Post: /blogs
 * 4. Put: /blogs/{blogId}
 * 5. Delete: /blogs/{blogId}
 *
 * */

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // 3. Post: /blogs
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Blog saveBlog(@RequestBody NewBlogPayload blog) {
        return blogService.saveBlog(blog);
    }

    // 1. Get: /blogs --> findAll
    @GetMapping("")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // 2. Get: /blogs/{blogId} --> findById
    @GetMapping("/{blogId}")
    public Blog getBlog(@PathVariable int blogId) {
        return blogService.getBlog(blogId);
    }


    // 4. Put: /blogs/{blogId}
    @PutMapping("/{blogId}")
    public Blog getBlogByIdAndUpdate(@PathVariable int blogId, @RequestBody NewBlogPayload blog) {
        return blogService.getBlogByIdAndUpdate(blogId, blog);
    }

    // 5. Delete: /blogs/{blogId}
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable int blogId) {
        blogService.getBlogByIdAndDelete(blogId);
    }

}
