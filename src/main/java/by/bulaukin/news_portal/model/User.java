package by.bulaukin.news_portal.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private String email;
    @CreationTimestamp
    private Instant registrationAt;

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<News> newsList = new ArrayList<>();

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Comment> commentsList = new ArrayList<>();
//
//    public void AddNews(UpsertNewsRequest news) {
//        if(newsList == null) {
//            newsList = new ArrayList<>();
//        }
//        newsList.add(news);
//    }
//
//    public void removeNews(Long newsId) {
//        newsList = newsList.stream().filter(news -> !(news.getId().equals(newsId))).toList();
//    }
//
//    public void addCommentsList(Comment comment) {
//        if (commentsList == null) {
//            commentsList = new ArrayList<Comment>();
//        }
//        commentsList.add(comment);
//    }
//
//    public void removeComment(Long commentId) {
//        commentsList = commentsList.stream().filter(comment -> !(comment.getId().equals(commentId))).toList();
//    }

}
