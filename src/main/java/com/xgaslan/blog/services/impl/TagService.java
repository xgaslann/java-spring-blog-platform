package com.xgaslan.blog.services.impl;

import com.xgaslan.blog.domain.entities.Tag;
import com.xgaslan.blog.repositories.ITagRepository;
import com.xgaslan.blog.services.ITagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {

    private final ITagRepository repository;

    @Override
    public List<Tag> getAll() {
        return repository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> create(Set<String> tags) {
        List<Tag> existingTags = repository.findByNameIn(tags);
        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName).collect(Collectors.toSet());

        List<Tag> newTags = tags.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder().name(name)
                        .posts(new HashSet<>()).build())
                .toList();
        List<Tag> savedTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedTags = repository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);

        return savedTags;
    }

    @Override
    public Tag getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id: " + id));
    }

    @Override
    public List<Tag> getByIds(Set<UUID> ids) {
        List<Tag> tags = repository.findAllById(ids);
        if (tags.size() != ids.size()) {
            throw new EntityNotFoundException("Some tags not found for the provided IDs");
        }
        return tags;
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).ifPresent(tag -> {
                    if (!tag.isDeleted()) {
                        if (!tag.getPosts().isEmpty()) {
                            throw new IllegalStateException("Cannot delete tag with existing posts");
                        }
                        repository.deleteById(id);
                    }
                }
        );
    }
}
