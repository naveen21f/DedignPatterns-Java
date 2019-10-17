package com.ezetap.pipelinedesign.entity;

import java.util.LinkedHashSet;
import lombok.Data;

/**
 * @author naveen
 * @project pipeline-design
 */
@Data
public class Input {

    private LinkedHashSet<String> stageStamp = new LinkedHashSet<>();

    private Boolean success = Boolean.FALSE;

    public void stamp(String stage) {
        stageStamp.add(stage);
    }


    public Boolean isInFirstStage() {
        return stageStamp.isEmpty();
    }

}