package com.imalive.api.Service;

import com.imalive.api.DataTypes.MailType;
import com.imalive.api.DataTypes.StatusType;
import com.imalive.api.Model.Base;
import com.imalive.api.Repository.BaseRepository;
import com.imalive.api.Repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.imalive.api.Service.MailService.sendMail;

@Component
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;
    private final BaseRepository baseRepository;

    public List<Base> listAllRunning() {
        List<Base> bases = statusRepository.findAll();

        return bases.stream().filter(base -> base.getStatus().equals(StatusType.RUNNING)).collect(Collectors.toList());
    }

    public List<Base> listAllNotRunning() {
        List<Base> bases = statusRepository.findAll();

        return bases.stream()
                .filter(base -> base.getStatus().equals(StatusType.NOTRUNNING) || base.getStatus().equals(StatusType.DISABLED))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 60000)
    public void verifyStatus() {

        List<Base> bases = statusRepository.findAll();

        if (!bases.isEmpty()) {
            for (Base base : bases) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime interval = base.getUpDate().plusMinutes(5);

                if (base.getStatus().equals(StatusType.RUNNING)) {
                    if (now.isAfter(interval)) {
                        base.setStatus(StatusType.NOTRUNNING);
                        base.setDownDate(LocalDateTime.now());

                        if (notifyIfDown(base)) {
                            sendMail(base, MailType.DOWN);
                            base.setDownAdvertDate(LocalDateTime.now());
                            baseRepository.save(base);
                        }
                    }
                }
            }
        }
    }

    public Boolean notifyIfDown(Base base) {
        if (base.getDownAdvertDate() == null && base.getStatus().equals(StatusType.NOTRUNNING)) {
            return true;
        }
        return base.getDownDate().isAfter(base.getUpDate()) && base.getDownDate().isAfter(base.getDownAdvertDate());
    }
}
