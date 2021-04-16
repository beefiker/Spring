package hello.core.singleton;

public class StatefulService {

    // private int price; // 상태를 유지하는 필드

    // stateless 설계
    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        // this.price = price; //요기가 문제가 된다. (stateful 설계에서)
        return price;
    }

    // stateful 설계(bad)
    // public int getPrice() {
    //     return price;
    // }

}
