package ru.petrov.testt1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> countChars(@RequestBody Map<String, Object> payload) {
        if (!payload.containsKey("str")) {
            return ResponseEntity.badRequest()
                    .body(new HashMap<>(){{put("error", "Str parameter not found");}});
        }
        Map<Character, Integer> result = new HashMap<>();

        for (char c : payload.get("str").toString().toCharArray()) {
            result.putIfAbsent(c, 0);
            result.put(c, result.get(c) + 1);
        }

        Map<Character, Integer> answer = result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new)
                );

        return ResponseEntity.ok(new HashMap<>(){{put("answer", answer);}});
    }
}
