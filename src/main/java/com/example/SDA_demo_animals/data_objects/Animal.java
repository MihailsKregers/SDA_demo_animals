package com.example.SDA_demo_animals.data_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name should be not blank!")
    @Pattern(regexp = "[A-Za-z\\-\\']+", message = "Name should consist only of alphabet characters, - or '!")
    @Pattern(regexp = "[A-Za-z].*", message = "Name should start with alphabet character!")
    @Pattern(regexp = ".*[A-Za-z]", message = "Name should end with alphabet character!")
    private String name;

    @NotBlank(message = "Color should be not blank!")
    @Pattern(regexp = "[A-Za-z]+", message = "Color should cpnsist only of alphabet characters!")
    private String color;

    @NotNull(message = "Age is mandatory!")
    @Range(min = 0, max = 100, message = "Age should be between 0 and 100!")
    private Integer age;

    @NotNull(message = "Gender is mandatory!")
    @Pattern(regexp = "[MFN]", message = "Gender should be value M/F/N!")
    private String gender;

    @NotBlank(message = "Breed should be not blank!")
    @Pattern(regexp = "[A-Za-z\\-\\s]+", message = "Breed should consist only of alphabet characters, - or whitespaces!")
    private String breed;

    @NotBlank(message = "Kind should be not blank!")
    @Pattern(regexp = "[A-Za-z]+", message = "Kind should cpnsist only of alphabet characters!")
    private String kind;

    public Animal(String name, String color, Integer age, String gender, String breed, String kind) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.kind = kind;
    }

    public Animal() {
    }

    public Animal(Long id, String name, String color, Integer age, String gender, String breed, String kind) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.kind = kind;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
