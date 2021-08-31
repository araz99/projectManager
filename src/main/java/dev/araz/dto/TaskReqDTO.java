package dev.araz.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskReqDTO {
    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String taskName;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String issueType;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String priority;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String description;
}