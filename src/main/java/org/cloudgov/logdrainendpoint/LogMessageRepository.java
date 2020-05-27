package org.cloudgov.logdrainendpoint;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "logs")
public interface LogMessageRepository extends CrudRepository<LogMessage, Long> {}