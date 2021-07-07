package com.imalive.api.Repository;

import com.imalive.api.Model.Base;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository extends JpaRepository<Base, String> {
    Base findByBucketName(String bucketName);
}
