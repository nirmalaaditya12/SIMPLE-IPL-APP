package com.ipl.india.ipl.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberForm {
    private String teamId;
    private String playerName;
    private PlayerRole playerRole;
}
