package com.ezetap.pipelinedesign.core;

import com.ezetap.pipelinedesign.entity.Input;
import com.ezetap.pipelinedesign.entity.Output;

/**
 * @author naveen
 * @project pipeline-design
 */
public interface Stage <U extends Output, T extends Input>{

    public U execute(T input);

    public String name();

}
