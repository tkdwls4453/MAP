package com.sparta.finalprojectback.s3.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.member.repository.MemberRepository;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    private final AwsS3Service s3Service;
    private final PostRepository postRepository;

    private final MemberRepository memberRepository;

    //Multipart를 통해 전송된 파일을 업로드 하는 메소드
    @Transactional
    public String uploadImage(MultipartFile file, Long postId, String path) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디 입니다.")
        );

        String fileName = path + "/" + createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e){
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()));
        }
        String imageUrl = s3Service.getFileUrl(fileName);
        post.updateImage(imageUrl);
        return imageUrl;
    }

    @Transactional
    public String uploadImage(MultipartFile file, Member member, String path) {
        Member targetMember = memberRepository.findById(member.getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );

        String fileName = path + "/" + createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e){
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()));
        }
        String imageUrl = s3Service.getFileUrl(fileName);
        targetMember.updateImage(imageUrl);
        return imageUrl;
    }

    public Long deleteImage(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디 입니다.")
        );

        System.out.println(post.getImage());
        if(post.getImage().equals("입력 없음")){
            return postId;
        }
        s3Service.deleteFile(post.getImage());
        return postId;
    }

    // 파일 읽어오기
    public String importImage(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않느 게시물 아이디 입니다.")
        );

        return post.getImage();
    }

    // 기존 확장자명을 유지한 채, 유니크한 파일의 이름을 생성하는 로직
    private String createFileName(String originalFileName){
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    // 파일의 확장자명을 가져오는 로직
    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        }catch (StringIndexOutOfBoundsException e){
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다.", fileName));
        }
    }


}
