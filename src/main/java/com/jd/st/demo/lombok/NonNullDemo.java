package com.jd.st.demo.lombok;

import lombok.NonNull;

/**
 * @author ZhouZhiping
 * @date 2019/12/17 10:43
 * @Email zhouzhiping(a)jd.com
 */
public class NonNullDemo {

    public static void main(String[] args) {
        Head head = new Head();
        head.setFace(1);
        Body body = new Body();
        body.setHead(null);
        System.out.println(body);

    }


}


class Head {
    int face;

    void setFace(int i) {
        this.face = i;
    }
}

class Body {
    Head head;

    void setHead(@NonNull Head head1) {
        this.head = head1;

    }
}


