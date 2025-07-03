package com.xgaslan.blog.controllers;

import com.xgaslan.blog.domain.entities.User;
import com.xgaslan.blog.domain.mappers.IPostMapper;
import com.xgaslan.blog.domain.models.post.PostCreateModel;
import com.xgaslan.blog.domain.models.post.PostUpdateModel;
import com.xgaslan.blog.domain.models.post.PostViewModel;
import com.xgaslan.blog.services.IPostService;
import com.xgaslan.blog.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService service;
    private final IPostMapper mapper;
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<PostViewModel>> getAll(@RequestParam(required = false) UUID categoryId, @RequestParam(required = false) UUID tagId){
        var posts = service.getAll(categoryId, tagId);
        List<PostViewModel> models = posts.stream().map(mapper::toViewModel).toList();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/drafts")
    public ResponseEntity<List<PostViewModel>> getDrafts(@RequestAttribute UUID userId) {
        User loggedInUser = userService.getById(userId);
        var posts = service.getDrafts(loggedInUser);
        var models = posts.stream().map(mapper::toViewModel).toList();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostViewModel> create(@Valid @RequestBody PostCreateModel model, @RequestAttribute UUID userId) {
        User loggedInUser = userService.getById(userId);
        var post = service.create(model, loggedInUser);
        return new ResponseEntity<>(mapper.toViewModel(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostViewModel> update(@PathVariable UUID id, @Valid @RequestBody PostUpdateModel model, @RequestAttribute UUID userId) {
        User loggedInUser = userService.getById(userId);
        var post = service.update(id, model, loggedInUser);
        return new ResponseEntity<>(mapper.toViewModel(post), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostViewModel> getById(@PathVariable UUID id, @RequestAttribute UUID userId) {
        var post = service.getById(id, userService.getById(userId));
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.toViewModel(post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @RequestAttribute UUID userId) {
        User loggedInUser = userService.getById(userId);
        service.delete(id, loggedInUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
