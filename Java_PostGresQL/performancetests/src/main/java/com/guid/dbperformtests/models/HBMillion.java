package com.guid.dbperformtests.models;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hbmillion")
public class HBMillion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "some_value1")
    private String some_value1;

    @Column(name = "some_value2")
    private String some_value2;

    @Column(name = "some_value3")
    private String some_value3;

    @Column(name = "some_value4")
    private String some_value4;

    @Column(name = "some_value5")
    private Long some_value5;

    @Column(name = "some_value6")
    private String some_value6;

    @Column(name = "some_value7")
    private String some_value7;

    @Column(name = "some_value8")
    private String some_value8;

    @Column(name = "some_value9")
    private String some_value9;

    @Column(name = "some_value10")
    private Double some_value10;

    @Column(name = "some_value11")
    private String some_value11;

    @Column(name = "some_value12")
    private String some_value12;

    @Column(name = "some_value13")
    private String some_value13;

    @Column(name = "some_value14")
    private String some_value14;

    @Column(name = "some_value15")
    private String some_value15;

    @Column(name = "some_value16")
    private String some_value16;

    @Column(name = "some_value17")
    private String some_value17;

    @Column(name = "some_value18")
    private String some_value18;

    @Column(name = "some_value19")
    private String some_value19;

    @Column(name = "some_value20")
    private String some_value20;
}