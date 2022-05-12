package evening.bread.excel.model.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private int memberUid = 0;
    private String name;
    private String email;
}
