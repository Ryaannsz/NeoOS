package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.models.ProcessEntity;
import com.revisao.demo.service.KernelService;

@Component
public class CloseAppAction implements ProcessUserAction {

    public static final String TYPE = "CLOSE_APP";

    private KernelService kernelService;

    public CloseAppAction(KernelService kernelService) {
	this.kernelService = kernelService;
    }

    @Override
    public String getActionType() {
	return TYPE;
    }

    @Override
    public void execute(ProcessEntity process, Map<String, Object> payload) {

	kernelService.closeApp(process, payload, TYPE);

    }

}
