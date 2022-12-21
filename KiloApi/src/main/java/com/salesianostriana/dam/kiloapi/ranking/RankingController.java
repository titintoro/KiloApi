package com.salesianostriana.dam.kiloapi.ranking;

import com.salesianostriana.dam.kiloapi.clase.ClaseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final ClaseService service;

@GetMapping("/")
public ResponseEntity<List<Ranking>> createRanking(){

    List<Ranking> results = service.getRanking();

    if (results.isEmpty())
        return ResponseEntity.notFound().build();

    return ResponseEntity.ok(results);
}



}
