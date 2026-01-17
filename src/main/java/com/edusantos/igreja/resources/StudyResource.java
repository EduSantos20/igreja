package com.edusantos.igreja.resources;

import com.edusantos.igreja.entities.Study;
import com.edusantos.igreja.service.StudyService;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/studies")
public class StudyResource {

    private final StudyService studyService;

    public StudyResource(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping
    public ResponseEntity<List<Study>> findAll(){
        List<Study> list = studyService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Study> findById(@PathVariable Long id){
        Study object = studyService.findById(id);
        return ResponseEntity.ok().body(object);
    }

    @PostMapping
    public ResponseEntity<Study> insert(@RequestBody Study object){
        object = studyService.insert(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(object.getId()).toUri();
        return ResponseEntity.ok().body(object);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Study> update(@PathVariable Long id, @RequestBody Study object){
        object = studyService.update(id,object);
        return ResponseEntity.ok().body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        studyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
