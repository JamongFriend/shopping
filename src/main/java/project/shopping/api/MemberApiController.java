package project.shopping.api;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.shopping.domain.Member;
import project.shopping.service.MemberService;

// API를 설계 할때는 Entity를 파라미터로 받으면 안되고, 외부에 노출하면 안된다
//

@RestController // = @Controller + @ResponseBody
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member= new Member();
        member.setName(request.getName());

        Long id  = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }
    
    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
