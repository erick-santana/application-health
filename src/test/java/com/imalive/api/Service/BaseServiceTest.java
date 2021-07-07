package com.imalive.api.Service;

import com.imalive.api.DataTypes.StatusType;
import com.imalive.api.Model.Base;
import com.imalive.api.Repository.BaseRepository;
import com.imalive.api.Util.BaseCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class BaseServiceTest {

    @InjectMocks
    private BaseService baseService;

    @Mock
    private BaseRepository baseRepositoryMock;

    @Test
    @DisplayName("ListAll should return an list of bases when success")
    void listAll_SuccessCase() {
        BDDMockito.when(baseRepositoryMock.findAll())
                .thenReturn(Collections.singletonList(BaseCreator.createValidBaseWithUpDateBiggerWithoutDownDate()));

        String expectedBucketName = BaseCreator.createValidBaseWithUpDateBiggerWithoutDownDate().getBucketName();
        List<Base> bases = baseService.listAll();

        Assertions.assertThat(bases).hasSize(1);
        Assertions.assertThat(bases.get(0).getBucketName()).isEqualTo(expectedBucketName);
    }

    @Test
    @DisplayName("FindByBucketName should return an base when success")
    void findByBucketName_SuccessCase() {
        BDDMockito.when(baseRepositoryMock.findByBucketName(ArgumentMatchers.anyString()))
                .thenReturn(BaseCreator.createValidBaseWithUpDateBiggerWithoutDownDate());

        String expectedBucketName = BaseCreator.createValidBaseWithUpDateBiggerWithoutDownDate().getBucketName();
        Base base = baseService.findByBucketName("name");

        Assertions.assertThat(base).isNotNull();
        Assertions.assertThat(base.getBucketName()).isEqualTo(expectedBucketName);
    }

    @Test
    @DisplayName("Delete should return void when success")
    void delete_SuccessCase() {
        BDDMockito.doNothing().when(baseRepositoryMock).delete(ArgumentMatchers.any(Base.class));

        Assertions.assertThatCode(() -> baseService.delete("name")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Save should return an base when success")
    void save_SuccessCase_WithUpAdvertDateBigger() {
        BDDMockito.when(baseRepositoryMock.findByBucketName(BaseCreator.createValidBaseWithUpAdvertDateBigger().getBucketName()))
                .thenReturn(BaseCreator.createValidBaseWithUpAdvertDateBigger());
        BDDMockito.when(baseRepositoryMock.save(ArgumentMatchers.any(Base.class)))
                .thenReturn(BaseCreator.createValidBaseWithUpAdvertDateBigger());

        String expectedBucketName = BaseCreator.createValidBaseWithUpAdvertDateBigger().getBucketName();
        Base savedBase = baseService.findByBucketName(expectedBucketName);
        Base base = baseService.save(savedBase);

        Assertions.assertThat(base).isNotNull();
        Assertions.assertThat(base.getBucketName()).isEqualTo(expectedBucketName);
        Assertions.assertThat(base.getStatus()).isEqualTo(StatusType.RUNNING);
    }

    @Test
    @DisplayName("Save should return an base when success")
    void save_SuccessCase_WithUpDateBigger() {
        BDDMockito.when(baseRepositoryMock.findByBucketName(BaseCreator.createValidBaseWithUpDateBigger().getBucketName()))
                .thenReturn(BaseCreator.createValidBaseWithUpDateBigger());
        BDDMockito.when(baseRepositoryMock.save(ArgumentMatchers.any(Base.class)))
                .thenReturn(BaseCreator.createValidBaseWithUpAdvertDateBigger());

        String expectedBucketName = BaseCreator.createValidBaseWithUpDateBigger().getBucketName();
        Base savedBase = baseService.findByBucketName(expectedBucketName);
        Base base = baseService.save(savedBase);

        Assertions.assertThat(base).isNotNull();
        Assertions.assertThat(base.getBucketName()).isEqualTo(expectedBucketName);
        Assertions.assertThat(base.getStatus()).isEqualTo(StatusType.RUNNING);
    }

    @Test
    @DisplayName("Save should return an base when success")
    void save_SuccessCase_WithUpAdvertDateBiggerWithoutDownDate() {
        BDDMockito.when(baseRepositoryMock.findByBucketName(BaseCreator.createValidBaseWithUpAdvertDateBiggerWithoutDownDate().getBucketName()))
                .thenReturn(BaseCreator.createValidBaseWithUpAdvertDateBiggerWithoutDownDate());
        BDDMockito.when(baseRepositoryMock.save(ArgumentMatchers.any(Base.class)))
                .thenReturn(BaseCreator.createValidBaseWithUpAdvertDateBiggerWithoutDownDate());

        String expectedBucketName = BaseCreator.createValidBaseWithUpAdvertDateBiggerWithoutDownDate().getBucketName();
        Base savedBase = baseService.findByBucketName(expectedBucketName);
        Base base = baseService.save(savedBase);

        Assertions.assertThat(base).isNotNull();
        Assertions.assertThat(base.getBucketName()).isEqualTo(expectedBucketName);
        Assertions.assertThat(base.getStatus()).isEqualTo(StatusType.RUNNING);
    }

    @Test
    @DisplayName("Save should return an base when success")
    void save_SuccessCase_WithUpDateBiggerWithoutDownDate() {
        BDDMockito.when(baseRepositoryMock.findByBucketName(BaseCreator.createValidBaseWithUpDateBiggerWithoutDownDate().getBucketName()))
                .thenReturn(null);
        BDDMockito.when(baseRepositoryMock.save(ArgumentMatchers.any(Base.class)))
                .thenReturn(BaseCreator.createValidBaseWithUpAdvertDateBiggerWithoutDownDate());

        String expectedBucketName = BaseCreator.createValidBaseWithUpDateBiggerWithoutDownDate().getBucketName();
        Base savedBase = baseService.findByBucketName(expectedBucketName);
        Base base = baseService.save(BaseCreator.createValidBaseWithUpAdvertDateBiggerWithoutDownDate());

        Assertions.assertThat(savedBase).isNull();
        Assertions.assertThat(base).isNotNull();
        Assertions.assertThat(base.getBucketName()).isEqualTo(expectedBucketName);
        Assertions.assertThat(base.getStatus()).isEqualTo(StatusType.RUNNING);
    }
}