package com.example.springtest_week1.service;

import com.example.springtest_week1.domain.Member;
import com.example.springtest_week1.dto.MemberInfoResponseDto;
import com.example.springtest_week1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberInfoResponseDto findMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);

        MemberInfoResponseDto memberInfoResponseDto = new MemberInfoResponseDto();

        memberInfoResponseDto.setId(member.get().getId());
        memberInfoResponseDto.setName(member.get().getName());
        memberInfoResponseDto.setEmail(member.get().getEmail());
        memberInfoResponseDto.setPw(member.get().getPassword());

        return memberInfoResponseDto;
    }

    @Transactional
    public List<MemberInfoResponseDto> findAllMember() {
        List<Member> members = memberRepository.findAll();
        List<MemberInfoResponseDto> memberInfoResponseDtoList = new ArrayList<>();

        for (Member m : members) {
            MemberInfoResponseDto memberInfoResponseDto = new MemberInfoResponseDto(m);
            memberInfoResponseDtoList.add(memberInfoResponseDto);
        }

        return memberInfoResponseDtoList;
    }
}
