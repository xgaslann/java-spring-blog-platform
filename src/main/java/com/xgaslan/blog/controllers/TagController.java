package com.xgaslan.blog.controllers;

import com.xgaslan.blog.domain.entities.Tag;
import com.xgaslan.blog.domain.mappers.ITagMapper;
import com.xgaslan.blog.domain.models.tag.TagCreateModel;
import com.xgaslan.blog.domain.models.tag.TagViewModel;
import com.xgaslan.blog.services.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/tags")
@RequiredArgsConstructor
public class TagController {

    private final ITagService service;
    private final ITagMapper mapper;

    @GetMapping
    public ResponseEntity<List<TagViewModel>> getAll(){
        List<Tag> tags = service.getAll();
        List<TagViewModel> tagViewModels = tags.stream()
                .map(mapper::toViewModel)
                .toList();
        return new ResponseEntity<>(tagViewModels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<TagViewModel>> create(@RequestBody TagCreateModel model){
        List<Tag> savedTags = service.create(model.getNames());
        List<TagViewModel> createdTags = savedTags.stream().map(mapper::toViewModel).toList();
        return new ResponseEntity<>(createdTags, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
