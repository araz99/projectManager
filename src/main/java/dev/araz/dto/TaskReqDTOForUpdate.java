package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskReqDTOForUpdate {
    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String taskName;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String executor;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String issueType;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String priority;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String status;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String description;
}