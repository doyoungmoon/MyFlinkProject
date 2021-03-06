package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/12 13:53
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ldcode {
    public String codetype;
    public String code;
    public String codename;
    public String codealias;
    public String comcode;
    public String othersign;

}
