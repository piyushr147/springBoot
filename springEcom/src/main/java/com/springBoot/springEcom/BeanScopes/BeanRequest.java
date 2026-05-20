package com.springBoot.springEcom.BeanScopes;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class BeanRequest {

    public BeanRequest(){
        System.out.println("BeanRequest constructor called");
    }

    @PostConstruct
    public void init(){
        System.out.println("BeanRequest post construct HASHCODE: "+ this.hashCode());
    }

    public void call(){
        System.out.println("BeanRequest HASHCODE: "+ this.hashCode());
    }
}
