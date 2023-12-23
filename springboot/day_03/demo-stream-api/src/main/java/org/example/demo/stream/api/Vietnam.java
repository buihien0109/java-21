package org.example.demo.stream.api;

public class Vietnam implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Xin chào " + name);
    }
}
