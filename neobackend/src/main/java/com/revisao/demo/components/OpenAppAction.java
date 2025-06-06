package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.util.ProducerManipulateDTO;

@Component
public class OpenAppAction implements ProcessUserAction {

    public static final String TYPE = "OPEN_APP";

    private ProducerManipulateDTO manipulateDTO;

    public OpenAppAction(ProducerManipulateDTO manipulateDTO) {
	this.manipulateDTO = manipulateDTO;
    }

    @Override
    public String getActionType() {
	return TYPE;
    }

    @Override
    public void execute(String appId, Map<String, Object> payload) {

	manipulateDTO.createProcessDTO(appId, payload, TYPE);

    }

}
