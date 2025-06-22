package com.example.player_info_microservice.controller;


import com.example.player_info_microservice.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    private static final List<Player> players= Arrays.asList(
            new Player(1,"p1","Nitin Varshney", BigDecimal.valueOf(10000)),
            new Player(2,"p1","Megha Varshney", BigDecimal.valueOf(13000)),
            new Player(3,"p1","Vansh Varshney", BigDecimal.valueOf(15000)),
            new Player(4, "p1","Ayush Varshney", BigDecimal.valueOf(20000)),
            new Player(5,"p2","Somya Varshney", BigDecimal.valueOf(13000)),
            new Player(6,"p2","Shaurya Varshney", BigDecimal.valueOf(12000))
    );


    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        log.info("method {} invoked ", "getAllPlayers");
        return ResponseEntity.ok(players);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable("id") int id ){
        log.info("method {} invoked  with player id {}", "getPlayerById",id);
       List<Player> playerList=players.stream().filter(player -> player.getId()==id).toList();
        log.info("playerList {}", playerList);
       if(!playerList.isEmpty()){
           return ResponseEntity.ok(playerList.get(0));
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No player found");
       }
    }

    @GetMapping(value = "/partner/{partnerId}")
    public ResponseEntity<List<Player>> getPlayerByPartner(@PathVariable("partnerId") String partnerId ){
        log.info("method {} invoked  with partner id {}", "getPlayerByPartner",partnerId);
        List<Player> playerList=players.stream().filter(player -> player.getPartnerId().equalsIgnoreCase(partnerId)).toList();
        if(!playerList.isEmpty()){
            return ResponseEntity.ok(playerList);
        }else{
            return ResponseEntity.ok(List.of());
        }
    }

}
