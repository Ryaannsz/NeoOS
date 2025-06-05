package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.service.KernelService;

@Component
public class OpenAppAction implements ProcessUserAction {

    public static final String TYPE = "OPEN_APP";

    private KernelService kernelService;

    public OpenAppAction(KernelService kernelService) {
	this.kernelService = kernelService;
    }

    @Override
    public String getActionType() {
	return TYPE;
    }

    @Override
    public void execute(ProcessEntity process, Map<String, Object> payload) {

	kernelService.openApp(process, payload, TYPE);

    }

}
