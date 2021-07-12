package com.backpac.mapper;

import com.backpac.domain.Gender;
import com.backpac.domain.Member;
import com.backpac.domain.Order;
import com.backpac.dto.MemberForm;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-12T23:26:16+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9 (AdoptOpenJDK)"
)
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member formToEntity(MemberForm memberForm) {
        if ( memberForm == null ) {
            return null;
        }

        String name = null;
        String nickname = null;
        String password = null;
        int phoneNumber = 0;
        String email = null;
        Gender gender = null;

        name = memberForm.getName();
        nickname = memberForm.getNickname();
        password = memberForm.getPassword();
        phoneNumber = memberForm.getPhoneNumber();
        email = memberForm.getEmail();
        gender = memberForm.getGender();

        Long id = null;
        List<Order> orders = null;

        Member member = new Member( id, name, nickname, password, phoneNumber, email, gender, orders );

        return member;
    }

    @Override
    public MemberForm entityToForm(Member member) {
        if ( member == null ) {
            return null;
        }

        String name = null;
        String nickname = null;
        int phoneNumber = 0;
        String password = null;
        String email = null;
        Gender gender = null;

        name = member.getName();
        nickname = member.getNickname();
        phoneNumber = member.getPhoneNumber();
        password = member.getPassword();
        email = member.getEmail();
        gender = member.getGender();

        MemberForm memberForm = new MemberForm( name, nickname, phoneNumber, password, email, gender );

        return memberForm;
    }
}
