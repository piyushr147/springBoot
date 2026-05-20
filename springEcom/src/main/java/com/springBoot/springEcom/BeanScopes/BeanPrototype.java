package com.springBoot.springEcom.BeanScopes;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BeanPrototype {
    public BeanPrototype() {
        System.out.println("BeanPrototype constructor called");
    }

    @PostConstruct
    public void init() {
        System.out.println("BeanPrototype post construct HASHCODE: "+ this.hashCode());
    }

    public void call() {
        System.out.println("BeanPrototype HASHCODE: "+ this.hashCode());
    }
}
