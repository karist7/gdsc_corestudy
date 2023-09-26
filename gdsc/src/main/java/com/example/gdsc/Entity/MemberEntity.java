package com.example.gdsc.Entity;

import com.example.gdsc.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

@Table(name="member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String id;

    private String password;

    private String name;
    private String job;
    public MemberEntity() {

    }


    public static MemberEntity toMemberEntity(MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setCode(memberDto.getCode());
        memberEntity.setId(memberDto.getId());
        memberEntity.setPassword(memberDto.getPassword());
        memberEntity.setName(memberDto.getName());
        memberEntity.setJob(memberDto.getJob());

        return memberEntity;
    }
    public static MemberEntity toUpdateEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setCode(memberDto.getCode());
        memberEntity.setId(memberDto.getId());
        memberEntity.setPassword(memberDto.getPassword());
        memberEntity.setName(memberDto.getName());
        memberEntity.setJob(memberDto.getJob());
        return memberEntity;
    }


}
