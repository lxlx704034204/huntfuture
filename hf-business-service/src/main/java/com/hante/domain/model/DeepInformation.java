package com.hante.domain.model;

import com.hante.common.domain.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Map;

/**
 * 岗位深度信息实体
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
//这里在数据库生成唯一索引的时候会报错，无视掉就行，可以正常使用，而且下次运行索引已存在的情况下就不会报错了
//注意，唯一索引的建立单纯是为了提高索引性能和当做数据库最后的一道屏障，插入或更新数据时仍应该先查再插
//这是因为：1.异常的处理很损耗性能；2：jpa在唯一索引违反的情况下抛出的是RunTimeException，不好处理
@Table(name = "t_deep_information", indexes = {
        @Index(name = "position", columnList = "position_id", unique = true)
})
public class DeepInformation extends BaseModel {
    /**
     * 岗位id
     */
    @Column(name = "position_id", columnDefinition = "char(32)", nullable = false)
    private String positionId;

    /**
     * 深度信息，将map转成json字符串存储进来
     */
    @Column(name = "deep_info", columnDefinition = "text", nullable = false)
    private String deepInfo;

    /**
     * 创建岗位的深度信息
     * @param positionId
     * @param deepInfo
     */
    public DeepInformation(String positionId, Map<String, String> deepInfo) {
        this.positionId = positionId;
        // 将map转换成json字符串存储起来
        JSONObject jsonObject = new JSONObject(deepInfo);
        this.deepInfo = jsonObject.toString();
    }

}

   