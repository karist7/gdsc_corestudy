package com.example.gdsc.dto;

import com.example.gdsc.Entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private Long code;
    private String id;
    private String password;
    private String name;
    private String job;

    public static MemberDto toMemberDto(MemberEntity memberEntity){
        MemberDto memberDto = new MemberDto();
        memberDto.setCode(memberEntity.getCode());
        memberDto.setId(memberEntity.getId());
        memberDto.setPassword(memberEntity.getPassword());
        memberDto.setName(memberEntity.getName());
        memberDto.setJob(memberEntity.getJob());
        return memberDto;
    }
}
