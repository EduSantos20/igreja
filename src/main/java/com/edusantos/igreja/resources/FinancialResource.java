package com.edusantos.igreja.resources;

import com.edusantos.igreja.entities.Financial;
import com.edusantos.igreja.service.FinancialService;
import org.apache.catalina.valves.rewrite.Substitution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/financial")
public class FinancialResource {

    private final FinancialService financialService;

    public FinancialResource(FinancialService financialService) {
        this.financialService = financialService;
    }

    @GetMapping
    public ResponseEntity<List<Financial>> findAll(){
        List<Financial> list = financialService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Financial> findById(@PathVariable Long id){
        Financial object = financialService.findById(id);
        return ResponseEntity.ok().body(object);
    }

    @PostMapping
    public ResponseEntity<Financial> insert(@RequestBody Financial object){
        object =financialService.insert(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).body(object);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Financial> update(@PathVariable Long id, @RequestBody Financial object){
        object = financialService.update(id,object);
        return ResponseEntity.ok().body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        financialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
