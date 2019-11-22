package com.jd.st.JavaHuashanBan;

import java.io.Serializable;

/**
 * @author ZhouZhiping
 * @date 2019/6/28 9:11
 * @Email zhouzhiping(a)jd.com
 */
public class SerialTest implements Serializable {
    /**
     * 13. 【强制】序列化类新增属性时，请不要修改serialVersionUID字段，避免反序列失败；
     * 如果完全不兼容升级，避免反序列化混乱，那么请修改serialVersionUID值。
     * 说明：注意serialVersionUID不一致会抛出序列化运行时异常。
     * https://github.com/giantray/stackoverflow-java-top-qa/blob/master/contents/what-is-a-serialversionuid-and-why-should-i-use-it.md
     */

    /**
     * 23.
     * 【推荐】慎用Object的clone方法来拷贝对象。 说明：对象clone方法默认是浅拷贝，若想实现深拷贝需覆写clone方法实现域对象的深度遍历式拷贝。
     *
     */


}
