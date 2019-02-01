package com.dimple.modules.BackStageModule.Experiment.service;

import com.dimple.framework.message.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : ExperimentMdService
 * @description :
 * @date : 01/09/19 12:51
 */
public interface ExperimentMdService {

    Result handlerMdFile(MultipartFile file);

}
