package dev.araz.service;

import dev.araz.entity.IssueType;
import dev.araz.exception.NotIssueTypeException;
import dev.araz.repository.IssueTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueTypeService {

    private final IssueTypeRepository typeRepository;

    public IssueType getIssueTypeByName(String name) {
        Optional<IssueType> issueType = typeRepository.findByIssueTypeName(name);
        if (issueType.isPresent())
            return issueType.get();
        throw new NotIssueTypeException("Issue type " + name + "not exists!");
    }
}