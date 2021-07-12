package com.backpac.mapper;

import com.backpac.domain.Member;
import com.backpac.dto.MemberForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member formToEntity(MemberForm memberForm);
    MemberForm entityToForm(Member member);
}
