package com.imalive.api.Service;

import com.imalive.api.DataTypes.MailType;
import com.imalive.api.DataTypes.StatusType;
import com.imalive.api.Model.Base;
import com.imalive.api.Repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.imalive.api.Service.MailService.sendMail;

@Service
@RequiredArgsConstructor
public class BaseService { // RUN TESTS BEFORE EDITING

    private final BaseRepository baseRepository;

    public List<Base> listAll() {
        return baseRepository.findAll();
    }

    public Base findByBucketName(String bucketName) {
        return baseRepository.findByBucketName(bucketName);
    }

    public Base save(Base base) {
        base.setIncrementDate(LocalDateTime.now());
        base.setUpDate(base.getIncrementDate());
        base.setStatus(StatusType.RUNNING);

        Base savedBase = findByBucketName(base.getBucketName());

        if (savedBase == null) {
            sendMail(base, MailType.UP);
            base.setUpAdvertDate(LocalDateTime.now());

            return baseRepository.save(base);
        } else if (savedBase.getDownAdvertDate() == null && savedBase.getUpAdvertDate() != null) {
            return baseRepository.save(savedBase);
        } else if (savedBase.getDownAdvertDate().isAfter(savedBase.getUpAdvertDate())) {
            sendMail(savedBase, MailType.UP);
            savedBase.setUpAdvertDate(LocalDateTime.now());
        }

        return baseRepository.save(savedBase);
    }

    public void delete(String bucketName) {
        baseRepository.delete(findByBucketName(bucketName));
    }
}
