package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.exception.UnsupportedActionException;
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
    public void execute(String appId, Map<String, Object> payload) throws UnsupportedActionException {

	kernelService.closeApp(appId, payload, TYPE);

    }

}
