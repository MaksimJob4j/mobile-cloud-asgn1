package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping(VideoSvcApi.VIDEO_SVC_PATH)
    public @ResponseBody Collection<Video> getVideoList() {
        return videoService.getVideoList();
    }

    @PostMapping(VideoSvcApi.VIDEO_SVC_PATH)
    public @ResponseBody Video addVideo(@RequestBody Video v) {
        return videoService.addVideo(v);
    }

    @PostMapping(VideoSvcApi.VIDEO_DATA_PATH)
    public @ResponseBody ResponseEntity<VideoStatus> setVideoData(
            @PathVariable("id") long id, @RequestPart("data") MultipartFile videoData) throws IOException {
        return ResponseEntity.of(Optional.ofNullable(videoService.setVideoData(id, videoData.getInputStream())));
    }

    @GetMapping(VideoSvcApi.VIDEO_DATA_PATH)
    public @ResponseBody ResponseEntity<byte[]> getData(@PathVariable("id") long id) {
        return ResponseEntity.of(Optional.ofNullable(videoService.getVideoData(id)));
    }
}
