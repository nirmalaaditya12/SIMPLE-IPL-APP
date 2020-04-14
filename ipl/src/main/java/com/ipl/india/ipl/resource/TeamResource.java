package com.ipl.india.ipl.resource;

import com.ipl.india.ipl.form.MemberForm;
import com.ipl.india.ipl.form.TeamForm;
import com.ipl.india.ipl.model.Team;
import com.ipl.india.ipl.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/ipl")
public class TeamResource {
    @Autowired
    private PlayerService playerService;
    @NotNull(message = "Team name is mandatory")
    private String teamName;
    
    @ApiOperation(value = "used to add team", notes = "use this model as example request body: {\n" +
            "  \"players\": {\n" +
            "    \"allRounder\": \"ADITYA\",\n" +
            "    \"batsman\": [\"KUMAR\"]\n" +
            "    ,\n" +
            "    \"bowlers\": [\n" +
            "      \"MAHESH\"\n" +
            "    ],\n" +
            "    \"wicketKeeper\": \"SAM\"\n" +
            "  },\n" +
            "  \"teamName\": \"MI\"\n" +
            "}")
    @PostMapping
    public ResponseEntity addTeam(@RequestBody TeamForm form){
        return playerService.addTeam(form);
    }

    @PutMapping("/add")
    public ResponseEntity addTeamMemberName(@RequestBody MemberForm memberForm){
        return playerService.addPlayer(memberForm);
    }

    @GetMapping
    public List<Team> getAllTeams(){
        return playerService.getAllTeams();
    }
    @DeleteMapping
    public void deleteTeam(@RequestParam("teamId")String teamId){
         playerService.deleteTeam(teamId);
    }

}
