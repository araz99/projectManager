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
public class ProjectReqDTO {
    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String projectName;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String key;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String projectType;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String lead;

    @NotBlank(message = "{message.for.notBlank}")
    @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
    private String description;
}