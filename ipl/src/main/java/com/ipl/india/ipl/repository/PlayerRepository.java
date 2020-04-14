package com.ipl.india.ipl.repository;

import com.ipl.india.ipl.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Team,String> {
}
