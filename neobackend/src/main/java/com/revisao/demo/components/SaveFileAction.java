package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.service.KernelService;

@Component
public class SaveFileAction implements ProcessUserAction {

    public static final String TYPE = "SAVE_FILE";

    private KernelService kernelService;

    public SaveFileAction(KernelService kernelService) {
	this.kernelService = kernelService;
    }

    @Override
    public String getActionType() {
	return TYPE;
    }

    @Override
    public void execute(ProcessEntity process, Map<String, Object> payload) {

	kernelService.saveFile(process, payload, TYPE);

    }

}
