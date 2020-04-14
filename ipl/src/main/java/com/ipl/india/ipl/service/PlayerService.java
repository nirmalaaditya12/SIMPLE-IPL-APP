package com.ipl.india.ipl.service;

import com.ipl.india.ipl.config.ApplicationConstants;
import com.ipl.india.ipl.form.MemberForm;
import com.ipl.india.ipl.form.Player;
import com.ipl.india.ipl.form.ResponseForm;
import com.ipl.india.ipl.form.TeamForm;
import com.ipl.india.ipl.model.Team;
import com.ipl.india.ipl.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity addTeam(TeamForm form) {
        Team team = Team.builder().teamId(UUID.randomUUID().toString()).players(form.getPlayers()).teamName(form.getTeamName()).build();
        Team save = playerRepository.save(team);
        ResponseForm responseForm = ResponseForm.builder().code(HttpStatus.OK.value()).data(ApplicationConstants.USER_CREATED).build();
        return new ResponseEntity(responseForm, HttpStatus.OK);
    }

    public ResponseEntity addPlayer(MemberForm memberForm) {
        Optional<Team> team = playerRepository.findById(memberForm.getTeamId());
        if (team.isPresent() && team.get().getTeamSize() < ApplicationConstants.FIXED_TEAM_SIZE) {
            Player players = team.get().getPlayers();
            switch (memberForm.getPlayerRole().role) {
                case "ALL_ROUNDER":
                    String allrounder = players!=null ?players.getAllRounder():null;
                    if (null == allrounder) {
                        Query query = new Query(Criteria.where("teamId").is(memberForm.getTeamId()));
                        Update update = new Update().set("players.allRounder", memberForm.getPlayerName());
                        mongoTemplate.updateFirst(query, update, Team.class, "Team");
                        update.set("teamSize",team.get().getTeamSize()+1);
                        return new ResponseEntity("added successfully", HttpStatus.CREATED);
                    } else
                        return new ResponseEntity("Player cannot be added/team has ALL_ROUNDER", HttpStatus.BAD_REQUEST);
                case "WICKET_KEEPER":
                    String keeper = players!=null ?players.getWicketKeeper():null;
                    if (null == keeper) {
                        Query query = new Query(Criteria.where("teamId").is(memberForm.getTeamId()));
                        Update update = new Update().set("players.wicketKeeper", memberForm.getPlayerName());
                        update.set("teamSize",team.get().getTeamSize()+1);
                        mongoTemplate.updateFirst(query, update, Team.class, "Team");
                        return new ResponseEntity("added successfully", HttpStatus.CREATED);
                    } else
                        return new ResponseEntity("Player cannot be added/team has WICKET_KEEPER ", HttpStatus.BAD_REQUEST);
                case "BATSMAN":
                    HashSet<String> batsman = players!=null ?players.getBatsman():new HashSet();
                    if (( batsman.isEmpty() || batsman.size() < ApplicationConstants.FIXED_BATSMAN_SIZE+1 ) && !batsman.contains(memberForm.getPlayerName()) ) {
                        Query query = new Query(Criteria.where("teamId").is(memberForm.getTeamId()));
                        batsman.add(memberForm.getPlayerName());
                        Update update = new Update().set("players.batsman", batsman);
                        update.set("teamSize",team.get().getTeamSize()+1);
                        mongoTemplate.updateFirst(query, update, Team.class, "Team");
                        return new ResponseEntity("added successfully", HttpStatus.CREATED);
                    } else
                        return new ResponseEntity("Player cannot be added/team has enough BATSMAN /player name is already present ", HttpStatus.BAD_REQUEST);
                case "BOWLER":
                    HashSet<String> bowlers = players!=null ?players.getBowlers():new HashSet();
                    if ( (bowlers.isEmpty() || bowlers.size() < ApplicationConstants.FIXED_BOWLERS_SIZE+1) && !bowlers.contains(memberForm.getPlayerName()) ) {
                        Query query = new Query(Criteria.where("teamId").is(memberForm.getTeamId()));
                        bowlers.add(memberForm.getPlayerName());
                        Update update = new Update().set("players.bowlers", bowlers);
                        update.set("teamSize",team.get().getTeamSize()+1);
                        mongoTemplate.updateFirst(query, update, Team.class, "Team");
                        return new ResponseEntity("added successfully", HttpStatus.CREATED);
                    } else
                        return new ResponseEntity("Player cannot be added/team has enough BOWLERS/player name is already present ", HttpStatus.BAD_REQUEST);
                default:
                    break;
            }
        }
        return new ResponseEntity("No Content ", HttpStatus.NO_CONTENT);
    }

    public List<Team> getAllTeams() {
        return playerRepository.findAll();
    }

    public void deleteTeam(String teamId) {
        playerRepository.deleteById(teamId);
    }
}
