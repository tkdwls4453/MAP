package com.sparta.finalprojectback.post.model;

import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.member.model.Timestamped;
import com.sparta.finalprojectback.postComment.model.PostComment;
import com.sparta.finalprojectback.schedule.model.Schedule;
import lombok.*;

import javax.persistence.*;
import java.util.List;

//PostsRepository에서 Setter 설정함
@NoArgsConstructor // 기본생성자를 만듭니다.
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 45)
    private String title;

    @Column(length = 200)
    private String image;

    private int views;

    private Category category;

    private int likes;

    @Column(length = 300)
    private String content;

    private boolean isComplete;

    private int period;

    // LAZY - 삭제 동작 안해서 다시 변경
    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    // 좋아요 매핑
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Likes> likesList;


    // 댓글 매핑
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<PostComment> postComments;


    // 스케줄 매핑
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Schedule> schedules;


    public void updatePost(String title, Category category, int period, boolean isComplete) {
        this.title = title;
        this.category = category;
        this.period = period;
        this.isComplete = isComplete;
    }

    public void updateImage(String image){
        this.image = image;
    }

    // 좋아요 개수 증가/감소
    public void updateLike(int num) {
        likes += num;
    }
    public void countingView(int num1) {
        views += num1;
    }
}