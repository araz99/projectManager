package dev.araz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{project-key}/tasks")
@RequiredArgsConstructor
public class TaskController {
}