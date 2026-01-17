package com.edusantos.igreja.resources;

import com.edusantos.igreja.entities.Pray;
import com.edusantos.igreja.service.PrayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/prayers")
public class PrayResource {

    private final PrayService prayService;

    public PrayResource(PrayService prayService) {
        this.prayService = prayService;
    }

    @GetMapping
    public ResponseEntity<List<Pray>> findAll(){
        List<Pray> list = prayService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pray> findById(@PathVariable Long id){
        Pray object = prayService.findById(id);
        return ResponseEntity.ok().body(object);
    }

    @PostMapping
    public ResponseEntity<Pray> insert(@PathVariable Pray object){
        object = prayService.insert(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(object.getId()).toUri();

        return ResponseEntity.created(uri).body(object);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pray> update(@PathVariable Long id, @RequestBody Pray object){
        object = prayService.update(id,object);
        return ResponseEntity.ok().body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        prayService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
