package com.ezetap.pipelinedesign.core;

import com.ezetap.pipelinedesign.entity.Input;
import com.ezetap.pipelinedesign.entity.Output;
import lombok.extern.slf4j.Slf4j;

/**
 * @author naveen
 * @project pipeline-design
 */
@Slf4j
public class StageFactory {

    public static Stage getStage(String type) {

        switch (type) {

            case "amount" : return new AmountStage();

            case "nonbean": return new NonBeanStage();

            case "bean": return new BeanStage();

            case "conflict": return new ConflictResolveStage();

            default:
              log.info("Invalid Stage Type {}", type);
              throw new RuntimeException("Invalid Stage Type");
        }
    }

}