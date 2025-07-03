package com.xgaslan.blog.services.impl;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.entities.Post;
import com.xgaslan.blog.domain.entities.Tag;
import com.xgaslan.blog.domain.entities.User;
import com.xgaslan.blog.domain.enums.PostStatus;
import com.xgaslan.blog.domain.mappers.IPostMapper;
import com.xgaslan.blog.domain.models.post.PostCreateModel;
import com.xgaslan.blog.domain.models.post.PostUpdateModel;
import com.xgaslan.blog.repositories.IPostRepository;
import com.xgaslan.blog.services.IPostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final IPostRepository repository;
    private final IPostMapper mapper;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Override
    public Post getById(UUID id, User user) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

        if (post.getAuthor().getId().equals(user.getId())) {
            return post;
        } else {
            throw new IllegalArgumentException("You are not authorized to view this post.");
        }
    }

    @Override
    @Transactional
    public List<Post> getAll(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getById(categoryId);
            Tag tag = tagService.getById(tagId);
            return repository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
        }

        if (categoryId != null) {
            Category category = categoryService.getById(categoryId);
            return repository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        }

        if (tagId != null) {
            Tag tag = tagService.getById(tagId);
            return repository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, tag);
        }

        return repository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDrafts(User user) {
        return repository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Transactional
    @Override
    public Post create(PostCreateModel model, User user) {
        Post newPost = Post.builder()
                .title(model.getTitle())
                .content(model.getContent())
                .status(model.getStatus())
                .author(user)
                .readingTime(calculateReadingTime(model.getContent()))
                .category(categoryService.getById(model.getCategoryId()))
                .tags(new HashSet<>(tagService.getByIds(model.getTagIds())))
                .build();

        return repository.save(newPost);
    }

    @Override
    @Transactional
    public Post update(UUID id, PostUpdateModel model, User user) {
        var existingPost = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

        if (!existingPost.getAuthor().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You are not authorized to update this post.");
        }

        existingPost.setTitle(model.getTitle());
        existingPost.setContent(model.getContent());
        existingPost.setStatus(model.getStatus());
        existingPost.setReadingTime(calculateReadingTime(model.getContent()));

        UUID categoryId = model.getCategoryId();
        if (!existingPost.getCategory().getId().equals(categoryId)) {
            Category category = categoryService.getById(categoryId);
            existingPost.setCategory(category);
        }

        var existingTagIds = existingPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        if (!existingTagIds.equals(model.getTagIds())) {
            List<Tag> newTags = tagService.getByIds(model.getTagIds());
            existingPost.setTags(new HashSet<>(newTags));
        }

        return repository.save(existingPost);
    }

    @Override
    public void delete(UUID id, User user) {
        Post post = getById(id, user);

        repository.delete(post);
    }

    private Integer calculateReadingTime(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }

        int wordCount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount / 200);
    }
}
