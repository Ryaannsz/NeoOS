package com.revisao.demo.components;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.revisao.demo.util.ProducerManipulateDTO;

@Component
public class SaveFileAction implements ProcessUserAction {

    public static final String TYPE = "SAVE_FILE";

    private ProducerManipulateDTO manipulateDTO;

    public SaveFileAction(ProducerManipulateDTO manipulateDTO) {
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
