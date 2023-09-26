package com.example.gdsc.Repository;

import com.example.gdsc.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    //id 정보 조회
    Optional<MemberEntity> findById(String memberId);
}
