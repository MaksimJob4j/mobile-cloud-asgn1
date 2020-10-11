package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository repository;

    @Override
    public Collection<Video> getVideoList() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public VideoStatus setVideoData(long id, InputStream videoData) {
        Video video = repository.findById(id);
        if (video != null)
            try {
                VideoFileManager.get().saveVideoData(video, videoData);
                return new VideoStatus(VideoStatus.VideoState.READY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;


    }

    @Override
    public byte[] getVideoData(long id) {
        Video video = repository.findById(id);
        if (video != null)
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                VideoFileManager.get().copyVideoData(video, outputStream);
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public Video addVideo(Video v) {
        v.setDataUrl("");
        repository.save(v);
        return v;
    }
}
