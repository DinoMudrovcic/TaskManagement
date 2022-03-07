package com.dinomudrovcic.taskmanagement.repository.assignee;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
}
