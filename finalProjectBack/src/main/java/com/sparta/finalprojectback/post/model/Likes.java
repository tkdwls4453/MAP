package com.sparta.finalprojectback.post.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.sparta.finalprojectback.member.model.Member;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;


    public Likes(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

}