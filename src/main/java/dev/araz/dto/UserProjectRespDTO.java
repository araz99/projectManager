package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProjectRespDTO {
    private Long id;
    private String username;
    private Date registrationDate;
}