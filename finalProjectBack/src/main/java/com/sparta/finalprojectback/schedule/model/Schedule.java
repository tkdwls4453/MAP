package com.sparta.finalprojectback.schedule.model;

import com.sparta.finalprojectback.member.model.Timestamped;
import com.sparta.finalprojectback.post.model.Post;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String link;

    @Column(length = 100)
    private String address;

    private float x;

    private float y;

    private int date;

    @Column(length = 30)
    private String placeName;

    private String phone;

    //FK
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
}
