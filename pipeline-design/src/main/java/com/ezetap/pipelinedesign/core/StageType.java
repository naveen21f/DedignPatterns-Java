package com.ezetap.pipelinedesign.core;

import lombok.Data;

/**
 * @author naveen
 * @project pipeline-design
 */
public enum StageType {


    A("amount"),
    N("nonbean"),
    B("bean"),
    C("conflict");

    private String type;

    StageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }}


