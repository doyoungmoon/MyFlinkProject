package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zy
 * @version 1.0
 * @description:
 * @date 2021/8/5 15:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitInsured implements Serializable {
    public String list_id;
    public String item_id;


}
