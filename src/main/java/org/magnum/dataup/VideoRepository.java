package org.magnum.dataup;

import org.magnum.dataup.model.Video;

import java.util.Collection;

public interface VideoRepository {

    Collection<Video> findAll();

    Video save(Video v);

    Video findById(long id);
}
