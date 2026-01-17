package com.edusantos.igreja.resources;

import com.edusantos.igreja.entities.Member;
import com.edusantos.igreja.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/members")
public class MemberResource {

    private final MemberService memberService;

    public MemberResource(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> findAll(){
        List<Member> list = memberService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Member> findById(@PathVariable Long id){
        Member object = memberService.findById(id);
        return ResponseEntity.ok().body(object);
    }

    @PostMapping(value = "/churches/{churchid}")
    public ResponseEntity<Member> insert(@PathVariable Long churchId, @RequestBody Member object){
        object = memberService.createMember(churchId, object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).body(object);

    }

    @PutMapping(value = "/{memberId}/churches/{churchId}")
    public ResponseEntity<Member> update(@PathVariable Long memberId, @PathVariable Long churchId, @RequestBody Member object){
        object = memberService.updateMember(memberId,churchId,object);
        return ResponseEntity.ok().body(object);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
