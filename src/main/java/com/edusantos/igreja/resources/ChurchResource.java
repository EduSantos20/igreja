package com.edusantos.igreja.resources;

import com.edusantos.igreja.entities.Church;
import com.edusantos.igreja.repository.ChurchRepository;
import com.edusantos.igreja.service.ChurchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/churches")
public class ChurchResource {

    private final ChurchService churchService;

    public ChurchResource(ChurchService churchService) {
        this.churchService = churchService;
    }

    @GetMapping
    public ResponseEntity<List<Church>> findAll(){
        List<Church> list = churchService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Church> findById(@PathVariable Long id){
        Church object = churchService.finById(id);
        return ResponseEntity.ok().body(object);
    }

    @PostMapping
    public ResponseEntity<Church> insert(@RequestBody Church object){
        object = churchService.insert(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(object.getId()).toUri();
        return ResponseEntity.ok().body(object);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Church> update(@PathVariable Long id, @RequestBody Church object){
        object = churchService.update(id, object);
        return ResponseEntity.ok().body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        churchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
