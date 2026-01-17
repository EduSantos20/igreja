package com.edusantos.igreja.resources;

import com.edusantos.igreja.entities.Occasion;
import com.edusantos.igreja.service.OccasionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/occasions")
public class OccasionResource {

    private final OccasionService occasionService;

    public OccasionResource(OccasionService occasionService) {
        this.occasionService = occasionService;
    }

    @GetMapping
    public ResponseEntity<List<Occasion>> findAll(){
        List<Occasion> list = occasionService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Occasion> findById(@PathVariable Long id){
        Occasion object = occasionService.findById(id);
        return ResponseEntity.ok().body(object);
    }

    @PostMapping
    public ResponseEntity<Occasion> insert(@RequestBody Occasion object){
        object = occasionService.insert(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(object.getId()).toUri();

        return ResponseEntity.created(uri).body(object);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Occasion> update(@PathVariable Long id, @RequestBody Occasion object){
        object = occasionService.update(id,object);
        return ResponseEntity.ok().body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        occasionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
