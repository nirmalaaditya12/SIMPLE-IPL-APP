package com.ipl.india.ipl.model;

import com.ipl.india.ipl.form.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Team")
public class Team {
    @Id
    private String teamId;
    @NotNull(message = "Team name is mandatory")
    private String teamName;
    private Player players;
    private int teamSize=0;
}
