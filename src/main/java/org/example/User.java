package org.example;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private final int id;
    private String firstName, lastName;
    private int age;
    private String country;

    public boolean isOlder(int age) {
        return this.age > age;
    }
}
