package com.dinomudrovcic.taskmanagement.util;

import org.springframework.data.jpa.repository.JpaRepository;

import static java.util.Objects.nonNull;

public class RepositoryUtils {

    public static boolean checkIfEntityExists(final JpaRepository<?, Long> repository, final Long id) {
        return nonNull(repository.getById(id));
    }

}
