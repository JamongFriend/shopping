package project.shopping.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.shopping.domain.Member;
import project.shopping.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

// API를 설계 할때는 Entity를 파라미터로 받으면 안되고, 외부에 노출하면 안된다

@RestController // = @Controller + @ResponseBody
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("api/members/save")
    public CreateMemberResponse saveMemberApi(@RequestBody @Valid CreateMemberRequest request) {
        Member member= new Member();
        member.setName(request.getName());

        Long id  = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("api/members/{id}")
    public UpdateMemberResponse updateMemberApi(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id, request.getName());
        Member findMember = memberService.findOneMember(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @GetMapping("api/members")
    public Result membersApi() {
        List<MemberDto> collect = memberService.findMembers().stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse{
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }
}
