package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class VideoRepositoryImpl implements VideoRepository {

    private final Map<Long, Video> videos = new HashMap<>();
    private long lastId = 0L;

    @Override
    public Collection<Video> findAll() {
        return videos.values();
    }

    @Override
    public Video save(Video v) {
        v.setId(++lastId);
        videos.put(v.getId(), v);
        return v;
    }

    @Override
    public Video findById(long id) {
        return videos.get(id);
    }
}
