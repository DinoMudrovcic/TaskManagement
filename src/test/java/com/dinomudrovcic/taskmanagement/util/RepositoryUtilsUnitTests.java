package com.dinomudrovcic.taskmanagement.util;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RepositoryUtilsUnitTests {

    @Mock
    private JpaRepository<Assignee, Long> repository;

    @BeforeEach
    public void init() {
        Assignee assignee = new Assignee();
        assignee.setId(1L);

        Mockito.when(repository.getById(assignee.getId())).thenReturn(assignee);
    }

    @Test
    public void testCheckIfEntityExists() {
        Assignee expectedAssignee = new Assignee();
        expectedAssignee.setId(1L);

        Assignee actualAssignee = repository.getById(expectedAssignee.getId());

        assertEquals(expectedAssignee, actualAssignee);
    }

}
