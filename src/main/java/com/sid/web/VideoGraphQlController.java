package com.sid.web;

import com.sid.Dto.CreatorRequest;
import com.sid.Dto.VideoRequest;
import com.sid.entity.Creator;
import com.sid.entity.Video;
import com.sid.repository.CreatorRepository;
import com.sid.repository.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoGraphQlController {

    @Autowired
    private final CreatorRepository creatorRepository;
    @Autowired
    private final VideoRepository videoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
    }

    @QueryMapping
    public List<Video> videoList() {
        return videoRepository.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creatorRequest) {
        Creator creator = modelMapper.map(creatorRequest, Creator.class);
        return creatorRepository.save(creator);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequest videoRequest) {
        Video video1 = modelMapper.map(videoRequest, Video.class);
        Creator creator = creatorRepository.findById(video1.getCreator().getId())
                .orElseGet(() -> creatorRepository.save(video1.getCreator()));
        video1.setCreator(creator);
        return videoRepository.save(video1);
    }


}
