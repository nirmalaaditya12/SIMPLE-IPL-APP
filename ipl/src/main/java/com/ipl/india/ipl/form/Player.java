package com.ipl.india.ipl.form;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.HashSet;

@Data
public class Player {
    private HashSet<String> batsman;
    private HashSet<String> bowlers;
    private String wicketKeeper;
    private String allRounder;
}