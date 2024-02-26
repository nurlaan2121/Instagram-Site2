package java12.service.impl;

import java12.dtoes.CommentDTO;
import java12.dtoes.PostDTO;
import java12.entities.Comment;
import java12.entities.Post;
import java12.entities.User;
import java12.repo.PostRepo;
import java12.service.PostInterface;
import java12.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostImpl implements PostInterface {
    private final PostRepo postRepo;
    private final UserInterface userInterface;

    @Override
    public void save(Post newPost) {
        newPost.setComments(new ArrayList<>());
        newPost.setLikes(new ArrayList<>());
        newPost.setCreatedAd(Date.valueOf(LocalDate.now()));
        User currentUser = userInterface.getCurrentUser();
        System.out.println(currentUser.getId() + "   " + currentUser.getUserName());
        currentUser.getPosts().add(newPost);
        newPost.setUser(currentUser);
        postRepo.save(newPost);
    }

    @Override
    public List<Post> getAllMyPosts() {
        return postRepo.getAllMyPosts();
    }

    @Override
    public void deletePost(Long id) {
        postRepo.delete(id);
    }

    @Override
    public void update(Post post) {
        postRepo.update(post);
    }

    @Override
    public List<Post> getMyHomePosts() {
        return postRepo.getMyHomePosts();
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepo.getAllPosts();


    }

    @Override
    public void likePost(Long idPost) {
        postRepo.like(idPost);
    }

    @Override
    public List<Post> getLikedPost() {
        return postRepo.getLikedPost();
    }

    @Override
    public boolean isPostLikedByCurrentUser(Long postId) {
        return postRepo.check(postId);
    }

    @Override
    public void unlikePost(Long postId) {
        postRepo.unLike(postId);
    }

    @Override
    public void addComment(Long postId, String commentText) {
        postRepo.comment(postId, commentText);
    }

    @Override
    public List<PostDTO> search(String keyword) {
        return postRepo.search("%" + keyword + "%");
    }

    @Override
    public List<CommentDTO> infoAboutCurrentPost(Long id) {
        return postRepo.getInfoAboutCurrentPost(id);
    }

    @Override
    public void addLikeToComment(Long commentId) {
        postRepo.likeComment(commentId);
    }
}
