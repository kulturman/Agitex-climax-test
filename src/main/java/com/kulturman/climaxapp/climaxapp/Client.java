package com.kulturman.climaxapp.climaxapp;

import java.util.Objects;

public record Client(String name, String forename, int age, String profession, double salary) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age && Double.compare(client.salary, salary) == 0 && Objects.equals(name, client.name) && Objects.equals(forename, client.forename) && Objects.equals(profession, client.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, forename, age, profession, salary);
    }
}
