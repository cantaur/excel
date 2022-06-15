package evening.bread.excel.model.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberDTO {
    private int memberUid = 0;
    @NotNull
    private String name;
    @NotNull
    private String email;
}
