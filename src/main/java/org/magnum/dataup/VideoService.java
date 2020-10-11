package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;

import java.io.InputStream;
import java.util.Collection;

public interface VideoService {

    Video addVideo(Video v);

    Collection<Video> getVideoList();

    VideoStatus setVideoData(long id, InputStream videoData);

    byte[] getVideoData(long id);
}
