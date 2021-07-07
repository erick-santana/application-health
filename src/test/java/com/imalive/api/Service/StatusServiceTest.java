package com.imalive.api.Service;

import com.imalive.api.DataTypes.StatusType;
import com.imalive.api.Model.Base;
import com.imalive.api.Repository.StatusRepository;
import com.imalive.api.Util.BaseCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class StatusServiceTest {

    @InjectMocks
    private StatusService statusService;

    @Mock
    private StatusRepository statusRepositoryMock;

    @BeforeEach
    void setup() {
        BDDMockito.when(statusRepositoryMock.findAll())
                .thenReturn(Collections.singletonList(BaseCreator.createValidBaseWithDownAdvertDateBigger()));
    }

    @Test
    @DisplayName("ListAllRunning should return an list of running bases when success")
    void listAllRunning_SuccessCase() {
        BDDMockito.when(statusRepositoryMock.findAll())
                .thenReturn(Collections.singletonList(BaseCreator.createValidBaseWithUpAdvertDateBigger()));

        String expectedBucketName = BaseCreator.createValidBaseWithUpDateBiggerWithoutDownDate().getBucketName();
        List<Base> bases = statusService.listAllRunning();

        Assertions.assertThat(bases).hasSize(1);
        Assertions.assertThat(bases.get(0).getBucketName()).isEqualTo(expectedBucketName);
        Assertions.assertThat(bases.get(0).getStatus()).isEqualTo(StatusType.RUNNING);
    }

    @Test
    @DisplayName("ListAllNotRunning should return an list of not running bases when success")
    void listAllNotRunning_SuccessCase() {
        BDDMockito.when(statusRepositoryMock.findAll())
                .thenReturn(Collections.singletonList(BaseCreator.createValidBaseWithDownAdvertDateBigger()));

        String expectedBucketName = BaseCreator.createValidBaseWithDownAdvertDateBigger().getBucketName();
        List<Base> bases = statusService.listAllNotRunning();

        Assertions.assertThat(bases).hasSize(1);
        Assertions.assertThat(bases.get(0).getBucketName()).isEqualTo(expectedBucketName);
        Assertions.assertThat(bases.get(0).getStatus()).isEqualTo(StatusType.NOTRUNNING);
    }

    @Test
    @DisplayName("notifyIfDown should return true")
    void notifyIfDown_WithDownDateIsBigger() {
        Boolean notify = statusService.notifyIfDown(BaseCreator.createValidBaseWithDownDateBigger());

        Assertions.assertThat(notify).isTrue();
    }

    @Test
    @DisplayName("notifyIfDown should return false")
    void notifyIfDown_WithDownAdvertDateIsBigger() {
        Boolean notify = statusService.notifyIfDown(BaseCreator.createValidBaseWithDownAdvertDateBigger());

        Assertions.assertThat(notify).isFalse();
    }

    @Test
    @DisplayName("notifyIfDown should return false")
    void notifyIfDown_WithUpDateIsBigger() {
        Boolean notify = statusService.notifyIfDown(BaseCreator.createValidBaseWithUpDateBigger());

        Assertions.assertThat(notify).isFalse();
    }

    @Test
    @DisplayName("notifyIfDown should return false")
    void notifyIfDown_WithUpAdvertDateIsBigger() {
        Boolean notify = statusService.notifyIfDown(BaseCreator.createValidBaseWithUpAdvertDateBigger());

        Assertions.assertThat(notify).isFalse();
    }
}