package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private int id;
    private String name;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setId(1);
        helloLombok.setName("lee");

        // String name = helloLombok.getName();
        // System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}
