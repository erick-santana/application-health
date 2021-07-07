package com.imalive.api.Model;

import com.imalive.api.DataTypes.StatusType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Base {
    @Id
    @NotEmpty
    private String bucketName;
    @NotEmpty
    private LocalDateTime incrementDate;
    private LocalDateTime downDate;
    private LocalDateTime downAdvertDate;
    @NotEmpty
    private LocalDateTime upDate;
    private LocalDateTime upAdvertDate;
    @NotEmpty
    private StatusType status;
}
