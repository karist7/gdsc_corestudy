package com.example.gdsc.Service;

import com.example.gdsc.Entity.MemberEntity;
import com.example.gdsc.Repository.MemberRepository;
import com.example.gdsc.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
    }

    public MemberDto login(MemberDto memberDto) {

        Optional<MemberEntity> byMemberId =  memberRepository.findById(memberDto.getId());
        if(byMemberId.isPresent()){
            //회원 정보가 있음
            MemberEntity memberEntity = byMemberId.get();
            if(memberEntity.getPassword().equals(memberDto.getPassword())){
                //비밀번호 일치
                MemberDto check = MemberDto.toMemberDto(memberEntity);
                return check;
            }
            else{
                //비밀번호 불일치
                return null;
            }
        }
        else{
            //회원 정보가 없음
            return null;
        }
    }
    public List<MemberDto> findAll(){
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList=new ArrayList<>();
        for(MemberEntity memberEntity:memberEntityList){
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));
        }
        return memberDtoList;
    }

    public MemberDto findById(Long code) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(code);
        if (optionalMemberEntity.isPresent())
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        else
            return null;

    }

    public void deleteById(Long code){
        memberRepository.deleteById(code);
    }
    public void update(MemberDto memberDto){
        memberRepository.save(MemberEntity.toUpdateEntity(memberDto));
    }
    public MemberDto updateForm(Long code) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(code);
        if (optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
}
